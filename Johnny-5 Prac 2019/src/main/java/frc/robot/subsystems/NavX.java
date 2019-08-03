/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controlsystem.RoboSystem;
import frc.robot.controlsystem.TeleThreeJoysticks;


/**
 * Add your docs here.
 */
public class NavX {
   static AHRS ahrs = new AHRS(SPI.Port.kMXP);
   PIDController turnController;
   double rotateToAngleRate;
  public static final double kP = 0.03;
  public static final double kI = 0.00;
  public static final double kD = 0.00;
  public static final double kF = 0.00;
  public static final double kToleranceDegrees = 2.0f;
  public RoboSystem robosystem;
  

        
    

    public static double getbotAngle() {
        return ahrs.getAngle();
        
    }

    public static void writeToDash() {
        SmartDashboard.putNumber("navx angle", ahrs.getAngle());
        SmartDashboard.putNumber("Pitch", ahrs.getPitch());
        SmartDashboard.putNumber("Yaw", ahrs.getYaw());
    }

    public static double getVelocityX() {
        return ahrs.getVelocityX();
    }

    public static double getVelocityY() {
        return ahrs.getVelocityY();
    }

    public static double getVelocityZ() {
        return ahrs.getVelocityZ();
    } 

    public static void resetAngle() {
        ahrs.reset();
    }

    public static double getbotAltitude() {
        return ahrs.getAltitude();
    }

    public static double getXAcceleration() {
        return ahrs.getRawAccelX();
    }

    public static double getYAcceleration() {
        return ahrs.getRawAccelY();
    }

    public static double getYaw() {
        return ahrs.getYaw();
    }

    public void turnToAngle(double angle) {
        double rightVolts = 0.25;
        double leftVolts = 0.25;
        double offset = 10;
        
        if (NavX.getbotAngle() < angle - offset) {
            robosystem.drivetrain.setPower(leftVolts, 0);
            System.out.println("Turning to");
        }else {
          robosystem.drivetrain.setPower(0, 0);
        }
        
    
    }



    
    
}
