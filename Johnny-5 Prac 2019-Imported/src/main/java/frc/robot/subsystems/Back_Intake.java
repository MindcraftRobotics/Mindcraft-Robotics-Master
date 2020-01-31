/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.controlsystem.TeleThreeJoysticks;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controlsystem.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Add your docs here.
 */
public class Back_Intake {
    public DigitalInput frontLimit1;
    //public DigitalInput frontLimit2;
    public TalonSRX roller1;
    public TalonSRX roller2;
    public TalonSRX groundWrist;
    public Solenoid back_one;


    private Back_Intake() {
        frontLimit1 = new DigitalInput(0);
        //frontLimit2 = new DigitalInput(1);
        roller1 = new TalonSRX(Ports.GROUND_INTAKE_LEFT);
        roller2 = new TalonSRX(Ports.GROUND_INTAKE_RIGHT);
        groundWrist = new TalonSRX(Ports.GROUND_INTAKE_WRIST);
        back_one = new Solenoid(2);
        //piston2 = new Solenoid();
    }

    private static Back_Intake instance = null;

    public static Back_Intake getInstance() {
        if(instance == null) instance = new Back_Intake();
        return instance;
    }
    public void setGroundWristPower(double power)
    {
     groundWrist.set(ControlMode.PercentOutput, power); 
    }
    public void setRollerPower(double power)
    {
     roller1.set(ControlMode.PercentOutput, -power);
     roller2.set(ControlMode.PercentOutput, power);
    }

    public boolean getLimit()
    {
        if(frontLimit1.get())
        {
            return true;
            //System.out.println("YEEEET");
        }
        return false;
    }

    public void setBackSolenoid() {
        back_one.set(true);
    }

}
