package com.github.anthonime.testbot.runtime.databinding;

import com.github.anthonime.testbot.definitions.databinding.DataMapperDefinition;

/**
 * Created by schio on 9/16/2017.
 */
public class DataMapperProviderImpl implements DataMapperProvider {
    private DataMapperRepository dataBinderRepository;
    private DataMapperFactory dataBinderFactory;

    public DataMapperProviderImpl(DataMapperRepository dataMapperRepository, DataMapperFactory dataMapperFactory) {
        this.dataBinderRepository = dataMapperRepository;
        this.dataBinderFactory = dataMapperFactory;
    }

    @Override
    public DataMapper get(DataMapperDefinition dataMapperDefinition) {
        DataMapper dataMapper = dataBinderRepository.get(dataMapperDefinition);
        if (dataMapper == null) {
            dataMapper = dataBinderFactory.get(dataMapperDefinition);
            dataBinderRepository.put(dataMapperDefinition, dataMapper);
        }
        return dataMapper;
    }
}
