package com.team3495.frc2018.controlsystem;

import com.team3495.frc2018.controlsystem.RoboSystem;
import com.team3495.frc2018.subsystems.Arm;
import com.team3495.frc2018.subsystems.Climber;
import com.team3495.frc2018.subsystems.Intake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.team3495.frc2018.Ports;
import com.team3495.frc2018.Util;
import com.team3495.frc2018.Constants;

public class TeleThreeJoysticks
{
    public RoboSystem robosystem;
    public Joystick driverLeft;
    public Joystick driverRight;
    public Joystick coDriver;

    private TeleThreeJoysticks()
    {
        robosystem = RoboSystem.getInstance();
        driverLeft = new Joystick(Ports.JOYSTICK_LEFT);
        driverRight = new Joystick(Ports.JOYSTICK_RIGHT);
        coDriver = new Joystick(Ports.JOYSTICK_CODRIVER);
    } 
    private void driver()
    {
        double left = Util.deadbandAndBound(-driverLeft.getRawAxis(Constants.ControlAxes.yAxis),
        Constants.TankDriver.Deadbands.Left.kMinReverse, 
        Constants.TankDriver.Deadbands.Left.kMinForward,
        -1.0, 1.0);
        double right = Util.deadbandAndBound(-driverRight.getRawAxis(Constants.ControlAxes.yAxis),
        Constants.TankDriver.Deadbands.Right.kMinReverse, 
        Constants.TankDriver.Deadbands.Right.kMinForward,
        -1.0, 1.0);

        robosystem.drivetrain.sendInputNormalized(left, right);

        
   

   
    }
    private void coDriver()
    {
        if(coDriver.getRawButton(Constants.JoystickCodriver.Buttons.intakeIn))
        {
            robosystem.intake.requestState(Intake.RollerState.INTAKING);
        }else if(coDriver.getRawButton(Constants.JoystickCodriver.Buttons.intakeOut))
        {
            robosystem.intake.requestState(Intake.RollerState.OUTTAKING);
        }else
        {
            robosystem.intake.requestState(Intake.RollerState.IDLE);
        }

        if(coDriver.getRawButton(Constants.JoystickCodriver.Buttons.armRaise))
        {
            robosystem.arm.requestState(Arm.State.GOING_UP);
        }else if (coDriver.getRawButton(Constants.JoystickCodriver.Buttons.armLower))
        {
            robosystem.arm.requestState(Arm.State.GOING_DOWN);
        }else {
            robosystem.arm.requestState(Arm.State.HOLDING);
        }
        if (driverRight.getRawButton(Constants.JoystickCodriver.Buttons.intakeOpen))
        {
            robosystem.intake.openIntake();
        }else{
            robosystem.intake.closeIntake();
        }
        if (coDriver.getRawButton(Constants.JoystickCodriver.Buttons.climber))
        {
            robosystem.climber.requestState(Climber.State.GOING_UP);
        }else
        {
            robosystem.climber.requestState(Climber.State.IDLE);
        }
    }

    
    
    public void update()
    {
        driver();
        coDriver();
    }

    private static TeleThreeJoysticks instance = null;

    public static TeleThreeJoysticks getInstance() {
        if(instance == null) instance = new TeleThreeJoysticks();
        return instance;

}
}