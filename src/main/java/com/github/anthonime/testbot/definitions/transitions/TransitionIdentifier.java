package com.github.anthonime.testbot.definitions.transitions;

import java.util.Objects;

/**
 * Created by schio on 9/17/2017.
 */
public class TransitionIdentifier {
    private String identifier;

    public TransitionIdentifier(String identifier) {
        this.identifier = Objects.requireNonNull(identifier);
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransitionIdentifier that = (TransitionIdentifier) o;

        return identifier.equals(that.identifier);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public String toString() {
        return "TransitionIdentifier{" +
                "identifier='" + identifier + '\'' +
                '}';
    }
}
