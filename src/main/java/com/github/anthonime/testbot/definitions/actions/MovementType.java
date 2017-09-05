package com.github.anthonime.testbot.definitions.actions;

/**
 * Created by schio on 9/16/2017.
 */
public enum MovementType {
    /**
     * Hold a mouse button or a keyboard key
     */
    HOLD,
    /**
     * Release a mouse button or a keyboard key
     * that was held.
     */
    RELEASE,

    /**
     * click a mouse button or type a keyboard key
     */
    HOLD_AND_RELEASE,

    /**
     * Move the mouse
     */
    MOVE
}
