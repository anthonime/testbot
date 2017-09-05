package com.github.anthonime.testbot.definitions.elements;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by schio on 9/12/2017.
 */
public interface ElementDefinition extends HasElementIdentifier {
    @Nonnull
    Type getType();

    @Nonnull
    ElementIdentifier getIdentifier();

    @Override
    default ElementIdentifier getElementIdentifier() {
        return getIdentifier();
    }

    @Nullable
    SelectorDefinition getSelector();

    @Nullable
    ContainerElementDefinition getParent();

    void setParent(ContainerElementDefinition container);

    default boolean isRoot() {
        return getParent() == null;
    }

    default boolean isContainer() {
        return this instanceof ContainerElementDefinition;
    }
}
