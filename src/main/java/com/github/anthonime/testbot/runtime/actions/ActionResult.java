package com.github.anthonime.testbot.runtime.actions;

import com.github.anthonime.testbot.definitions.actions.ActionDefinition;

/**
 * Created by schio on 9/16/2017.
 */
public class ActionResult {

    private ActionDefinition definition;
    private ActionResultType type;
    private ActionFailureReason reason;

    public ActionResult(ActionDefinition definition, ActionResultType type, ActionFailureReason reason) {
        this.definition = definition;
        this.type = type;
        this.reason = reason;
    }

    public ActionDefinition getDefinition() {
        return definition;
    }

    public ActionResultType getType() {
        return type;
    }

    public ActionFailureReason getReason() {
        return reason;
    }
}
