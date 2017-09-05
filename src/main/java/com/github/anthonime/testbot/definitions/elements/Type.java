package com.github.anthonime.testbot.definitions.elements;

/**
 * Created by schio on 9/12/2017.
 */
public enum Type {

    /**
     * An element where you can only read text
     */
    TEXT,
    /**
     * A container of children elements
     */
    CONTAINER,
    /**
     * A graphical element you can click in
     */
    BUTTON,

    /**
     * An element the user can input data.
     * NOTE: should be abstract?
     */
    INPUT
}
