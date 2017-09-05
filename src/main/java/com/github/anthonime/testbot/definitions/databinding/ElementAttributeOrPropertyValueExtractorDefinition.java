package com.github.anthonime.testbot.definitions.databinding;

import java.util.Objects;

/**
 * Created by schio on 9/15/2017.
 */
public class ElementAttributeOrPropertyValueExtractorDefinition extends ElementValueExtractorDefinition {
    private String attributeOrPropertyName;

    public ElementAttributeOrPropertyValueExtractorDefinition(String attributeOrPropertyName) {
        this.attributeOrPropertyName = Objects.requireNonNull(attributeOrPropertyName);
    }

    public String getAttributeOrPropertyName() {
        return attributeOrPropertyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElementAttributeOrPropertyValueExtractorDefinition that = (ElementAttributeOrPropertyValueExtractorDefinition) o;

        return attributeOrPropertyName.equals(that.attributeOrPropertyName);
    }

    @Override
    public int hashCode() {
        return attributeOrPropertyName.hashCode();
    }
}
