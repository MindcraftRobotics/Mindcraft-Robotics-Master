package com.team3495.frc2018.auto.actions;

import com.team3495.frc2018.controlsystem.RoboSystem;

public class VoltageDriveAction extends RunOnceAction {
    private double left, right;
    private RoboSystem robosystem;

    public VoltageDriveAction(double left, double right) {
        this.left = left;
        this.right = right;
        this.robosystem = RoboSystem.getInstance();
    }

    @Override
    public void runOnce() {
        robosystem.drivetrain.sendInputVolts(left, right);
    }
}
