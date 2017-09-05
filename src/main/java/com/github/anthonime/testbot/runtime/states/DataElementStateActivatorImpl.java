package com.github.anthonime.testbot.runtime.states;

import com.github.anthonime.testbot.definitions.data.ObjectInstance;
import com.github.anthonime.testbot.runtime.databinding.DataMapper;
import com.github.anthonime.testbot.runtime.elements.Element;

import java.util.Objects;

/**
 * Created by schio on 9/14/2017.
 */
public class DataElementStateActivatorImpl implements ElementStateActivator {
    private ElementValueExtractor elementExtractor;
    private DataMapper dataMapper;

    public DataElementStateActivatorImpl(ElementValueExtractor elementExtractor, DataMapper dataMapper) {
        this.elementExtractor = elementExtractor;
        this.dataMapper = dataMapper;
    }

    public ElementValueExtractor getElementExtractor() {
        return elementExtractor;
    }

    public DataMapper getDataExtractor() {
        return dataMapper;
    }


    @Override
    public boolean isActive(Element element, ObjectInstance instance) {
        Object elementValue = extractElementValue(element);
        Object dataValue = dataMapper.format(instance);
        return Objects.equals(elementValue, dataValue);
    }

    private Object extractElementValue(Element element) {
        try {
            return elementExtractor.extract(element);
        } catch (Exception e) {
            throw new IllegalStateException("Impossible to extract the value from the element " + element, e);
        }
    }
}
