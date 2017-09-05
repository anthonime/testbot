package com.github.anthonime.testbot.definitions.elements;

/**
 * Created by schio on 9/12/2017.
 */
public class ElementIdentifier implements HasElementIdentifier {

    private String identifier;

    public ElementIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String toLowerCase() {
        return identifier.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElementIdentifier that = (ElementIdentifier) o;

        return identifier != null ? identifier.equals(that.identifier) : that.identifier == null;
    }

    @Override
    public int hashCode() {
        return identifier != null ? identifier.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ElementIdentifier{" +
                "identifier='" + identifier + '\'' +
                '}';
    }

    @Override
    public ElementIdentifier getElementIdentifier() {
        return this;
    }
}
