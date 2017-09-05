package com.github.anthonime.testbot.definitions.actions;

import com.github.anthonime.testbot.definitions.elements.ElementIdentifier;

import java.time.Duration;

/**
 * Created by schio on 9/13/2017.
 */
public class AbstractActionDefinition implements ActionDefinition {
    private final Verb verb;
    private final ElementIdentifier target;
    private final Duration duration;

    public AbstractActionDefinition(Verb verb, ElementIdentifier target, ElementIdentifier source, Duration duration) {
        this.verb = verb;
        this.target = target;
        this.duration = duration;
    }

    @Override
    public Verb getVerb() {
        return verb;
    }

    @Override
    public ElementIdentifier getTarget() {
        return target;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }
}
