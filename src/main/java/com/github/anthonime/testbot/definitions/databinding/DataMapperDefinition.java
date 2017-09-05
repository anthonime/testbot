package com.github.anthonime.testbot.definitions.databinding;

import com.github.anthonime.testbot.definitions.data.ObjectDefinitionIdentifier;

import java.util.Objects;

/**
 * Created by schio on 9/13/2017.
 */
public class DataMapperDefinition {

    private ObjectDefinitionIdentifier objectDefinitionIdentifier;
    private DataMapperType mapperType;
    private String mapping;


    public DataMapperDefinition(ObjectDefinitionIdentifier objectDefinitionIdentifier, DataMapperType mapperType, String mapping) {
        this.objectDefinitionIdentifier = Objects.requireNonNull(objectDefinitionIdentifier);
        this.mapperType = Objects.requireNonNull(mapperType);
        this.mapping = Objects.requireNonNull(mapping);
    }

    public DataMapperType getMapperType() {
        return mapperType;
    }

    public String getMapping() {
        return mapping;
    }


    public ObjectDefinitionIdentifier getDataType() {
        return objectDefinitionIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataMapperDefinition that = (DataMapperDefinition) o;

        if (!objectDefinitionIdentifier.equals(that.objectDefinitionIdentifier)) return false;
        if (mapperType != that.mapperType) return false;
        return mapping.equals(that.mapping);
    }

    @Override
    public int hashCode() {
        int result = objectDefinitionIdentifier.hashCode();
        result = 31 * result + mapperType.hashCode();
        result = 31 * result + mapping.hashCode();
        return result;
    }
}
