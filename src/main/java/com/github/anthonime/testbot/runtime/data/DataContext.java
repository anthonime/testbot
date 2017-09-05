package com.github.anthonime.testbot.runtime.data;

import com.github.anthonime.testbot.definitions.data.ObjectDefinitionIdentifier;
import com.github.anthonime.testbot.definitions.data.ObjectInstance;
import com.github.anthonime.testbot.definitions.data.ObjectInstanceIdentifier;
import com.github.anthonime.testbot.definitions.data.ObjectInstanceRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by schio on 9/13/2017.
 */
public class DataContext {

    private final Map<ObjectDefinitionIdentifier, ObjectInstanceIdentifier> currentObjects = new HashMap<>();
    private ObjectInstanceRepository repository;

    public DataContext(ObjectInstanceRepository repository) {
        this.repository = repository;
    }

    public ObjectInstance getCurrent(ObjectDefinitionIdentifier identifier) {
        ObjectInstanceIdentifier objectInstanceIdentifier = currentObjects.get(identifier);
        return repository.getObject(objectInstanceIdentifier);
    }

    public void setCurrent(ObjectInstance newCurrentInstance) {
        this.currentObjects.put(newCurrentInstance.getDefinition().getIdentifier(), newCurrentInstance.getIdentifier());
    }
}
