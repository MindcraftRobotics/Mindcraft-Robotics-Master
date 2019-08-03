package com.team3495.frc2018.auto.modes;

import com.team3495.frc2018.auto.actions.Action;

public abstract class AutoModeBase {
    private boolean isActive = false;

    public abstract void routine();
    // this wraps routine() because later we might add crashey things and we'd put a try-catch in here
    public void run() {
        isActive = true;
        routine();
    }
    public void runAction(Action action) {
        action.start();
        while(!action.isFinished()) action.update();
        action.done();
    }
    public void done() {}
    public void stop() { isActive = false; }
    public boolean isActive() { return isActive; }
}
