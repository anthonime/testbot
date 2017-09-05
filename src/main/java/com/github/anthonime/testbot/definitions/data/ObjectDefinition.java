package com.github.anthonime.testbot.definitions.data;

import java.util.List;
import java.util.Objects;

/**
 * Created by schio on 9/13/2017.
 */
public class ObjectDefinition {
    private ObjectDefinitionIdentifier identifier;
    private ObjectDefinitionIdentifier superClassIdentifier;
    private List<String> propertyNames;

    public ObjectDefinition(ObjectDefinitionIdentifier identifier, ObjectDefinitionIdentifier superClassIdentifier, List<String> propertyNames) {
        this.identifier = Objects.requireNonNull(identifier);
        this.superClassIdentifier = superClassIdentifier;
        this.propertyNames = Objects.requireNonNull(propertyNames);
    }

    public ObjectDefinitionIdentifier getIdentifier() {
        return identifier;
    }

    public ObjectDefinitionIdentifier getSuperDefinitionIdentifier() {
        return superClassIdentifier;
    }

    public List<String> getPropertyNames() {
        return propertyNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectDefinition that = (ObjectDefinition) o;

        if (!identifier.equals(that.identifier)) return false;
        if (superClassIdentifier != null ? !superClassIdentifier.equals(that.superClassIdentifier) : that.superClassIdentifier != null)
            return false;
        return propertyNames.equals(that.propertyNames);
    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + (superClassIdentifier != null ? superClassIdentifier.hashCode() : 0);
        result = 31 * result + propertyNames.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ObjectDefinition{" +
                "identifier=" + identifier +
                ", superClassIdentifier=" + superClassIdentifier +
                ", propertyNames=" + propertyNames +
                '}';
    }

    public boolean hasProperty(String p) {
        return getPropertyNames().contains(p);
    }
}
