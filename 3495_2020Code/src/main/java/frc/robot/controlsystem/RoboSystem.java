package frc.robot.controlsystem;

import frc.robot.subsystem.Drivetrain;
import frc.robot.subsystem.ColorWheel;
import frc.robot.subsystem.NavX;
import frc.robot.subsystem.Lift;


public class RoboSystem {

    public Drivetrain drivetrain;
    public ColorWheel colorwheel;
    public NavX navx;
    public Lift lift;


    private RoboSystem() {
        drivetrain = Drivetrain.getInstance();
        colorwheel = ColorWheel.getInstance();
        navx = NavX.getInstance();
        lift = Lift.getInstance();


    }

    private static RoboSystem instance = null;

    public static RoboSystem getInstance() {
        if(instance == null) instance = new RoboSystem();
        return instance;
    }
}