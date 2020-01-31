/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.controlsystem.RoboSystem;
import frc.robot.controlsystem.TeleThreeJoysticks;
import frc.robot.subsystems.Limelight;
//import frc.robot.subsystems.NavX;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;



import frc.robot.subsystems.Drivetrain;
//import com.kauailabs.navx.frc.*;
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
  boolean valueHit;
  Servo servo = new Servo(0);



  
 

  
  
    

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
    Limelight.setPipeline(0);
    robosystem.drivetrain.zeroSensors();
    
   
    


    
    compressor = new Compressor(0);
    
    //kaceyPitcher = PathManager.getInstance();
  }

  @Override
  public void robotPeriodic() {
   // robosystem.drivetrain.odometry.Update();
   //NavX.writeToDash();
   SmartDashboard.putNumber("axis", teleControllers.driverRight.getY());
   //System.out.println(Limelight.getHeight());
   SmartDashboard.putNumber("visionbox", Limelight.getTx());
   SmartDashboard.putNumber("Pixel Height", Limelight.getHeight());

   

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
    double current = compressor.getCompressorCurrent();
    teleControllers.update();
    if (teleControllers.driverRight.getTrigger()) {
      robosystem.drivetrain.limelightTurning(5, 5);
    }else if(teleControllers.driverRight.getTriggerReleased()) {
      Limelight.setPipeline(0);
    }
    /*if (teleControllers.driverLeft.getRawButton(6))
    {
      servo.setSpeed(2.4);//2.4 is forward max, 0.6 stop, 0.25 full reverse max
    }else
    {
      servo.setSpeed(0.6);
    }(/)

    System.out.println(current);
    /*if(teleControllers.driverRight.getRawButton(12))
    {
      Limelight.blink();
    }*/

    if(robosystem.back_Intake.getLimit()) //automatic ground intake wrist
    {
      //timer.delay(1);
      //piston1.true
      //piston2.true
      robosystem.back_Intake.setGroundWristPower(1.0);
      //timer.delay(1.3);
      robosystem.back_Intake.setGroundWristPower(0.0);
    }
  
   
  }
    

  
  

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }


 

public void findCloseUp() {
  double rightVolts = 0.10;
  double leftVolts = 0.10;
  double voltAmp = Limelight.getAmp();
  double aim_error = Limelight.getTx(); //add in
  double heading_error = Limelight.getTx();
  double steering_adjust = Constants.Drivetrain.Kp*aim_error;
  double KpAim = -0.1f;
  double min_aim_command = 0.05f;
  double distance_error = Limelight.getTy();
  double KpDistance = -0.1f;
  double distance_adjust = KpDistance * distance_error;


  Limelight.setPipeline(1);

  if(Limelight.isTarget() == true) {
      if(Limelight.getTx() > 1.0){
        robosystem.drivetrain.setPower((leftVolts -= steering_adjust-Constants.Drivetrain.min_command + distance_adjust) , ( rightVolts += steering_adjust+Constants.Drivetrain.min_command  - distance_adjust) );
      }else if(Limelight.getTx() < 1.0){
        robosystem.drivetrain.setPower((leftVolts -= steering_adjust-Constants.Drivetrain.min_command + distance_adjust) , ( rightVolts += steering_adjust+Constants.Drivetrain.min_command - distance_adjust) );
      }else if(Limelight.getTx() == 0.0){
        robosystem.drivetrain.setPower(leftVolts, rightVolts);
      }
    }

}
}






