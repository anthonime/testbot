package com.github.anthonime.testbot.definitions.data;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by schio on 9/13/2017.
 * Manage instanciation (with caching) of ObjectDefinitions,
 * with registration in the ObjectRepository.
 */
public class ObjectDefinitionFactory {


    private ObjectDefinitionRepository repository;

    private ObjectDefinitionIdentifierFactory identifierFactory;

    public ObjectDefinitionFactory(ObjectDefinitionRepository repository) {
        this.repository = repository;
        this.identifierFactory = new ObjectDefinitionIdentifierFactory();
    }


    /**
     * @param clazz
     * @return
     */
    public ObjectDefinition fromClass(Class<?> clazz) {
        return getOrCreateDefinition(clazz);
    }

    private ObjectDefinition getOrCreateDefinition(Class<?> clazz) {
        ObjectDefinitionIdentifier definitionIdentifier = identifierFactory.fromClass(clazz);
        ObjectDefinition definition = repository.getDefinition(definitionIdentifier);
        if (definition == null) {
            ObjectDefinitionIdentifier superClassIdentifier = null;
            if (clazz.getSuperclass() != null) {
                if (samePackages(clazz, clazz.getSuperclass())) {
                    superClassIdentifier = getOrCreateDefinition(clazz.getSuperclass()).getIdentifier();
                }
            }
            List<String> propertyNames = extractPropertyNames(clazz);
            definition = new ObjectDefinition(definitionIdentifier, superClassIdentifier, propertyNames);
            repository.putDefinition(definition);
        }
        return definition;
    }


    private List<String> extractPropertyNames(Class<?> clazz) {
        return FieldUtils.getAllFieldsList(clazz).stream().map(f -> f.getName()).collect(Collectors.toList());
    }

    private boolean samePackages(Class<?> clazz, Class<?> superclass) {
        return clazz.getPackage().getName().startsWith(superclass.getPackage().getName())
                || superclass.getPackage().getName().startsWith(clazz.getPackage().getName());
    }
}
