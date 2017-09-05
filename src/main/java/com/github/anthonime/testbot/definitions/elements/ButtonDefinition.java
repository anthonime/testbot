package com.github.anthonime.testbot.definitions.elements;

/**
 * Created by schio on 9/12/2017.
 */
public class ButtonDefinition extends AbstractElementDefinition {

    public ButtonDefinition(String identifier) {
        this(identifier, null);
    }

    public ButtonDefinition(String identifier, SelectorDefinition selector) {
        super(identifier, Type.BUTTON, selector);
    }

}
