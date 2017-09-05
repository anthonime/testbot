package com.github.anthonime.testbot.definitions.states;

import com.github.anthonime.testbot.definitions.elements.ElementIdentifier;

import java.util.Objects;

/**
 * Created by schio on 9/13/2017.
 */
public class VisibilityStateDefinition extends AbstractElementStateDefinition {
    private VisibilityState state;

    public VisibilityStateDefinition(String identifier, VisibilityState state) {
        super(identifier);
        this.state = Objects.requireNonNull(state);
    }

    public VisibilityStateDefinition(ElementIdentifier elementIdentifier, VisibilityState state) {
        super(elementIdentifier);
        this.state = Objects.requireNonNull(state);
    }

    public VisibilityState getVisibilityState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisibilityStateDefinition that = (VisibilityStateDefinition) o;

        return state == that.state;
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    @Override
    public boolean isDataBinded() {
        return false;
    }
}
