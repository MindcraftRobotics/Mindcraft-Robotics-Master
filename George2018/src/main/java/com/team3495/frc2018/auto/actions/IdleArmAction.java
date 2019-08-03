package com.team3495.frc2018.auto.actions;

import com.team3495.frc2018.controlsystem.RoboSystem;
import com.team3495.frc2018.subsystems.Arm;

public class IdleArmAction extends RunOnceAction {
    private RoboSystem robosystem;

    public IdleArmAction() {
        this.robosystem = RoboSystem.getInstance();
    }

    @Override
    public void runOnce() {
        robosystem.arm.requestState(Arm.State.HOLDING);
    }
}
