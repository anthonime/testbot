package com.github.anthonime.testbot.definitions.elements;

import javax.annotation.Nullable;

/**
 * Created by schio on 9/12/2017.
 */
public abstract class AbstractElementDefinition implements ElementDefinition {
    protected ElementIdentifier identifier;
    private Type type;
    private SelectorDefinition selector;
    private ContainerElementDefinition parent;

    public AbstractElementDefinition(String identifier, Type type, @Nullable SelectorDefinition selector) {
        this.identifier = new ElementIdentifier(identifier);
        this.type = type;
        this.selector = selector;
    }

    @Nullable
    @Override
    public ContainerElementDefinition getParent() {
        return parent;
    }

    @Override
    public void setParent(ContainerElementDefinition parent) {
        this.parent = parent;
    }

    @Override
    public ElementIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Nullable
    @Override
    public SelectorDefinition getSelector() {
        return selector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractElementDefinition that = (AbstractElementDefinition) o;

        if (!identifier.equals(that.identifier)) return false;
        if (type != that.type) return false;
        if (selector != null ? !selector.equals(that.selector) : that.selector != null) return false;
        return parent != null ? parent.equals(that.parent) : that.parent == null;
    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (selector != null ? selector.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }
}
