package com.github.anthonime.testbot.definitions.states;

import com.github.anthonime.testbot.definitions.elements.ElementIdentifier;

/**
 * Created by schio on 9/10/2017.
 */
public interface ElementStateDefinition {

    /**
     * The element identified by this state
     *
     * @return
     */
    ElementIdentifier getElementIdentifier();

    /**
     * Return true if the element state defines a databinding
     *
     * @return
     */
    boolean isDataBinded();
}
