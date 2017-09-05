package com.github.anthonime.testbot.runtime.states;

import com.github.anthonime.testbot.definitions.databinding.ElementAttributeOrPropertyValueExtractorDefinition;
import com.github.anthonime.testbot.definitions.databinding.ElementTextExtractorDefinition;
import com.github.anthonime.testbot.definitions.databinding.ElementTitleExtractorDefinition;
import com.github.anthonime.testbot.definitions.databinding.ElementValueExtractorDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by schio on 9/15/2017.
 */
public class ElementValueExtractorProvider {

    private Map<ElementValueExtractorDefinition, ElementValueExtractor> extractors = new HashMap<>();

    public ElementValueExtractorProvider() {
    }

    public ElementValueExtractor get(ElementValueExtractorDefinition definition) {
        return extractors.computeIfAbsent(definition, d -> create(d));
    }

    private ElementValueExtractor create(ElementValueExtractorDefinition definition) {
        if (definition instanceof ElementAttributeOrPropertyValueExtractorDefinition) {
            return ElementValueExtractor.byAttributeOrProperty(((ElementAttributeOrPropertyValueExtractorDefinition) definition).getAttributeOrPropertyName());
        } else if (definition instanceof ElementTitleExtractorDefinition) {
            return ElementValueExtractor.byAttributeTitle();
        } else if (definition instanceof ElementTextExtractorDefinition) {
            return ElementValueExtractor.byElementText();
        }

        throw new IllegalStateException("not implemented " + definition);
    }
}
