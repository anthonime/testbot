package com.github.anthonime.testbot.runtime.applications;

import com.github.anthonime.testbot.definitions.actions.ActionDefinition;
import com.github.anthonime.testbot.definitions.actions.ActionDefinitionList;
import com.github.anthonime.testbot.definitions.applications.EnvironmentDefinition;
import com.github.anthonime.testbot.definitions.transitions.TransitionDefinition;
import com.github.anthonime.testbot.runtime.actions.ActionListResult;
import com.github.anthonime.testbot.runtime.actions.ActionResult;
import com.github.anthonime.testbot.runtime.states.State;

import java.util.List;

/**
 * Created by schio on 9/16/2017.
 */
public interface Application {

    /**
     * Return true if the current web page context
     * is currently in the host/port of the application.
     *
     * @return
     */
    boolean isOpen();

    /**
     * Return true if the current web page context
     * is currently in the host/port of the given named application environment.
     *
     * @return
     */
    boolean isOpen(String environment);


    /**
     * Return the active environment or null if the application is not open.
     *
     * @return
     */
    EnvironmentDefinition getActiveEnvironment();

    /**
     * Set the web page context into this application
     * at the given environment name, if not already in.
     */
    void navigateTo(String environment);

    /**
     * Navigate to the main environment of this application,
     * which is an environment with the "main" identifier
     * or navigate to the only environment if the application
     * has only one environment.
     */
    void open();

    /**
     * Return all the states that are actives.
     *
     * @return
     */
    List<State> getActiveStates(ApplicationContext context);

    /**
     * Perform the given action.
     *
     * @param action
     * @return
     */
    ActionResult performAction(ActionDefinition action);

    /**
     * Perform the given action.
     *
     * @param actionList the actions to perform
     * @return
     */
    ActionListResult performActions(ActionDefinitionList actionList);

    /**
     * Operate the given transition.
     * Will fail if the current application state does not match
     * the source state of the given transition.
     * <p>
     *
     * @param transitionDefinition
     */
    void operateTransition(TransitionDefinition transitionDefinition);
}
