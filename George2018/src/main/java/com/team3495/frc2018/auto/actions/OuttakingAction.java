package com.team3495.frc2018.auto.actions;

import com.team3495.frc2018.controlsystem.RoboSystem;
import com.team3495.frc2018.subsystems.Intake;

public class OuttakingAction extends RunOnceAction {
    private RoboSystem robosystem;

    public OuttakingAction() {
        this.robosystem = RoboSystem.getInstance();
    }

    @Override
    public void runOnce() {
        robosystem.intake.requestState(Intake.RollerState.OUTTAKING);
    }
}