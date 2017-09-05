package com.github.anthonime.testbot.runtime.elements;

import com.github.anthonime.testbot.definitions.elements.ContainerElementDefinition;
import com.github.anthonime.testbot.definitions.elements.ElementDefinition;
import com.github.anthonime.testbot.definitions.elements.ElementIdentifier;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by schio on 9/12/2017.
 */
public class ElementRepository {

    private Map<ElementIdentifier, Element> elementMap = new HashMap<>();
    private Map<ElementIdentifier, ElementDefinition> elementDefinitionMap;
    private ElementFactory elementFactory;

    public ElementRepository(ElementFactory elementFactory, ElementDefinition... elementDefinitions) {
        this(elementFactory, Arrays.asList(elementDefinitions));
    }

    public ElementRepository(ElementFactory elementFactory, List<ElementDefinition> elementDefinitions) {
        this.elementDefinitionMap = Maps.uniqueIndex(flattenContainerChildrens(elementDefinitions), ElementDefinition::getIdentifier);
        this.elementFactory = elementFactory;
    }

    private List<ElementDefinition> flattenContainerChildrens(List<ElementDefinition> elementDefinitions) {
        return elementDefinitions.stream().flatMap(e -> toList(e)).collect(Collectors.toList());
    }

    private Stream<ElementDefinition> toList(ElementDefinition e) {
        return e.isContainer() ? concatContainerAndChildren((ContainerElementDefinition) e) : Stream.of(e);
    }

    private Stream<ElementDefinition> concatContainerAndChildren(ContainerElementDefinition e) {
        return Stream.concat(Stream.of(e), e.getChildrenElementDefinitions().stream());
    }

    /**
     * Return the {@link Element} identified by this identifier.
     *
     * @param identifier
     * @return
     */
    public <T extends Element> T get(ElementIdentifier identifier) {
        return Objects.requireNonNull(getOrCreateElement(identifier), "No element for identifier " + identifier);
    }

    private <T extends Element> T getOrCreateElement(ElementIdentifier identifier) {
        return (T) elementMap.computeIfAbsent(identifier, this::createElement);
    }

    private <T extends Element> T createElement(ElementIdentifier identifier) {
        ElementDefinition elementDefinition = elementDefinitionMap.get(identifier);
        Objects.requireNonNull(elementDefinition, "No definition for element identifier " + identifier);
        return (T) elementFactory.create(elementDefinition);
    }

}
