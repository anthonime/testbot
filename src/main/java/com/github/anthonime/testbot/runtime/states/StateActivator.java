package com.github.anthonime.testbot.runtime.states;

import com.github.anthonime.testbot.definitions.data.ObjectInstance;
import com.github.anthonime.testbot.definitions.states.StateDefinition;

/**
 * Created by schio on 9/12/2017.
 */
public interface StateActivator {

    /**
     * Return true if the state definition is currently active
     * taking into account the current data context.
     *
     * @param definition
     * @return
     */
    boolean isActive(StateDefinition definition, ObjectInstance instance);


}
