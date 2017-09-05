package com.github.anthonime.testbot.definitions.data;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by schio on 9/13/2017.
 */
public class ObjectDefinitionRepository {

    private Map<ObjectDefinitionIdentifier, ObjectDefinition> objectDefinitionMap;

    public ObjectDefinitionRepository(ObjectDefinition... instances) {
        this(Arrays.asList(instances));
    }

    public ObjectDefinitionRepository(List<ObjectDefinition> instances) {
        this.objectDefinitionMap = new HashMap<>(Maps.uniqueIndex(instances, def -> def.getIdentifier()));

    }

    public ObjectDefinition getDefinition(ObjectDefinitionIdentifier identifier) {
        return objectDefinitionMap.get(identifier);
    }

    public void putDefinition(ObjectDefinition definition) {
        objectDefinitionMap.put(definition.getIdentifier(), definition);
    }


}
