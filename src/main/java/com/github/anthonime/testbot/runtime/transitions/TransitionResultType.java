package com.github.anthonime.testbot.runtime.transitions;

/**
 * Created by schio on 9/18/2017.
 */
public enum TransitionResultType {

    /**
     * The transition was not started because
     * one or several source states were not active.
     */
    NOT_STARTED,

    /**
     * The transition started but some action failed
     * in between, so the transition has aborted.
     */
    ABORTED,

    /**
     * The actions has been successfully performed,
     * but the target state has not been reached.
     */
    NOT_EFFECTIVE,

    /**
     * The transition was successful.
     */
    SUCCESS

}
