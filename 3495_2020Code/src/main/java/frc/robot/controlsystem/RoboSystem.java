package frc.robot.controlsystem;

import frc.robot.subsystem.Drivetrain;
import frc.robot.subsystem.ColorWheel;
import frc.robot.subsystem.NavX;


public class RoboSystem {

    public Drivetrain drivetrain;
    public ColorWheel colorwheel;
    public NavX navx;


    private RoboSystem() {
        drivetrain = Drivetrain.getInstance();
        colorwheel = ColorWheel.getInstance();
        navx = NavX.getInstance();

    }

    private static RoboSystem instance = null;

    public static RoboSystem getInstance() {
        if(instance == null) instance = new RoboSystem();
        return instance;
    }
}