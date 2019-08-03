package frc.robot.controlsystem;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Front_Intake;
import main.java.frc.robot.subsystems.Back_Intake;

public class RoboSystem {

    public Drivetrain drivetrain;
    public Elevator elevator;
    public Front_Intake front_intake;
    public Back_Intake back_Intake;

    private RoboSystem() {
        drivetrain = Drivetrain.getInstance();
        elevator = Elevator.getInstance();
        front_intake = Front_Intake.getInstance();
        back_Intake = Back_Intake.getInstance();
    }

    private static RoboSystem instance = null;

    public static RoboSystem getInstance() {
        if(instance == null) instance = new RoboSystem();
        return instance;
    }
}