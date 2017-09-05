package com.github.anthonime.testbot.definitions.states.builders;

import com.github.anthonime.testbot.definitions.elements.ContainerElementDefinition;
import com.github.anthonime.testbot.definitions.states.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by schio on 9/16/2017.
 */
public final class StateDefinitionBuilder {
    private StateIdentifier superstate;
    private StateIdentifier identifier;
    private List<ElementStateDefinition> elementStateDefinitions = new ArrayList<>();
    private URLStateDefinition urlStateDefinition;

    private StateDefinitionBuilder(String identifier) {
        this.identifier = new StateIdentifier(identifier);
    }

    public static StateDefinitionBuilder aState(String identifier) {
        return new StateDefinitionBuilder(identifier);
    }

    public static StateDefinitionBuilder aState(StateDefinition other) {
        return aState(other.getIdentifier().getIdentifier())
                .withSuperstate(other.getSuperstateIdentifier())
                .with(other.getElementStateDefinitions())
                .at(other.getUrlStateDefinition().getUrl());
    }

    public StateDefinitionBuilder withSuperstate(String superstateIdentifier) {
        this.superstate = new StateIdentifier(superstateIdentifier);
        return this;
    }

    public StateDefinitionBuilder withSuperstate(StateIdentifier superstateIdentifier) {
        this.superstate = superstateIdentifier;
        return this;
    }

    public StateDefinitionBuilder with(ElementStateDefinition... elementStateDefinitions) {
        return with(Arrays.asList(elementStateDefinitions));
    }

    public StateDefinitionBuilder with(List<ElementStateDefinition> elementStateDefinitions) {
        this.elementStateDefinitions = elementStateDefinitions;
        return this;
    }

    public StateDefinitionBuilder withAllElementsVisible(ContainerElementDefinition container) {
        this.elementStateDefinitions.add(new VisibilityStateDefinition(container.getElementIdentifier(), VisibilityState.VISIBLE));
        this.elementStateDefinitions.addAll(container.getChildrenElementDefinitions().stream().map(e -> States.elementVisible(e)).collect(Collectors.toList()));
        return this;
    }


    public StateDefinition build() {
        StateDefinition stateDefinition = new StateDefinition(superstate, identifier, elementStateDefinitions, urlStateDefinition);
        return stateDefinition;
    }

    /**
     * Indicate this state is bound to the provided URL location.
     *
     * @param pathQueryFragments
     * @return
     */
    public StateDefinitionBuilder at(String pathQueryFragments) {
        urlStateDefinition = new URLStateDefinition(pathQueryFragments);
        return this;
    }

    /**
     * Indicate the state is bound to the root URL
     *
     * @return
     */
    public StateDefinitionBuilder atRoot() {
        return at("/");
    }

}
