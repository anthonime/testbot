package com.github.anthonime.testbot.runtime.databinding;

import com.github.anthonime.testbot.definitions.data.ObjectInstance;

/**
 * Created by schio on 9/14/2017.
 */
public class DataMapperPropertyImpl implements DataMapper {
    private String propertyName;

    public DataMapperPropertyImpl(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String format(ObjectInstance instance) {
        return formatPropertyValue(instance.getProperty(propertyName));
    }

    private String formatPropertyValue(Object value) {
        return value == null ? "" : value.toString();
    }

    @Override
    public void parse(String text, ObjectInstance instance) {
        //get property type ?
        instance.setProperty(propertyName, parsePropertyValue(text));
    }

    private Object parsePropertyValue(String text) {
        //definition.getPropertyType
        //return DataType.converter.parse(text)
        return text;
    }
}
