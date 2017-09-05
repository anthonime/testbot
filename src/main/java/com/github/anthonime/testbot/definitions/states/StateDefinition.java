package com.github.anthonime.testbot.definitions.states;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by schio on 9/12/2017.
 */
public class StateDefinition implements HasStateIdentifier {
    private StateIdentifier superstate;
    private StateIdentifier identifier;
    private List<ElementStateDefinition> elementStateDefinitions;
    private URLStateDefinition urlStateDefinition;


    public StateDefinition(String identifier, String superstate, URLStateDefinition urlStateDefinition, ElementStateDefinition... elementStateDefinitions) {
        this(new StateIdentifier(superstate), new StateIdentifier(identifier), Arrays.asList(elementStateDefinitions), urlStateDefinition);
    }

    public StateDefinition(String identifier, URLStateDefinition urlStateDefinition, ElementStateDefinition... elementStateDefinitions) {
        this(null, new StateIdentifier(identifier), Arrays.asList(elementStateDefinitions), urlStateDefinition);
    }

    public StateDefinition(StateIdentifier superstate, StateIdentifier identifier, List<ElementStateDefinition> elementStateDefinitions, URLStateDefinition urlStateDefinition) {
        this.superstate = superstate;
        this.identifier = identifier;
        this.elementStateDefinitions = Objects.requireNonNull(elementStateDefinitions);
        this.urlStateDefinition = urlStateDefinition;
    }

    public StateIdentifier getIdentifier() {
        return identifier;
    }

    public StateIdentifier getSuperstateIdentifier() {
        return superstate;
    }

    public List<ElementStateDefinition> getElementStateDefinitions() {
        return elementStateDefinitions;
    }

    public boolean isDataBinded() {
        for (ElementStateDefinition elementStateDefinition : elementStateDefinitions) {
            if (elementStateDefinition.isDataBinded()) {
                return true;
            }
        }
        return false;
    }

    public URLStateDefinition getUrlStateDefinition() {
        return urlStateDefinition;
    }

    public boolean isRoot() {
        return identifier.isRoot();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateDefinition that = (StateDefinition) o;

        if (superstate != null ? !superstate.equals(that.superstate) : that.superstate != null) return false;
        if (!identifier.equals(that.identifier)) return false;
        if (!elementStateDefinitions.equals(that.elementStateDefinitions)) return false;
        return urlStateDefinition != null ? urlStateDefinition.equals(that.urlStateDefinition) : that.urlStateDefinition == null;
    }

    @Override
    public int hashCode() {
        int result = superstate != null ? superstate.hashCode() : 0;
        result = 31 * result + identifier.hashCode();
        result = 31 * result + elementStateDefinitions.hashCode();
        result = 31 * result + (urlStateDefinition != null ? urlStateDefinition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StateDefinition{" +
                "superstate=" + superstate +
                ", identifier=" + identifier +
                ", elementStateDefinitions=" + elementStateDefinitions +
                ", urlStateDefinition=" + urlStateDefinition +
                '}';
    }

    @Override
    public StateIdentifier getStateIdentifier() {
        return this.getIdentifier();
    }
}
