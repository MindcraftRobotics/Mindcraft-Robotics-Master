package frc.robot.controlsystem;

import frc.robot.subsystem.Drivetrain;
import frc.robot.subsystem.ColorWheel;


public class RoboSystem {

    public Drivetrain drivetrain;
    public ColorWheel colorwheel;


    private RoboSystem() {
        drivetrain = Drivetrain.getInstance();
        colorwheel = ColorWheel.getInstance();

    }

    private static RoboSystem instance = null;

    public static RoboSystem getInstance() {
        if(instance == null) instance = new RoboSystem();
        return instance;
    }
}