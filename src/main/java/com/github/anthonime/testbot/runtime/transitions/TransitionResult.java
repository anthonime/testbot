package com.github.anthonime.testbot.runtime.transitions;

import com.github.anthonime.testbot.definitions.states.StateIdentifier;
import com.github.anthonime.testbot.runtime.actions.ActionListResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schio on 9/18/2017.
 */
public class TransitionResult {

    private TransitionResultType resultType;
    private List<StateIdentifier> inactiveSourceStates;
    private ActionListResult actionListResult;

    public TransitionResult(TransitionResultType resultType, List<StateIdentifier> inactiveSourceStates, ActionListResult actionListResult) {
        this.resultType = resultType;
        this.inactiveSourceStates = inactiveSourceStates;
        this.actionListResult = actionListResult;
    }

    public static TransitionResult notStarted(List<StateIdentifier> inactiveStates) {
        return new TransitionResult(TransitionResultType.NOT_STARTED, inactiveStates, null);
    }


    public static TransitionResult aborted(ActionListResult actionListResult) {
        return new TransitionResult(TransitionResultType.ABORTED, new ArrayList<>(), actionListResult);
    }

    public static TransitionResult notEffective(ActionListResult actionListResult) {
        return new TransitionResult(TransitionResultType.NOT_EFFECTIVE, new ArrayList<>(), actionListResult);
    }

    public static TransitionResult success(ActionListResult actionListResult) {
        return new TransitionResult(TransitionResultType.SUCCESS, new ArrayList<>(), actionListResult);
    }

    public TransitionResultType getResultType() {
        return resultType;
    }

    public List<StateIdentifier> getInactiveSourceStates() {
        return inactiveSourceStates;
    }

    public ActionListResult getActionListResult() {
        return actionListResult;
    }

    public boolean isSuccess() {
        return resultType == TransitionResultType.SUCCESS;
    }
}
