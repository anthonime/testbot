package com.github.anthonime.testbot.runtime.states;

import com.github.anthonime.testbot.definitions.data.ObjectInstance;
import com.github.anthonime.testbot.definitions.states.StateDefinition;

/**
 * Created by schio on 9/16/2017.
 */
public interface State {

    StateDefinition getDefinition();

    ObjectInstance getObject();
}
