package com.github.anthonime.testbot.definitions.states;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by schio on 9/12/2017.
 */
public class StateRepository {

    private Map<StateIdentifier, StateDefinition> stateDefinitionMap;

    public StateRepository(List<StateDefinition> elementStateDefinitions) {
        this.stateDefinitionMap = Maps.uniqueIndex(elementStateDefinitions, StateDefinition::getIdentifier);
    }

    public StateDefinition getStateDefinition(String identifier) {
        return getStateDefinition(new StateIdentifier(identifier));
    }

    public StateDefinition getStateDefinition(StateIdentifier identifier) {
        return stateDefinitionMap.get(identifier);
    }

    public Collection<StateDefinition> getStateDefinitions() {
        return stateDefinitionMap.values();
    }

    public Set<StateIdentifier> getStateIdentifiers() {
        return stateDefinitionMap.keySet();
    }
}
