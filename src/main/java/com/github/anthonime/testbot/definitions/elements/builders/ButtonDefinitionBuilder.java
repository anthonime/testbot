package com.github.anthonime.testbot.definitions.elements.builders;

import com.github.anthonime.testbot.definitions.elements.ButtonDefinition;
import com.github.anthonime.testbot.definitions.elements.SelectorDefinition;

/**
 * Created by schio on 9/16/2017.
 */
public final class ButtonDefinitionBuilder extends AbstractElementBuilder {
    private SelectorDefinition selector;

    private ButtonDefinitionBuilder(String identifier) {
        super(identifier);
    }

    public static ButtonDefinitionBuilder aButton(String identifier) {
        return new ButtonDefinitionBuilder(identifier);
    }

    public ButtonDefinitionBuilder withSelector(SelectorDefinition selector) {
        this.selector = selector;
        return this;
    }

    public ButtonDefinition build() {
        ButtonDefinition buttonDefinition = new ButtonDefinition(identifier, selector);
        return buttonDefinition;
    }
}
