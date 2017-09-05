package com.github.anthonime.testbot.runtime.states;

import com.github.anthonime.testbot.definitions.states.DataBindedElementStateDefinition;
import com.github.anthonime.testbot.definitions.states.ElementStateDefinition;
import com.github.anthonime.testbot.definitions.states.VisibilityState;
import com.github.anthonime.testbot.definitions.states.VisibilityStateDefinition;
import com.github.anthonime.testbot.runtime.databinding.DataMapper;
import com.github.anthonime.testbot.runtime.databinding.DataMapperProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by schio on 9/16/2017.
 */
public class ElementStateActivatorProvider {


    private static Map<ElementStateDefinition, ElementStateActivator> ACTIVATORS = new HashMap<>();
    private DataMapperProvider dataMapperProvider;
    private ElementValueExtractorProvider valueExtractorProvider;

    public ElementStateActivatorProvider(DataMapperProvider dataMapperProvider, ElementValueExtractorProvider valueExtractorProvider) {
        this.dataMapperProvider = dataMapperProvider;
        this.valueExtractorProvider = valueExtractorProvider;
    }

    public ElementStateActivator get(ElementStateDefinition definition) {
        return ACTIVATORS.computeIfAbsent(definition, d -> createElementStateActivator(d));
    }

    private ElementStateActivator createElementStateActivator(ElementStateDefinition definition) {
        if (definition instanceof VisibilityStateDefinition) {
            VisibilityState visibilityState = ((VisibilityStateDefinition) definition).getVisibilityState();
            return VisibilityElementStateActivator.from(visibilityState);
        } else if (definition instanceof DataBindedElementStateDefinition) {
            DataBindedElementStateDefinition dbesd = (DataBindedElementStateDefinition) definition;
            DataMapper dataMapper = dataMapperProvider.get(dbesd.getDataMapperDefinition());
            ElementValueExtractor elementValueExtractor = valueExtractorProvider.get(dbesd.getElementValueExtractorDefinition());
            return new DataElementStateActivatorImpl(elementValueExtractor, dataMapper);
        } else throw new IllegalStateException("Cannot get activator for " + definition);
    }
}
