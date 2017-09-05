package com.github.anthonime.testbot.definitions.data;

import java.util.Map;
import java.util.Objects;

/**
 * Created by schio on 9/13/2017.
 */
public class ObjectInstance {

    private ObjectDefinition definition;
    private ObjectInstanceIdentifier identifier;
    private Map<String, Object> properties;

    public ObjectInstance(ObjectDefinition definition, ObjectInstanceIdentifier identifier, Map<String, Object> properties) {
        this.definition = Objects.requireNonNull(definition);
        this.identifier = Objects.requireNonNull(identifier);
        this.properties = Objects.requireNonNull(properties);
    }

    public ObjectDefinition getDefinition() {
        return definition;
    }

    public ObjectInstanceIdentifier getIdentifier() {
        return identifier;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public Object getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public void setProperty(String propertyName, Object propertyValue) {
        properties.put(propertyName, propertyValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectInstance that = (ObjectInstance) o;

        if (!definition.equals(that.definition)) return false;
        if (!identifier.equals(that.identifier)) return false;
        return properties.equals(that.properties);
    }

    @Override
    public int hashCode() {
        int result = definition.hashCode();
        result = 31 * result + identifier.hashCode();
        result = 31 * result + properties.hashCode();
        return result;
    }
}
