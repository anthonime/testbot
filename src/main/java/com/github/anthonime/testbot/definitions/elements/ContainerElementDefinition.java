package com.github.anthonime.testbot.definitions.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by schio on 9/12/2017.
 */
public class ContainerElementDefinition extends AbstractElementDefinition {

    private List<ElementDefinition> elementDefinitions;

    public ContainerElementDefinition(String identifier, SelectorDefinition selector, ElementDefinition... children) {
        this(identifier, selector, Arrays.asList(children));
    }

    public ContainerElementDefinition(String identifier, SelectorDefinition selector, List<ElementDefinition> children) {
        super(identifier, Type.CONTAINER, Objects.requireNonNull(selector, "Selector cannot be null!"));
        this.elementDefinitions = children;
        if (children != null) {
            for (ElementDefinition elementDefinition : this.elementDefinitions) {
                elementDefinition.setParent(this);
            }
        } else {
            this.elementDefinitions = new ArrayList<>();
        }
    }

    public void addChild(ElementDefinition definition) {
        this.elementDefinitions.add(definition);
        definition.setParent(this);
    }

    public List<ElementDefinition> getChildrenElementDefinitions() {
        return elementDefinitions;
    }

    public HasElementIdentifier getChild(int i) {
        return getChildrenElementDefinitions().get(i);
    }
}

