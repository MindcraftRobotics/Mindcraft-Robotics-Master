/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  

  TalonSRX shooterTalon = new TalonSRX(2);
  TalonSRX succTalon = new TalonSRX(5);
  Joystick left = new Joystick(0);
  Joystick right = new Joystick(1);
  //Joystick controller = new Joystick(2);
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    succTalon.configPeakOutputForward(.75);
    succTalon.configPeakOutputReverse(-.75);
    succTalon.setNeutralMode(NeutralMode.Coast);
   // m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
   /// m_chooser.addOption("My Auto", kCustomAuto);
    //SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  



  if (left.getRawButton(2)) {
    succTalon.set(ControlMode.PercentOutput, -.75);
  }else if(left.getRawButton(1)){
    succTalon.set(ControlMode.PercentOutput, -.75);
    shooterTalon.set(ControlMode.PercentOutput, 1);
  }else {
    succTalon.set(ControlMode.PercentOutput, 0);
    shooterTalon.set(ControlMode.PercentOutput, 0);
  }

 
  
  
 
  }
  
  @Override
  public void autonomousInit() {
   // m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    //System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    ////switch (m_autoSelected) {
     // case kCustomAuto:
        // Put custom auto code here
      ////  break;
      ///case kDefaultAuto:
      //default:
        // Put default auto code here
        //break;
    }
  

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
   
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
