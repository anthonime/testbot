package com.github.anthonime.testbot.definitions.actions;

import java.util.Arrays;
import java.util.List;

/**
 * Created by schio on 9/17/2017.
 */
public class ActionDefinitionList {
    private List<ActionDefinition> actions;

    public ActionDefinitionList(ActionDefinition... actions) {
        this.actions = Arrays.asList(actions);
    }

    public ActionDefinitionList(List<ActionDefinition> actions) {
        this.actions = actions;
    }

    public List<ActionDefinition> getActions() {
        return actions;
    }
}
