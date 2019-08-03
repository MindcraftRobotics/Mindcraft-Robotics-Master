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
public class Front_Intake {
    public TalonSRX cargo;
    public TalonSRX wrist;
    public Solenoid front_one;
    public Solenoid front_two;


    private Front_Intake() {
        cargo = new TalonSRX(Ports.FRONT_INTAKE_CARGO);
        wrist = new TalonSRX(Ports.FRONT_INTAKE_WRIST);
        front_one = new Solenoid(0);
        front_two = new Solenoid(1);
    }

    private static Front_Intake instance = null;

    public static Front_Intake getInstance() {
        if(instance == null) instance = new Front_Intake();
        return instance;
    }

    public void setCargoPower(double power)
    {
     cargo.set(ControlMode.PercentOutput, power);
    }
    public void setWristPower(double power)
    {
     wrist.set(ControlMode.PercentOutput, power); 
    }
    public void setSolenoids() {
        front_one.set(true);
        front_two.set(true);
    }

}
