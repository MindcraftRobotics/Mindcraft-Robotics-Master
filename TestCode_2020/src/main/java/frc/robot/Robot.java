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

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorSensorV3.RawColor;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  

  TalonSRX shooterTalon = new TalonSRX(5);
  TalonSRX succTalon = new TalonSRX(2);
  TalonSRX colorWheel = new TalonSRX(3);
  Joystick left = new Joystick(1);
  Joystick right = new Joystick(0);
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  //Joystick controller = new Joystick(2);

  /*
  private final Color kBlueTarget = ColorMatch.makeColor(0.176, 0.2772, 0.5469);
  private final Color kGreenTarget = ColorMatch.makeColor(0.2525, 0.4843, 0.08933);
  private final Color kRedTarget = ColorMatch.makeColor(0.63333, 0.2192, 0.148);
  private final Color kYellowTarget = ColorMatch.makeColor(0.4272, 0.4835, 0.08833);
  private final Color kFellowTarget = ColorMatch.makeColor(0.346, 0.328, 0.227);
  private final Color kFreenTarget = ColorMatch.makeColor(0.240, .371, .389);
  private final ColorMatch m_colorMatcher = new ColorMatch();
  String colorString;
  String selectedColor;
  String colorStringLast;
  String desiredColor;
  String expectedLeftColor;
  String expectedRightColor;
  private double rotations;
  private double transitions;
  private int red;
  private int green;
  private int blue;
  private double redPercentageNormalized;
  private double bluePercentageNormalized;
  private double greenPercentageNormalized;
  TalonSRX colorSpinner;
  String[] colorLog;
  //Encoder wheelEncoder;
  */
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    succTalon.configPeakOutputForward(1);
    succTalon.configPeakOutputReverse(-1);
    succTalon.setNeutralMode(NeutralMode.Coast);
    /*
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    m_colorMatcher.addColorMatch(kFellowTarget);
    m_colorMatcher.addColorMatch(kFreenTarget);
    //wheelEncoder = new Encoder(0,1);
    colorSpinner.setNeutralMode(NeutralMode.Brake);
    colorSpinner.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
    */
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
    succTalon.set(ControlMode.PercentOutput, -.50);
  }else if(left.getRawButton(1)){
    succTalon.set(ControlMode.PercentOutput, -.50);
    shooterTalon.set(ControlMode.PercentOutput, 1);
  }else {
    succTalon.set(ControlMode.PercentOutput, 0);
    shooterTalon.set(ControlMode.PercentOutput, 0);
  }

  if (right.getRawButton(2)) {
      colorWheel.set(ControlMode.PercentOutput, 1);
    
}else{
  colorWheel.set(ControlMode.PercentOutput, 0);
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
