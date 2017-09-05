package com.github.anthonime.testbot.definitions.states.builders;

import com.github.anthonime.testbot.definitions.elements.ElementIdentifier;

/**
 * Created by schio on 9/16/2017.
 */
public abstract class AbstractElementStateDefinitionBuilder {
    protected ElementIdentifier elementIdentifier;

    protected AbstractElementStateDefinitionBuilder(ElementIdentifier elementIdentifier) {
        this.elementIdentifier = elementIdentifier;
    }


}
