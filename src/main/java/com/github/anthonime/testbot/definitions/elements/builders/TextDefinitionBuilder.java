package com.github.anthonime.testbot.definitions.elements.builders;

import com.github.anthonime.testbot.definitions.elements.SelectorDefinition;
import com.github.anthonime.testbot.definitions.elements.TextDefinition;

/**
 * Created by schio on 9/16/2017.
 */
public final class TextDefinitionBuilder extends AbstractElementBuilder {
    private SelectorDefinition selector;

    private TextDefinitionBuilder(String identifier) {
        super(identifier);
    }

    public static TextDefinitionBuilder aText(String identifier) {
        return new TextDefinitionBuilder(identifier);
    }

    public TextDefinitionBuilder withSelector(SelectorDefinition selector) {
        this.selector = selector;
        return this;
    }

    public TextDefinition build() {
        TextDefinition def = new TextDefinition(identifier, selector);
        return def;
    }
}
