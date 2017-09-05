package com.github.anthonime.testbot.definitions.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by schio on 9/13/2017.
 */
public class ObjectDefinitionIdentifierFactory {

    private Map<Class<?>, ObjectDefinitionIdentifier> definitionIdentifierByClass = new HashMap<>();

    public ObjectDefinitionIdentifier fromClass(Class<?> clazz) {
        return getOrCreateDefinitionIdentifier(clazz);
    }

    private ObjectDefinitionIdentifier getOrCreateDefinitionIdentifier(Class<?> clazz) {
        return definitionIdentifierByClass.computeIfAbsent(clazz, ObjectDefinitionIdentifierFactory::createDefinitionIdentifier);
    }


    private static ObjectDefinitionIdentifier createDefinitionIdentifier(Class<?> clazz) {
        return new ObjectDefinitionIdentifier(clazz.getSimpleName());
    }


}
