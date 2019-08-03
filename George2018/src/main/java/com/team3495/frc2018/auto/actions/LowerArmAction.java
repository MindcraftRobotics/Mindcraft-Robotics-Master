package com.team3495.frc2018.auto.actions;

import com.team3495.frc2018.controlsystem.RoboSystem;
import com.team3495.frc2018.subsystems.Arm;

public class LowerArmAction extends RunOnceAction {
    private RoboSystem robosystem;

    public LowerArmAction() {
        this.robosystem = RoboSystem.getInstance();
    }

    @Override
    public void runOnce() {
        robosystem.arm.requestState(Arm.State.GOING_DOWN);
    }
}
