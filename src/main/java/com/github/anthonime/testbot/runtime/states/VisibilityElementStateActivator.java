package com.github.anthonime.testbot.runtime.states;

import com.github.anthonime.testbot.definitions.data.ObjectInstance;
import com.github.anthonime.testbot.definitions.states.VisibilityState;
import com.github.anthonime.testbot.runtime.elements.Element;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by schio on 9/15/2017.
 */
public class VisibilityElementStateActivator implements ElementStateActivator {


    private static final Logger LOGGER = LoggerFactory.getLogger(StateActivatorImpl.class);
    private VisibilityState visibilityState;

    public static Map<VisibilityState, VisibilityElementStateActivator> INSTANCES = ImmutableMap.of(
            VisibilityState.VISIBLE, new VisibilityElementStateActivator(VisibilityState.VISIBLE),
            VisibilityState.INVISIBLE, new VisibilityElementStateActivator(VisibilityState.INVISIBLE),
            VisibilityState.UNDEFINED, new VisibilityElementStateActivator(VisibilityState.UNDEFINED)
    );

    public static VisibilityElementStateActivator from(VisibilityState state) {
        return INSTANCES.get(state);
    }

    private VisibilityElementStateActivator(VisibilityState visibilityState) {
        this.visibilityState = visibilityState;
    }

    public boolean isActive(Element element, ObjectInstance data) {
        return isElementVisibilityStateActive(element);
    }


    private boolean isElementVisibilityStateActive(Element element) {
        LOGGER.debug("Comparing element visibility to visibility state defined as {}", visibilityState);
        switch (visibilityState) {
            case VISIBLE:
                boolean visible = element.isVisible();
                LOGGER.debug("Element is {}: state is {}", visible ? "visible" : "invisible", visible ? "active" : "not active");
                return visible;
            case INVISIBLE:
                boolean invisible = element.isInvisible();
                LOGGER.debug("Element is {}: state is {}", invisible ? "invisible" : "visible", invisible ? "active" : "not active");
                return invisible;
            case UNDEFINED:
                LOGGER.debug("Undefined visibility state: always active");
                return true;
            default:
                throw new IllegalStateException("Cannot check visibility state for " + visibilityState);
        }
    }
}
