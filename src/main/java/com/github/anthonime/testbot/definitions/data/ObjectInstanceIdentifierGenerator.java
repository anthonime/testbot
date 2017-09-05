package com.github.anthonime.testbot.definitions.data;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Created by schio on 9/13/2017.
 */
public class ObjectInstanceIdentifierGenerator {

    private Multiset<Class<?>> sequenceNumberGenerator = HashMultiset.create();

    private ObjectDefinitionIdentifierFactory objectDefinitionIdentifierFactory;

    public ObjectInstanceIdentifierGenerator() {
        this.objectDefinitionIdentifierFactory = new ObjectDefinitionIdentifierFactory();

    }

    public ObjectInstanceIdentifier generate(Class<?> clazz) {
        int oldValue = sequenceNumberGenerator.add(clazz, 1);
        return new ObjectInstanceIdentifier(objectDefinitionIdentifierFactory.fromClass(clazz), oldValue + 1);
    }
}
