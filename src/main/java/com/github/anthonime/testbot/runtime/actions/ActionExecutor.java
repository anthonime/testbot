package com.github.anthonime.testbot.runtime.actions;

import com.github.anthonime.testbot.definitions.actions.ActionDefinition;
import com.github.anthonime.testbot.definitions.actions.ActionDefinitionList;

/**
 * Created by schio on 9/14/2017.
 */
public interface ActionExecutor {
    ActionResult execute(ActionDefinition action);

    ActionListResult execute(ActionDefinitionList action);
}
