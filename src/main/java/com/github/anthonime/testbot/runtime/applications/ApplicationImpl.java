package com.github.anthonime.testbot.runtime.applications;

import com.github.anthonime.testbot.definitions.actions.ActionDefinition;
import com.github.anthonime.testbot.definitions.actions.ActionDefinitionList;
import com.github.anthonime.testbot.definitions.applications.ApplicationDefinition;
import com.github.anthonime.testbot.definitions.applications.EnvironmentDefinition;
import com.github.anthonime.testbot.definitions.states.StateDefinition;
import com.github.anthonime.testbot.definitions.transitions.TransitionDefinition;
import com.github.anthonime.testbot.runtime.actions.ActionListResult;
import com.github.anthonime.testbot.runtime.actions.ActionResult;
import com.github.anthonime.testbot.runtime.elements.WebPageContext;
import com.github.anthonime.testbot.runtime.states.State;
import com.github.anthonime.testbot.runtime.states.StateImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schio on 9/16/2017.
 */
public class ApplicationImpl implements Application {

    private WebPageContext context;
    private ApplicationDefinition definition;
    private ApplicationSupport applicationSupport;

    public ApplicationImpl(WebPageContext context, ApplicationDefinition definition) {
        this.context = context;
        this.definition = definition;
        this.applicationSupport = new ApplicationSupport(definition, context);
    }

    @Override
    public boolean isOpen() {
        return isStateActive(definition.getRootStateDefinition());
    }

    @Override
    public boolean isOpen(String environment) {
        return isOpen(definition.getEnvironmentByName(environment));
    }

    @Override
    public EnvironmentDefinition getActiveEnvironment() {
        return definition.getEnvironmentDefinitions().stream()
                .filter(e -> isOpen(e))
                .findFirst().orElse(null);
    }

    protected boolean isOpen(EnvironmentDefinition environmentDefinition) {
        return isStateActive(definition.getRootStateDefinition(environmentDefinition));
    }

    @Override
    public void navigateTo(String environment) {
        EnvironmentDefinition env = definition.getEnvironmentByName(environment);
        if (!isOpen(env)) {
            activate(env);
        }
    }

    @Override
    public void open() {
        if (definition.getEnvironmentDefinitions().size() > 1) {
            navigateTo(definition.getEnvironmentByName("main").getIdentifier());
        } else {
            navigateTo(definition.getEnvironmentDefinitions().get(0).getIdentifier());
        }
    }

    private void activate(EnvironmentDefinition env) {
        context.setCurrentURI(env.getUri());
    }


    @Override
    public List<State> getActiveStates(ApplicationContext context) {
        //there is a complex logic here!
        //this is in fact the heart!
        //what about abstract states...?
        //merging states together ?
        //exclusive / mutual states
        //the stupid thing is to iterate over all the statedefs,
        //and assert them (using all the possible objects)
        List<State> activeStates = new ArrayList<>();
        List<StateDefinition> stateDefinitions = this.definition.getStateDefinitions();
        for (StateDefinition stateDefinition : stateDefinitions) {
            if (stateDefinition.isDataBinded()) {
                //FIXME: should iterate over all the known object in the context
                //and assert the state with each of them.
                throw new IllegalStateException("not yet implemented");
            } else {
                if (isStateActive(stateDefinition)) {
                    activeStates.add(new StateImpl(stateDefinition, null));
                }
            }
        }
        return activeStates;
    }

    private boolean isStateActive(StateDefinition stateDefinition) {
        return applicationSupport.getStateActivator().isActive(stateDefinition, null);
    }

    @Override
    public ActionResult performAction(ActionDefinition action) {
        return applicationSupport.getActionExecutor().execute(action);
    }

    @Override
    public ActionListResult performActions(ActionDefinitionList actionList) {
        return applicationSupport.getActionExecutor().execute(actionList);
    }

    @Override
    public void operateTransition(TransitionDefinition transitionDefinition) {

    }
}
