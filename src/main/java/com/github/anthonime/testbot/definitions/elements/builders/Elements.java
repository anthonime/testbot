package com.github.anthonime.testbot.definitions.elements.builders;

import com.github.anthonime.testbot.definitions.elements.ButtonDefinition;
import com.github.anthonime.testbot.definitions.elements.InputDefinition;
import com.github.anthonime.testbot.definitions.elements.SelectorDefinition;
import com.github.anthonime.testbot.definitions.elements.TextDefinition;
import com.github.anthonime.testbot.w3c.xpath.XPathBuilder;

/**
 * Created by schio on 9/16/2017.
 */
public class Elements {

    public static ContainerElementDefinitionBuilder body(String identifier) {
        return container(identifier).withSelector(SelectorDefinition.css("body"));
    }

    public static ContainerElementDefinitionBuilder container(String identifier) {
        return ContainerElementDefinitionBuilder.aContainer(identifier);
    }

    public static ButtonDefinition button(String identifier) {
        return ButtonDefinitionBuilder.aButton(identifier).build();
    }

    public static ButtonDefinitionBuilder aButton(String identifier) {
        return ButtonDefinitionBuilder.aButton(identifier);
    }

    public static TextDefinitionBuilder aText(String identifier) {
        return TextDefinitionBuilder.aText(identifier);
    }

    public static TextDefinition text(String identifier) {
        return TextDefinitionBuilder.aText(identifier).build();
    }

    public static TextDefinition text(String identifier, String text) {
        return TextDefinitionBuilder.aText(identifier)
                .withSelector(SelectorDefinition.xpath(XPathBuilder.aRelativeDescendant()
                        .any().predicate().hasTextContaining(text.toLowerCase()).end().build()))
                .build();
    }

    public static InputDefinitionBuilder anInput(String identifier) {
        return InputDefinitionBuilder.anInput(identifier);
    }

    public static InputDefinition input(String identifier) {
        return InputDefinitionBuilder.anInput(identifier).build();
    }
}
