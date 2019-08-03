package com.team3495.frc2018.auto.actions;

import com.team3495.frc2018.subsystems.Intake;

public class OpenIntakeAction extends RunOnceAction {
    @Override
    public void runOnce() {
        Intake.getInstance().requestState(Intake.PistonState.OPEN);
    }
}
