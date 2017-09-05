package com.github.anthonime.testbot.definitions.states;

import com.github.anthonime.testbot.definitions.elements.ElementIdentifier;

import java.util.Objects;

/**
 * Created by schio on 9/10/2017.
 */
public abstract class AbstractElementStateDefinition implements ElementStateDefinition {
    private final ElementIdentifier elementIdentifier;

    public AbstractElementStateDefinition(String identifier) {
        this(new ElementIdentifier(identifier));
    }

    public AbstractElementStateDefinition(ElementIdentifier elementIdentifier) {
        this.elementIdentifier = Objects.requireNonNull(elementIdentifier);
    }

    @Override
    public ElementIdentifier getElementIdentifier() {
        return elementIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractElementStateDefinition that = (AbstractElementStateDefinition) o;

        return elementIdentifier.equals(that.elementIdentifier);
    }

    @Override
    public int hashCode() {
        return elementIdentifier.hashCode();
    }
}
