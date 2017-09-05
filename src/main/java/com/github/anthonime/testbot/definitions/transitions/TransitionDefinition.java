package com.github.anthonime.testbot.definitions.transitions;

import com.github.anthonime.testbot.definitions.actions.ActionDefinitionList;
import com.github.anthonime.testbot.definitions.states.StateIdentifier;

import java.util.Arrays;
import java.util.List;

/**
 * Created by schio on 9/10/2017.
 */
public class TransitionDefinition {

    private TransitionIdentifier identifier;
    private StateIdentifier targetState;
    private ActionDefinitionList actions;
    private List<StateIdentifier> sourceStates;

    public TransitionDefinition(StateIdentifier targetState, ActionDefinitionList actions, TransitionIdentifier identifier, StateIdentifier... sourceStates) {
        this(identifier, targetState, actions, Arrays.asList(sourceStates));
    }

    public TransitionDefinition(TransitionIdentifier identifier, StateIdentifier targetState, ActionDefinitionList actions, List<StateIdentifier> sourceStates) {
        this.identifier = identifier;
        this.targetState = targetState;
        this.actions = actions;
        this.sourceStates = sourceStates;
    }

    public StateIdentifier getTargetState() {
        return targetState;
    }

    public ActionDefinitionList getActions() {
        return actions;
    }

    public List<StateIdentifier> getSourceStates() {
        return sourceStates;
    }
}
