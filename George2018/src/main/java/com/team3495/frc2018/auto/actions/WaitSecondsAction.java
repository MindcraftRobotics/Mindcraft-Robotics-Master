package com.team3495.frc2018.auto.actions;

import edu.wpi.first.wpilibj.Timer;

public class WaitSecondsAction implements Action {
    private double duration;
    private double initial;

    public WaitSecondsAction(double duration) { this.duration = duration; }

    @Override
    public void start() { initial = Timer.getFPGATimestamp(); }

    @Override
    public void update() {}

    @Override
    public void done() {}

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() - initial >= duration;
    }
}
