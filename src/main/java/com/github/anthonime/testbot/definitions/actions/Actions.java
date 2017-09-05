package com.github.anthonime.testbot.definitions.actions;

import com.github.anthonime.testbot.definitions.elements.HasElementIdentifier;

import java.time.Duration;

/**
 * Created by schio on 9/14/2017.
 */
public class Actions {

    public static ActionDefinition click() {
        return new AbstractActionDefinition(Verb.click, null, null, null);
    }

    public static ActionDefinition click(HasElementIdentifier element) {
        return new AbstractActionDefinition(Verb.click, element.getElementIdentifier(), null, null);
    }

    public static ActionDefinition pause(Duration duration) {
        return new AbstractActionDefinition(Verb.pause, null, null, duration);
    }

    //KEYBOARD VERBS
    //keyDown [elmt] string/keys
    //keyUp [elmt] string/keys
    //MOUSE VERBS
    //click elmt
    //dragndrop
    //move from element to element
    //move by coords

    //click elmt
    //rightclick elmt
    //click
    //rightclick
    //middleclick
    //doubleclick elmt
    //doubleclick

    //take elmt (click&hold)
    //moveBy x,y
    //moveTo x,y
    //moveTo elmt
    //moveTo elmt x,y
    //release (drop)
    //releaseOn elmt (dropOn)

    //drag elmt
    //drag elmt x,y
    //drag elmt elmt
    //dropOn elmt
    //dropAt x,y

    //dragNDrop elmt to elmt
    //dragNDrop elmt by b,y

    //HIGHLEVEL (no keyboard, no mouse)
    //pause 5s

    //any of (keyboard, mouse)
    //scroll elmt
    //scrollToVisible elmt

}
