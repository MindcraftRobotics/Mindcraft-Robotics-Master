package com.team3495.frc2018.auto.actions;

public interface Action {
    public abstract void start();
    public abstract void update();
    public abstract void done();
    public abstract boolean isFinished();
}
