package com.github.anthonime.testbot.definitions.applications;

import com.github.anthonime.testbot.definitions.data.ObjectDefinitionRepository;
import com.github.anthonime.testbot.definitions.elements.ElementDefinition;
import com.github.anthonime.testbot.definitions.states.StateDefinition;
import com.github.anthonime.testbot.definitions.transitions.TransitionDefinition;

import java.util.Arrays;
import java.util.List;

/**
 * Created by schio on 9/16/2017.
 */
public final class ApplicationDefinitionBuilder {
    private String identifier;
    private List<EnvironmentDefinition> environmentDefinitions;
    private List<StateDefinition> stateDefinitions;
    private List<ElementDefinition> elementDefinitions;
    private ObjectDefinitionRepository objectDefinitionRepository;
    private List<TransitionDefinition> transitionDefinitions;


    private ApplicationDefinitionBuilder(String identifier) {
        this.identifier = identifier;
    }

    public static ApplicationDefinitionBuilder anApplication(String identifier) {
        return new ApplicationDefinitionBuilder(identifier);
    }

    public ApplicationDefinitionBuilder withObjectDefinitions(ObjectDefinitionRepository objectDefinitionRepository) {
        this.objectDefinitionRepository = objectDefinitionRepository;
        return this;
    }

    public ApplicationDefinitionBuilder withTransitions(TransitionDefinition... transitions) {
        return withTransitions(Arrays.asList(transitions));
    }

    private ApplicationDefinitionBuilder withTransitions(List<TransitionDefinition> transitions) {
        this.transitionDefinitions = transitions;
        return this;
    }


    public ApplicationDefinitionBuilder withElements(ElementDefinition... elementDefinitions) {
        return withElements(Arrays.asList(elementDefinitions));
    }

    public ApplicationDefinitionBuilder withElements(List<ElementDefinition> elementDefinitions) {
        this.elementDefinitions = elementDefinitions;
        return this;
    }

    public ApplicationDefinitionBuilder withEnvironments(EnvironmentDefinition... environmentDefinitions) {
        return withEnvironments(Arrays.asList(environmentDefinitions));
    }

    public ApplicationDefinitionBuilder withEnvironments(List<EnvironmentDefinition> environmentDefinitions) {
        this.environmentDefinitions = environmentDefinitions;
        return this;
    }

    public ApplicationDefinitionBuilder withStates(StateDefinition... stateDefinitions) {
        return withStates(Arrays.asList(stateDefinitions));
    }

    public ApplicationDefinitionBuilder withStates(List<StateDefinition> stateDefinitions) {
        this.stateDefinitions = stateDefinitions;
        return this;
    }

    public ApplicationDefinition build() {
        ApplicationDefinition applicationDefinition = new ApplicationDefinition(
                identifier,
                environmentDefinitions,
                stateDefinitions,
                elementDefinitions,
                objectDefinitionRepository,
                transitionDefinitions);
        return applicationDefinition;
    }
}
