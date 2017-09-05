package com.github.anthonime.testbot.definitions.data;

import java.util.Objects;

/**
 * Created by schio on 9/13/2017.
 */
public class ObjectDefinitionIdentifier {
    private String identifier;

    public ObjectDefinitionIdentifier(String identifier) {
        this.identifier = Objects.requireNonNull(identifier);
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectDefinitionIdentifier that = (ObjectDefinitionIdentifier) o;

        return identifier.equals(that.identifier);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public String toString() {
        return getIdentifier();
    }
}
