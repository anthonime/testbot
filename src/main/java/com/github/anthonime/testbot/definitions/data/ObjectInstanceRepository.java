package com.github.anthonime.testbot.definitions.data;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;

import java.util.*;

/**
 * Created by schio on 9/13/2017.
 * In memory storage for object instances.
 */
public class ObjectInstanceRepository implements ObjectInstanceExampleProvider {


    private Map<ObjectInstanceIdentifier, ObjectInstance> instanceMap;
    private ListMultimap<ObjectDefinitionIdentifier, ObjectInstance> examples;

    public ObjectInstanceRepository(ObjectInstance... instances) {
        this(Arrays.asList(instances));
    }

    public ObjectInstanceRepository(List<ObjectInstance> instances) {
        this.instanceMap = new HashMap<>(Maps.uniqueIndex(instances, i -> i.getIdentifier()));
        this.examples = Multimaps.index(instances, o -> o.getDefinition().getIdentifier());
    }

    public ObjectInstance getObject(ObjectInstanceIdentifier objectInstanceIdentifier) {
        return Objects.requireNonNull(instanceMap.get(objectInstanceIdentifier),
                "no object instance for identifier " + objectInstanceIdentifier);
    }


    public void putObject(ObjectInstance instance) {
        instanceMap.put(instance.getIdentifier(), instance);
    }

    @Override
    public List<ObjectInstance> getExamples(ObjectDefinitionIdentifier definitionIdentifier) {
        return examples.get(definitionIdentifier);
    }
}
