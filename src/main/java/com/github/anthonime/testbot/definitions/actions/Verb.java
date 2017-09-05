package com.github.anthonime.testbot.definitions.actions;

/**
 * Created by schio on 9/14/2017.
 */
public enum Verb {
    //KEYBOARD VERBS
    //keyDown [elmt] string/keys
    //keyUp [elmt] string/keys
    //sendKeys [elmt] string/keys
    type,
    sendKeys,
    keyDown,
    keyUp,


    //MOUSE VERBS
    click,
    //dragndrop
    //move from element to element
    //move by coords

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

    pause,
}
