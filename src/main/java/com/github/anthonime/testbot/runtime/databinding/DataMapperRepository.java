package com.github.anthonime.testbot.runtime.databinding;

import com.github.anthonime.testbot.definitions.databinding.DataMapperDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by schio on 9/14/2017.
 */
public class DataMapperRepository implements DataMapperProvider {

    public Map<DataMapperDefinition, DataMapper> databinders = new HashMap<>();


    public DataMapper put(DataMapperDefinition definition, DataMapper binder) {
        return databinders.put(definition, binder);
    }

    public DataMapper get(DataMapperDefinition definition) {
        return databinders.get(definition);
    }
}
