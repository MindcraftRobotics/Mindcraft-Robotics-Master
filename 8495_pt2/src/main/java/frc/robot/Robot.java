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

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.networktables.*;
import jaci.pathfinder.*;
import jaci.pathfinder.modifiers.TankModifier;
import frc.robot.subsystem.Drivetrain;
import jaci.pathfinder.followers.EncoderFollower;
//import frc.robot.subsystem.Limelight;

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
  private 
  boolean valueHit;
  Servo servo = new Servo(0);
  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry ledMode = table.getEntry("ledMode");
  EncoderFollower left;
  EncoderFollower right;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    teleControllers = TeleThreeJoysticks.getInstance();
    robosystem = RoboSystem.getInstance();
    //pauloGarcia = AutoModeExecutor.getInstance();
    CameraServer.getInstance().startAutomaticCapture();
    //Limelight.setPipeline(0);
    robosystem.drivetrain.zeroSensors();
    ledMode.setNumber(1);

    
    Waypoint[] points = new Waypoint[] {
      new Waypoint(-4, -1, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
      new Waypoint(-2, -2, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
      new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
    };
    Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
    Trajectory trajectory = Pathfinder.generate(points, config);
    TankModifier modifier = new TankModifier(trajectory).modify(0.5);

    left = new EncoderFollower(modifier.getLeftTrajectory());
    right = new EncoderFollower(modifier.getRightTrajectory());

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
    
  }

  private static boolean autoFinished = false;
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
     // findLeftTarget();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //double current = compressor.getCompressorCurrent();
    ledMode.setNumber(1);
    left.configureEncoder(0, 2048, 6.0);
    right.configureEncoder(0, 2048, 6.0);
    //left.configurePIDVA(1.0, 0, 0, 1 / max_velocity, 0);
    //right.configurePIDVA(1.0, 0.0, 0.0, 1.0 / max_velocity, 0);
    double l = left.calculate((int)robosystem.drivetrain.getPostition());
    double r = right.calculate((int)robosystem.drivetrain.getPostition());
    
    teleControllers.update();
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double KpHeading = -0.0235f;
    double min_command = 0.05f;
    double left_command = 0.21;
    double right_command = 0.21;
    double KpDistance = -0.04f;
    if (teleControllers.driverLeft.getRawButton(1)) {
      ledMode.setNumber(3);
      double heading_error = -x;
      double steering_adjust = 0.0f;
      double distance_error = -y;
      
      if (x > 1.0)
      {
              steering_adjust = KpHeading*heading_error - min_command;
      }
      else if (x < 1.0)
      {
              steering_adjust = KpHeading*heading_error + min_command;
      
      }
      double distance_adjust = KpDistance * distance_error;
      robosystem.drivetrain.setPower(left_command -= steering_adjust - distance_adjust, right_command += steering_adjust + distance_adjust);
    }
  }
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
