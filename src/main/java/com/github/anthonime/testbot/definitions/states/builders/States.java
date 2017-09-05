package com.github.anthonime.testbot.definitions.states.builders;

import com.github.anthonime.testbot.definitions.elements.ElementIdentifier;
import com.github.anthonime.testbot.definitions.elements.HasElementIdentifier;
import com.github.anthonime.testbot.definitions.states.VisibilityState;
import com.github.anthonime.testbot.definitions.states.VisibilityStateDefinition;

/**
 * Created by schio on 9/16/2017.
 */
public class States {

    public static VisibilityStateDefinition elementVisible(HasElementIdentifier element) {
        return create(element.getElementIdentifier(), VisibilityState.VISIBLE);
    }

    public static VisibilityStateDefinition elementInvisible(HasElementIdentifier element) {
        return create(element.getElementIdentifier(), VisibilityState.INVISIBLE);
    }

    public static VisibilityStateDefinition elementVisibilityUndefined(HasElementIdentifier element) {
        return create(element.getElementIdentifier(), VisibilityState.UNDEFINED);
    }

    private static VisibilityStateDefinition create(ElementIdentifier identifier, VisibilityState state) {
        return new VisibilityStateDefinition(identifier, state);
    }

}
