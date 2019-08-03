package com.team3495.frc2018.auto.actions;

import java.util.ArrayList;
import java.util.List;

/**
 * Do a bunch of actions in order, one after another.
 */
public class SerialAction implements Action {
    private ArrayList<Action> actions;
    private Action current_action;

    public SerialAction(List<Action> actions) {
        this.actions = new ArrayList<>(actions.size());
        for(Action action : actions) {
            this.actions.add(action);
        }
        this.current_action = null;
    }

    @Override
    public void start() {}

    @Override
    public void update() {
        // if we don't have an action at the moment, either be finished or move on to the next, depending on whether
        // there is a next
        if(current_action == null) {
            if (actions.isEmpty()) {
                return;
            }
            // pop!
            current_action = actions.remove(0);
            current_action.start();
        }
        current_action.update();
        // if current action is finished, clean up and """delete""" it
        if(current_action.isFinished()) {
            current_action.done();
            current_action = null;
        }
    }

    @Override
    public void done() {}

    @Override
    public boolean isFinished() {
        return actions.isEmpty() && current_action == null;
    }
}
