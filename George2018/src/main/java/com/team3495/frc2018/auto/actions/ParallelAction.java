package com.team3495.frc2018.auto.actions;

import java.util.ArrayList;
import java.util.List;

public class ParallelAction implements Action {
    private ArrayList<Action> actions;

    public ParallelAction(List<Action> actions) {
        this.actions = new ArrayList<>(actions.size());
        for(Action action : actions) {
            this.actions.add(action);
        }
    }

    @Override
    public void start() {
        for(Action action : actions) {
            action.start();
        }
    }

    @Override
    public void update() {
        for(Action action : actions) {
            action.update();
        }
    }

    @Override
    public void done() {
        for(Action action : actions) {
            action.done();
        }
    }

    @Override
    public boolean isFinished() {
        for(Action action : actions) {
            if(!action.isFinished()) return false;
        }
        return true;
    }
}
