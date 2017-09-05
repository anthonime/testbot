package com.github.anthonime.testbot.definitions.elements;

/**
 * Created by schio on 9/12/2017.
 */
public class InputDefinition extends AbstractElementDefinition {

    public InputDefinition(String identifier) {
        this(identifier, null);
    }

    public InputDefinition(String identifier, SelectorDefinition selector) {
        super(identifier, Type.INPUT, selector);
    }
}
