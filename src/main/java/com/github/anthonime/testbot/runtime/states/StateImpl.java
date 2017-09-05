package com.github.anthonime.testbot.runtime.states;

import com.github.anthonime.testbot.definitions.data.ObjectInstance;
import com.github.anthonime.testbot.definitions.states.StateDefinition;

/**
 * Created by schio on 9/16/2017.
 */
public class StateImpl implements State {

    private StateDefinition stateDefinition;
    private ObjectInstance objectInstance;

    public StateImpl(StateDefinition stateDefinition, ObjectInstance objectInstance) {
        this.stateDefinition = stateDefinition;
        this.objectInstance = objectInstance;
    }

    @Override
    public ObjectInstance getObject() {
        return objectInstance;
    }

    @Override
    public StateDefinition getDefinition() {
        return stateDefinition;
    }
}
