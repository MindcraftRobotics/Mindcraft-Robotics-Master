package com.team3495.frc2018.auto.actions;

import com.team3495.frc2018.controlsystem.RoboSystem;
import com.team3495.frc2018.subsystems.Arm;

public class RaiseArmAction extends RunOnceAction {
    private RoboSystem robosystem;

    public RaiseArmAction() {
        this.robosystem = RoboSystem.getInstance();
    }

    @Override
    public void runOnce() {
        robosystem.arm.requestState(Arm.State.GOING_UP);
    }
}
