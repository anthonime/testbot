package com.github.anthonime.testbot.definitions.actions;

import com.github.anthonime.testbot.definitions.elements.ElementIdentifier;

import java.time.Duration;

/**
 * Created by schio on 9/10/2017.
 */
public interface ActionDefinition {

    Verb getVerb();

    ElementIdentifier getTarget();

    Duration getDuration();


    //source
    //modifier keys/dead/lock/nav/editingcontextual/
    //double click
    //context click

    //type
    //keyDown/keyUp

}
