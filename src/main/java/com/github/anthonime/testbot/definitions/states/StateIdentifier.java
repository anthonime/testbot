package com.github.anthonime.testbot.definitions.states;

/**
 * Created by schio on 9/12/2017.
 */
public class StateIdentifier implements HasStateIdentifier {
    private String identifier;

    public StateIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateIdentifier that = (StateIdentifier) o;

        return identifier != null ? identifier.equals(that.identifier) : that.identifier == null;
    }

    @Override
    public int hashCode() {
        return identifier != null ? identifier.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "StateIdentifier{" +
                "identifier='" + identifier + '\'' +
                '}';
    }

    public boolean isRoot() {
        return identifier.equals("root");
    }

    @Override
    public StateIdentifier getStateIdentifier() {
        return this;
    }
}
