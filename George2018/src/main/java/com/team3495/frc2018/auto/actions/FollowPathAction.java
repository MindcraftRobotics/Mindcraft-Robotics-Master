package com.team3495.frc2018.auto.actions;

import com.team3495.frc2018.auto.paths.Path;
import com.team3495.frc2018.controlsystem.RoboSystem;
import com.team3495.frc2018.subsystems.Drivetrain;

public class FollowPathAction implements Action {
    private Path path;
    private RoboSystem robosystem;

    public FollowPathAction(Path path) {
        this.path = path;
        this.robosystem = RoboSystem.getInstance();
    }

    public void start()
    {
        robosystem.drivetrain.beginPathFollowing(Drivetrain.State.ENCODER_FOLLOWER, this.path);
    }
    public void update(){
        robosystem.drivetrain.update();
    }
    public void done(){}
    public boolean isFinished(){
        return false;
    }
}