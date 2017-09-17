package com.github.anthonime.testbot.runtime.applications;

import com.github.anthonime.testbot.definitions.applications.ApplicationDefinition;
import com.github.anthonime.testbot.definitions.data.ObjectInstance;
import com.github.anthonime.testbot.definitions.states.StateDefinition;
import com.github.anthonime.testbot.definitions.states.StateIdentifier;
import com.github.anthonime.testbot.definitions.states.StateRepository;
import com.github.anthonime.testbot.definitions.transitions.TransitionDefinition;
import com.github.anthonime.testbot.runtime.actions.ActionExecutor;
import com.github.anthonime.testbot.runtime.actions.ActionExecutorImpl;
import com.github.anthonime.testbot.runtime.actions.ActionListResult;
import com.github.anthonime.testbot.runtime.databinding.DataMapperFactory;
import com.github.anthonime.testbot.runtime.databinding.DataMapperProvider;
import com.github.anthonime.testbot.runtime.databinding.DataMapperProviderImpl;
import com.github.anthonime.testbot.runtime.databinding.DataMapperRepository;
import com.github.anthonime.testbot.runtime.elements.ElementFactory;
import com.github.anthonime.testbot.runtime.elements.ElementRepository;
import com.github.anthonime.testbot.runtime.elements.WebPageContext;
import com.github.anthonime.testbot.runtime.elements.impl.ElementFactoryImpl;
import com.github.anthonime.testbot.runtime.elements.webdriver.WebElementProviderFactoryImpl;
import com.github.anthonime.testbot.runtime.elements.webdriver.WebPageContextImpl;
import com.github.anthonime.testbot.runtime.states.ElementStateActivatorProvider;
import com.github.anthonime.testbot.runtime.states.ElementValueExtractorProvider;
import com.github.anthonime.testbot.runtime.states.StateActivator;
import com.github.anthonime.testbot.runtime.states.StateActivatorImpl;
import com.github.anthonime.testbot.runtime.transitions.TransitionResult;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by schio on 9/17/2017.
 */
public class ApplicationSupport {

    private ApplicationDefinition definition;
    private WebPageContext context;

    private WebElementProviderFactoryImpl webElementProviderFactory;
    private ElementFactory elementFactory;
    private ElementRepository elementRepository;

    private StateRepository stateRepository;

    private DataMapperFactory dataMapperFactory;
    private DataMapperRepository dataMapperRepository;
    private DataMapperProvider dataMapperProvider;

    private ElementValueExtractorProvider elementValueExtractorProvider;
    private ElementStateActivatorProvider elementStateActivatorProvider;

    private StateActivator stateActivator;

    private ActionExecutor actionExecutor;

    public ApplicationSupport(ApplicationDefinition definition, WebPageContext context) {
        this.definition = definition;
        this.context = context;
        this.webElementProviderFactory = new WebElementProviderFactoryImpl((WebPageContextImpl) context);
        this.elementFactory = new ElementFactoryImpl(webElementProviderFactory);
        this.elementRepository = new ElementRepository(elementFactory, definition.getElementDefinitions());
        this.stateRepository = new StateRepository(definition.getStateDefinitions());
        this.dataMapperFactory = new DataMapperFactory(definition.getObjectDefinitionRepository());
        this.dataMapperRepository = new DataMapperRepository();
        this.dataMapperProvider = new DataMapperProviderImpl(dataMapperRepository, dataMapperFactory);
        this.elementValueExtractorProvider = new ElementValueExtractorProvider();
        this.elementStateActivatorProvider = new ElementStateActivatorProvider(dataMapperProvider, elementValueExtractorProvider);
        this.stateActivator = new StateActivatorImpl(context, elementRepository, stateRepository, elementStateActivatorProvider);
        this.actionExecutor = new ActionExecutorImpl(context, elementRepository);
    }

    public ActionExecutor getActionExecutor() {
        return actionExecutor;
    }

    public StateDefinition getStateDefinition(StateIdentifier identifier) {
        return Objects.requireNonNull(stateRepository.getStateDefinition(identifier), "The state " + identifier + " does not exists!");
    }

    public TransitionResult operationTransition(TransitionDefinition transition) {
        List<StateIdentifier> inactiveStates = verifySourceStatesAreActive(transition);
        if (inactiveStates.size() > 0) {
            return TransitionResult.notStarted(inactiveStates);
        }
        ActionListResult actionListResult = getActionExecutor().execute(transition.getActions());
        if (!actionListResult.isSuccess()) {
            return TransitionResult.aborted(actionListResult);
        }

        if (!isTransitionEffective(transition)) {
            return TransitionResult.notEffective(actionListResult);
        }
        return TransitionResult.success(actionListResult);
    }

    private boolean isTransitionEffective(TransitionDefinition transition) {
        return isStateActive(getStateDefinition(transition.getTargetState()), null);
    }

    public boolean isStateActive(StateDefinition state, ObjectInstance instance) {
        return stateActivator.isActive(state, null);
    }

    private List<StateIdentifier> verifySourceStatesAreActive(TransitionDefinition transitionDefinition) {
        List<StateIdentifier> sourceStates = transitionDefinition.getSourceStates();
        List<StateIdentifier> inactiveStates = sourceStates.stream().filter(s -> !isStateActive(getStateDefinition(s), null))
                .collect(Collectors.toList());
        return inactiveStates;
    }
}
