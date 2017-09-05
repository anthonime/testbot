package com.github.anthonime.testbot.definitions.elements;

/**
 * Created by schio on 9/12/2017.
 */
public class TextDefinition extends AbstractElementDefinition {

    public TextDefinition(String identifier) {
        this(identifier, null);
    }

    public TextDefinition(String identifier, SelectorDefinition selector) {
        super(identifier, Type.TEXT, selector);
    }

}
