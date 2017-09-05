package com.github.anthonime.testbot.runtime.databinding;

import com.github.anthonime.testbot.definitions.databinding.DataMapperDefinition;

/**
 * Created by schio on 9/14/2017.
 */
public interface DataMapperProvider {

    DataMapper get(DataMapperDefinition definition);
}
