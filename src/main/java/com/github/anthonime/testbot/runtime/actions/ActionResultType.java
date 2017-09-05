package com.github.anthonime.testbot.runtime.actions;

/**
 * Created by schio on 9/16/2017.
 */
public enum ActionResultType {
    /**
     * The action has been successfully done
     */
    SUCCESS,
    /**
     * The action has failed
     */
    FAILURE,

    /**
     * The action has not be performed,
     * because of the failure of a previous action.
     */
    SKIPPED
}
