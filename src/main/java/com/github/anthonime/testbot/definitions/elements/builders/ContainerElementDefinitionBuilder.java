package com.github.anthonime.testbot.definitions.elements.builders;

import com.github.anthonime.testbot.definitions.elements.ContainerElementDefinition;
import com.github.anthonime.testbot.definitions.elements.ElementDefinition;
import com.github.anthonime.testbot.definitions.elements.SelectorDefinition;

import java.util.Arrays;
import java.util.List;

/**
 * Created by schio on 9/16/2017.
 */
public final class ContainerElementDefinitionBuilder extends AbstractElementBuilder {

    private SelectorDefinition selector;
    private List<ElementDefinition> children;

    private ContainerElementDefinitionBuilder(String identifier) {
        super(identifier);
    }

    public static ContainerElementDefinitionBuilder aContainer(String identifier) {
        return new ContainerElementDefinitionBuilder(identifier);
    }

    public ContainerElementDefinitionBuilder withSelector(SelectorDefinition selector) {
        this.selector = selector;
        return this;
    }

    public ContainerElementDefinitionBuilder withChildren(ElementDefinition... children) {
        return withChildren(Arrays.asList(children));
    }

    public ContainerElementDefinitionBuilder withChildren(List<ElementDefinition> children) {
        this.children = children;
        return this;
    }

    public ContainerElementDefinition build() {
        ContainerElementDefinition containerElementDefinition = new ContainerElementDefinition(identifier, selector, children);
        return containerElementDefinition;
    }
}
