package com.github.anthonime.testbot.runtime.actions;

import com.github.anthonime.testbot.definitions.actions.ActionDefinitionList;

import java.util.List;

/**
 * Created by schio on 9/17/2017.
 */
public class ActionListResult {

    private ActionDefinitionList actionList;
    private List<ActionResult> results;

    public ActionListResult(ActionDefinitionList actionList, List<ActionResult> results) {
        this.actionList = actionList;
        this.results = results;
    }

    public ActionDefinitionList getActionList() {
        return actionList;
    }

    public List<ActionResult> getResults() {
        return results;
    }

    public boolean isSuccess() {
        return getResults().stream().allMatch(r -> r.getType() == ActionResultType.SUCCESS);
    }
}
