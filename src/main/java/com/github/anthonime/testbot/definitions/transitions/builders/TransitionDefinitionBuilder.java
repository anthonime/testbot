package com.github.anthonime.testbot.definitions.transitions.builders;

import com.github.anthonime.testbot.definitions.actions.ActionDefinition;
import com.github.anthonime.testbot.definitions.actions.ActionDefinitionList;
import com.github.anthonime.testbot.definitions.states.HasStateIdentifier;
import com.github.anthonime.testbot.definitions.states.StateDefinition;
import com.github.anthonime.testbot.definitions.states.StateIdentifier;
import com.github.anthonime.testbot.definitions.transitions.TransitionDefinition;
import com.github.anthonime.testbot.definitions.transitions.TransitionIdentifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by schio on 9/17/2017.
 */
public final class TransitionDefinitionBuilder {
    private final TransitionIdentifier identifier;
    private StateIdentifier targetState;
    private ActionDefinitionList actions;
    private List<StateIdentifier> sourceStates;

    private TransitionDefinitionBuilder(String identifier) {
        this.identifier = new TransitionIdentifier(identifier);
    }

    public static TransitionDefinitionBuilder aTransition(String identifier) {
        return new TransitionDefinitionBuilder(identifier);
    }

    public TransitionDefinitionBuilder to(StateDefinition targetState) {
        this.targetState = targetState.getStateIdentifier();
        return this;
    }

    public TransitionDefinitionBuilder by(ActionDefinition... actions) {
        return by(new ActionDefinitionList(actions));
    }

    public TransitionDefinitionBuilder by(ActionDefinitionList actions) {
        this.actions = actions;
        return this;
    }

    public TransitionDefinitionBuilder from(HasStateIdentifier... sourceStates) {
        return from(Arrays.asList(sourceStates));
    }

    public TransitionDefinitionBuilder from(List<HasStateIdentifier> sourceStates) {
        this.sourceStates = sourceStates.stream().map(HasStateIdentifier::getStateIdentifier).collect(Collectors.toList());
        return this;
    }

    public TransitionDefinition build() {
        TransitionDefinition transitionDefinition = new TransitionDefinition(identifier, targetState, actions, sourceStates);
        return transitionDefinition;
    }
}
