package com.github.anthonime.testbot.definitions.applications;

import com.github.anthonime.testbot.definitions.data.ObjectDefinitionRepository;
import com.github.anthonime.testbot.definitions.elements.ElementDefinition;
import com.github.anthonime.testbot.definitions.states.StateDefinition;
import com.github.anthonime.testbot.definitions.states.builders.StateDefinitionBuilder;
import com.github.anthonime.testbot.definitions.transitions.TransitionDefinition;
import com.github.anthonime.testbot.runtime.applications.exceptions.UnknownEnvironmentException;
import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import com.google.common.collect.FluentIterable;

import java.util.List;
import java.util.Objects;

/**
 * Created by schio on 9/16/2017.
 */
public class ApplicationDefinition {

    private final String identifier;
    private final List<EnvironmentDefinition> environments;
    private final List<StateDefinition> states;
    private final List<ElementDefinition> elements;
    private final List<TransitionDefinition> transitions;
    private final ObjectDefinitionRepository definitionRepository;
    private final StateDefinition rootStateDefintion;

    public ApplicationDefinition(String identifier, List<EnvironmentDefinition> environments, List<StateDefinition> states, List<ElementDefinition> elements, ObjectDefinitionRepository definitionRepository, List<TransitionDefinition> transitions) {
        Preconditions.checkArgument(environments.size() > 0, "no environment defined");
        this.definitionRepository = Objects.requireNonNull(definitionRepository);
        this.identifier = Objects.requireNonNull(identifier);
        this.environments = environments;
        this.elements = Objects.requireNonNull(elements);
        this.rootStateDefintion = createRootStateDefinition(environments);
        this.states = injectRootStateInChildStates(Objects.requireNonNull(states), this.rootStateDefintion);
        this.transitions = Objects.requireNonNull(transitions);
    }

    private List<StateDefinition> injectRootStateInChildStates(List<StateDefinition> states, StateDefinition rootStateDefintion) {
        return FluentIterable.of(rootStateDefintion).append(states).toList();
    }

    private StateDefinition createRootStateDefinition(List<EnvironmentDefinition> environments) {
        //FIXME
        Verify.verify(environments.size() == 1, "several environmenets not yet implemented !");
        return StateDefinitionBuilder.aState("root")
                .at(environments.get(0).getUri().toString())
                .build();
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<StateDefinition> getStateDefinitions() {
        return states;
    }

    public List<EnvironmentDefinition> getEnvironmentDefinitions() {
        return environments;
    }

    public EnvironmentDefinition getEnvironmentByName(String environment) {
        return environments.stream().filter(e -> e.getIdentifier().equals(environment)).findFirst().orElseThrow(
                () -> new UnknownEnvironmentException("cannot find environment " + environment)
        );
    }

    public List<ElementDefinition> getElementDefinitions() {
        return elements;
    }

    public ObjectDefinitionRepository getObjectDefinitionRepository() {
        return definitionRepository;
    }

    public StateDefinition getRootStateDefinition() {
        return rootStateDefintion;
    }

    public StateDefinition getRootStateDefinition(EnvironmentDefinition environmentDefinition) {
        //FIXME
        Preconditions.checkArgument(environmentDefinition.getIdentifier().equals("root"), "only one single root env is handled for the moment");
        return rootStateDefintion;
    }

    public List<TransitionDefinition> getTransitions() {
        return transitions;
    }
}
