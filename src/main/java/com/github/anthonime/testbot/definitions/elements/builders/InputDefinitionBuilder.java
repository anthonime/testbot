package com.github.anthonime.testbot.definitions.elements.builders;

import com.github.anthonime.testbot.definitions.elements.InputDefinition;
import com.github.anthonime.testbot.definitions.elements.SelectorDefinition;

/**
 * Created by schio on 9/16/2017.
 */
public final class InputDefinitionBuilder extends AbstractElementBuilder {
    private SelectorDefinition selector;

    private InputDefinitionBuilder(String identifer) {
        super(identifer);
    }

    public static InputDefinitionBuilder anInput(String identifer) {
        return new InputDefinitionBuilder(identifer);
    }

    public InputDefinitionBuilder withSelector(SelectorDefinition selector) {
        this.selector = selector;
        return this;
    }

    public InputDefinition build() {
        InputDefinition inputDefinition = new InputDefinition(identifier, selector);
        return inputDefinition;
    }
}
