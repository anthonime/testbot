package com.github.anthonime.testbot.definitions.states;

import com.github.anthonime.testbot.definitions.databinding.DataMapperDefinition;
import com.github.anthonime.testbot.definitions.databinding.ElementValueExtractorDefinition;
import com.github.anthonime.testbot.definitions.elements.ElementIdentifier;

import java.util.Objects;

/**
 * Created by schio on 9/13/2017.
 */
public class DataBindedElementStateDefinition extends AbstractElementStateDefinition {

    private ElementValueExtractorDefinition elementValueExtractorDefinition;
    private DataMapperDefinition dataMapperDefinition;

    public DataBindedElementStateDefinition(String identifier, ElementValueExtractorDefinition elementValueExtractorDefinition, DataMapperDefinition dataMapperDefinition) {
        super(identifier);
        this.elementValueExtractorDefinition = Objects.requireNonNull(elementValueExtractorDefinition);
        this.dataMapperDefinition = Objects.requireNonNull(dataMapperDefinition);
    }

    public DataBindedElementStateDefinition(ElementIdentifier elementIdentifier, ElementValueExtractorDefinition elementValueExtractorDefinition, DataMapperDefinition dataMapperDefinition) {
        super(elementIdentifier);
        this.elementValueExtractorDefinition = Objects.requireNonNull(elementValueExtractorDefinition);
        this.dataMapperDefinition = Objects.requireNonNull(dataMapperDefinition);
    }

    public ElementValueExtractorDefinition getElementValueExtractorDefinition() {
        return elementValueExtractorDefinition;
    }

    public DataMapperDefinition getDataMapperDefinition() {
        return dataMapperDefinition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DataBindedElementStateDefinition that = (DataBindedElementStateDefinition) o;

        if (!elementValueExtractorDefinition.equals(that.elementValueExtractorDefinition)) return false;
        return dataMapperDefinition.equals(that.dataMapperDefinition);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + elementValueExtractorDefinition.hashCode();
        result = 31 * result + dataMapperDefinition.hashCode();
        return result;
    }

    @Override
    public boolean isDataBinded() {
        return false;
    }
}
