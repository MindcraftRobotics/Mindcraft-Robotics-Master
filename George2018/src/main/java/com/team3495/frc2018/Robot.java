/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team3495.frc2018;

import com.team3495.frc2018.auto.AutoModeExecutor;
import com.team3495.frc2018.auto.paths.PathManager;
import com.team3495.frc2018.controlsystem.RoboSystem;
import com.team3495.frc2018.controlsystem.TeleThreeJoysticks;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {
  private TeleThreeJoysticks teleControllers;
  private Compressor compressor;
  private RoboSystem robosystem;
  private AutoModeExecutor pauloGarcia;
  private PathManager kaceyPitcher;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    teleControllers = TeleThreeJoysticks.getInstance();
    robosystem = RoboSystem.getInstance();
    pauloGarcia = AutoModeExecutor.getInstance();
    CameraServer.getInstance().startAutomaticCapture();
    
    compressor = new Compressor(0);
    kaceyPitcher = PathManager.getInstance();
  }

  @Override
  public void robotPeriodic() {
    robosystem.drivetrain.odometry.Update();
  }

  @Override
  public void autonomousInit() {
    pauloGarcia.selectAutoMode();
    pauloGarcia.start();
  }

  private static boolean autoFinished = false;
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    teleControllers.update();
  
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
