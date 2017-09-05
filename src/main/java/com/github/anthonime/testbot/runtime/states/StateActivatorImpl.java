package com.github.anthonime.testbot.runtime.states;

import com.github.anthonime.testbot.definitions.data.ObjectInstance;
import com.github.anthonime.testbot.definitions.elements.ElementIdentifier;
import com.github.anthonime.testbot.definitions.states.*;
import com.github.anthonime.testbot.runtime.elements.Element;
import com.github.anthonime.testbot.runtime.elements.ElementRepository;
import com.github.anthonime.testbot.runtime.elements.WebPageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Objects;

/**
 * Created by schio on 9/12/2017.
 */
public class StateActivatorImpl implements StateActivator {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateActivatorImpl.class);

    private WebPageContext context;
    private ElementRepository elementRepository;
    private StateRepository stateRepository;
    private ElementStateActivatorProvider elementStateActivatorProvider;

    public StateActivatorImpl(WebPageContext context, ElementRepository elementRepository, StateRepository stateRepository, ElementStateActivatorProvider elementStateActivatorProvider) {
        this.context = context;
        this.elementRepository = elementRepository;
        this.stateRepository = stateRepository;
        this.elementStateActivatorProvider = elementStateActivatorProvider;
    }

    @Override
    public boolean isActive(StateDefinition definition, ObjectInstance instance) {
        //should put cache (to avoid evaluating superstates n times!
        //FIXME merge all state + superstates together , instead of
        if (!definition.isRoot()) {
            boolean isSuperstateActive = isSuperstateActive(definition.getSuperstateIdentifier(), instance);
            if (!isSuperstateActive) {
                return false;
            }
        }
        LOGGER.debug("Checking if state {} is active", definition.getIdentifier());
        boolean active = isUrlStateActive(definition) && areElementStatesActive(definition, instance);
        LOGGER.debug("State {} is {}", definition.getIdentifier(), active ? "active" : "not active");
        return active;
    }

    private boolean areElementStatesActive(StateDefinition definition, ObjectInstance instance) {
        return definition.getElementStateDefinitions().stream()
                .allMatch(esd -> isElementStateActive(esd, instance));
    }


    private boolean isUrlStateActive(StateDefinition stateDefinition) {
        URLStateDefinition urlStateDefinition = stateDefinition.getUrlStateDefinition();
        if (urlStateDefinition != null) {
            return checkURLStateDefinition(urlStateDefinition);
        } else {
            return true;
        }
    }

    private boolean checkURLStateDefinition(URLStateDefinition urlStateDefinition) {
        URI currentURI = context.getCurrentURI();
        //FIXME: what about databinding + this is too much simplist
        return currentURI.toString().contains(urlStateDefinition.getUrl());
    }


    private boolean isSuperstateActive(StateIdentifier superstateIdentifier, ObjectInstance data) {
        if (superstateIdentifier == null) {
            return true;
        } else {
            StateDefinition superstateDefinition = stateRepository.getStateDefinition(superstateIdentifier);
            Objects.requireNonNull(superstateDefinition, "no superstate definition for state " + superstateIdentifier);
            return isActive(superstateDefinition, data);
        }
    }

    private boolean isElementStateActive(ElementStateDefinition esd, ObjectInstance data) {
        LOGGER.debug("Checking if element state {} is active", esd.getElementIdentifier());
        ElementStateActivator activator = elementStateActivatorProvider.get(esd);
        ElementIdentifier identifier = esd.getElementIdentifier();
        Element element = elementRepository.get(identifier);
        boolean active = activator.isActive(element, data);
        LOGGER.debug("DataStateDefintion for element {} with data {} is {}", identifier, data, active ? "active" : "not active");
        return active;

    }


}
