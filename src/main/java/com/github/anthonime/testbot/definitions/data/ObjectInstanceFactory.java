package com.github.anthonime.testbot.definitions.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by schio on 9/13/2017.
 */
public class ObjectInstanceFactory {

    private final ObjectMapper objectMapper;
    private ObjectDefinitionFactory objectDefinitionFactory;
    private ObjectInstanceRepository repository;
    private ObjectInstanceIdentifierGenerator identifierGenerator;
    private final Map<Object, ObjectInstance> instanceMap = new WeakHashMap();

    public ObjectInstanceFactory(ObjectDefinitionFactory objectDefinitionFactory, ObjectInstanceRepository repository, ObjectInstanceIdentifierGenerator identifierGenerator) {
        this.objectDefinitionFactory = objectDefinitionFactory;
        this.repository = repository;
        this.identifierGenerator = identifierGenerator;
        this.objectMapper = initMapper();
    }

    private ObjectMapper initMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper;
    }

    public ObjectInstance fromObject(Object o) {
        return instanceMap.computeIfAbsent(o, this::createFromObjectAndStore);
    }

    private ObjectInstance createFromObjectAndStore(Object o) {
        ObjectInstance instance = createFromObject(o);
        repository.putObject(instance);
        return instance;
    }

    private ObjectInstance createFromObject(Object o) {
        Class<?> objectClass = o.getClass();
        ObjectDefinition definition = objectDefinitionFactory.fromClass(objectClass);
        ObjectInstanceIdentifier identifier = identifierGenerator.generate(objectClass);
        Map<String, Object> properties = extractProperties(o);
        return new ObjectInstance(definition, identifier, properties);
    }

    private Map<String, Object> extractProperties(Object o) {
        return objectMapper.convertValue(o, Map.class);
    }


}
