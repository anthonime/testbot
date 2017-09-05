package com.github.anthonime.testbot.runtime.actions;

import com.github.anthonime.testbot.definitions.actions.ActionDefinition;
import com.github.anthonime.testbot.definitions.actions.ActionDefinitionList;
import com.github.anthonime.testbot.definitions.elements.ElementIdentifier;
import com.github.anthonime.testbot.runtime.elements.ElementRepository;
import com.github.anthonime.testbot.runtime.elements.WebPageContext;
import com.github.anthonime.testbot.runtime.elements.webdriver.WebDriverElement;
import com.github.anthonime.testbot.runtime.elements.webdriver.WebPageContextImpl;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.InvalidCoordinatesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schio on 9/14/2017.
 */
public class ActionExecutorImpl implements ActionExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActionExecutorImpl.class);
    private WebPageContext context;
    private ElementRepository repository;

    public ActionExecutorImpl(WebPageContext context, ElementRepository repository) {
        this.context = context;
        this.repository = repository;
    }

    @Override
    public ActionResult execute(ActionDefinition action) {
        LOGGER.debug("Executing action {}", describeAction(action));
        try {
            Action a = createWebDriverAction(action);
            a.perform();
            return new ActionResult(action, ActionResultType.SUCCESS, null);
        } catch (Exception e) {
            LOGGER.info("Impossible to execute action {}", describeAction(action), e);
            return new ActionResult(action, ActionResultType.FAILURE, toReason(e));
        }
    }

    @Override
    public ActionListResult execute(ActionDefinitionList actions) {
        LOGGER.debug("Executing action list {}", describeActions(actions));
        List<ActionResult> results = new ArrayList<>();
        int lastSuccessfulActionIndex = executeActionsUntilFailure(actions, results);
        fillSkippedActionResults(actions, results, lastSuccessfulActionIndex);
        return new ActionListResult(actions, results);
    }

    private void fillSkippedActionResults(ActionDefinitionList actions, List<ActionResult> results, int lastSuccessfulActionIndex) {
        int skippedActionIndex = lastSuccessfulActionIndex + 1;
        //fill the other results with SKIPPED
        while (skippedActionIndex < actions.getActions().size()) {
            results.add(new ActionResult(actions.getActions().get(skippedActionIndex++), ActionResultType.SKIPPED, null));
        }
    }

    private int executeActionsUntilFailure(ActionDefinitionList actions, List<ActionResult> results) {
        int lastSuccessfulActionIndex = -1;
        for (ActionDefinition actionDefinition : actions.getActions()) {
            ActionResult result = execute(actionDefinition);
            results.add(result);
            if (result.getType() == ActionResultType.FAILURE) {
                break;
            }
            lastSuccessfulActionIndex++;
        }
        return lastSuccessfulActionIndex;
    }

    private String describeActions(ActionDefinitionList actions) {
        return actions.getActions().stream().map(this::describeAction)
                .reduce("", (s1, s2) -> s1 + "," + s2);
    }

    private String describeAction(ActionDefinition action) {
        return action.toString();
    }

    private ActionFailureReason toReason(Exception ex) {
        try {
            throw ex;
        } catch (ElementNotVisibleException e) {
            return ActionFailureReason.ELEMENT_NOT_VISIBLE;
        } catch (NotFoundException e) {
            return ActionFailureReason.ELEMENT_NOT_FOUND;
        } catch (ElementNotInteractableException e) {
            return ActionFailureReason.ELEMENT_NOT_INTERACTABLE;
        } catch (ElementNotSelectableException e) {
            return ActionFailureReason.ELEMENT_NOT_SELECTABLE;
        } catch (InvalidCoordinatesException e) {
            return ActionFailureReason.INVALID_COORDINATES;
        } catch (Exception e) {
            return ActionFailureReason.UNKNOWN_REASON;
        }
    }

    private Action createWebDriverAction(ActionDefinition action) {
        Actions actions = new Actions(((WebPageContextImpl) context).getWebDriver());
        actions = appendAction(action, actions);
        return actions.build();
    }

    @Nullable
    private Actions appendAction(ActionDefinition action, Actions actions) {
        //handle
        switch (action.getVerb()) {
            case click:
                return appendClick(action, actions);
            case pause:
                return appendPause(action, actions);
            case type:
            case sendKeys:
            case keyDown:
            case keyUp:
            default:
                throw new IllegalStateException("not implemented " + action.getVerb());
        }
    }

    private Actions appendPause(ActionDefinition action, Actions actions) {
        return actions.pause(action.getDuration());
    }

    private Actions appendClick(ActionDefinition action, Actions actions) {
        if (action.getTarget() != null) {
            return actions.click(toElement(action.getTarget()));
        } else {
            return actions.click();
        }
    }

    private WebElement toElement(ElementIdentifier target) {
        return ((WebDriverElement) repository.get(target)).getWebElementIfPresent();
    }
}
