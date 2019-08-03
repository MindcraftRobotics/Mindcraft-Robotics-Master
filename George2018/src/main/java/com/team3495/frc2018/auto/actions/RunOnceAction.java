package com.team3495.frc2018.auto.actions;

public abstract class RunOnceAction implements Action{
    @Override
    public void start() { runOnce(); }

    @Override
    public void update() {}

    @Override
    public void done() {}

    @Override
    public boolean isFinished() { return true; }

    public abstract void runOnce();
}
