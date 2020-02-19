/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.controlsystem.RoboSystem;
import frc.robot.controlsystem.TeleThreeJoysticks;
//import frc.robot.subsystem.Limelight;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.networktables.*;

import frc.robot.subsystem.Drivetrain;
//import frc.robot.subsystem.Limelight;
import frc.robot.subsystem.NavX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private TeleThreeJoysticks teleControllers;
  private Compressor compressor;
  private RoboSystem robosystem;
  private boolean valueHit;
  Servo servo = new Servo(0);
  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tv = table.getEntry("tv");
  NetworkTableEntry ledMode = table.getEntry("ledMode");
  CameraServer cameraserver;
  UsbCamera camera;
  private boolean finishedDistance = false;



  
 

  
  
    

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    teleControllers = TeleThreeJoysticks.getInstance();
    robosystem = RoboSystem.getInstance();
    CameraServer.getInstance().startAutomaticCapture();

    //pauloGarcia = AutoModeExecutor.getInstance();
    
    //Limelight.setPipeline(0);
    robosystem.drivetrain.zeroSensors();
    ledMode.setValue(1);
    robosystem.colorwheel.resetRotations();
    robosystem.colorwheel.equalColorStringLast();
    compressor = new Compressor(0);
    
    //kaceyPitcher = PathManager.getInstance();
  }


  @Override
  public void robotPeriodic() {
   // robosystem.drivetrain.odometry.Update();
   //NavX.writeToDash();
   SmartDashboard.putNumber("axis", teleControllers.driverRight.getY());

   //System.out.println(Limelight.getHeight());
   //SmartDashboard.putNumber("visionbox", Limelight.getTx());
   //SmartDashboard.putNumber("Pixel Height", Limelight.getHeight());

   

  }

  @Override
  public void autonomousInit() {
    //pauloGarcia.selectAutoMode();
    //pauloGarcia.start();
    //NavX.resetAngle();
    valueHit = false;
    finishedDistance = false;
    ledMode.setValue(3);

    
    
  }

  private static boolean autoFinished = false;
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
   
    if (finishedDistance == false) {
      robosystem.drivetrain.setPower(.30, .30);
      Timer.delay(1.2);
      robosystem.drivetrain.setPower(.15, .33); // turn when on the right side of field
      Timer.delay(2.3);
      finishedDistance = true;
    }else{
   
    

    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double validTarget = tv.getDouble(0.0);
    double KpHeading = -0.0145f;
    double min_command = 0.061f;
    double left_command = 0.0;
    double right_command = 0.0;
    double KpDistance = -0.0311f;
    double desiredArea = 17.647;
    
    
      
      
      double heading_error = -x;
      double steering_adjust = 0.0f;
      double distance_error = -y;
      if (validTarget == 1) {
      
        if (x > 1.0)
        {
          steering_adjust = KpHeading*heading_error - min_command;
        }
        else if (x < 1.0)
        {
          steering_adjust = KpHeading*heading_error + min_command;
        
        }
      
      double distance_adjust = KpDistance * (desiredArea - area); //distance_error for y offset
      robosystem.drivetrain.setPower(left_command -= steering_adjust + distance_adjust, right_command += steering_adjust - distance_adjust);
    }else {
      robosystem.drivetrain.setPower(0, 0);
    }
  
    }
    
  }

     
  

  

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    robosystem.colorwheel.setPower(teleControllers.coDriver.getY());
    robosystem.colorwheel.readColor();
    //robosystem.colorwheel.countRotations();
    //double current = compressor.getCompressorCurrent();
    teleControllers.update();
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double validTarget = tv.getDouble(0.0);
    double KpHeading = -0.0145f;
    double min_command = 0.061f;
    double left_command = 0.085; 
    double right_command = 0.085; 
    double KpDistance = -0.0341f;
    double desiredArea = 14.647;
    ledMode.setValue(3);
    if (teleControllers.driverLeft.getRawButton(1)) {
      
      
      double heading_error = -x;
      double steering_adjust = 0.0f;
      double distance_error = -y;
      if (validTarget == 1) {
      
      

      if (x > 1.0)
      {
        steering_adjust = KpHeading*heading_error - min_command;
      }
      else if (x < 1.0)
      {
        steering_adjust = KpHeading*heading_error + min_command;
      
      }
      
      double distance_adjust = KpDistance * (desiredArea - area); //distance_error for y offset
      robosystem.drivetrain.setPower(left_command -= steering_adjust - distance_adjust, right_command += steering_adjust + distance_adjust);
    }else {
      robosystem.drivetrain.setPower(0, 0);
    }
  
    }
   
  }

 
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }


  
    
  



}
  








