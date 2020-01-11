package frc.robot.subsystem;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Notifier;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import java.lang.Math;
import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.controlsystem.TeleThreeJoysticks;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controlsystem.*;

public class ColorWheel {

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
    private final ColorMatch m_colorMatcher = new ColorMatch();
    String colorString;
    String selectedColor;
    String colorStringLast;
    String desiredColor;
    private double rotations;
    private int transitions;
    

    
    private ColorWheel() {
       
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);    
        rotations = 0.0;
        transitions = 0;
        

    
    }
    private static ColorWheel instance = null;

    public static ColorWheel getInstance() {
        if(instance == null) instance = new ColorWheel();
        return instance;
    }

    public void readColor() {
        Color detectedColor = m_colorSensor.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == kBlueTarget) {
            colorString = "Blue";
            selectedColor = "Red";
        } else if (match.color == kRedTarget) {
            colorString = "Red";
            selectedColor = "Blue";
        } else if (match.color == kGreenTarget) {
            colorString = "Green";
            selectedColor = "Yellow";
        } else if (match.color == kYellowTarget) {
            colorString = "Yellow";
            selectedColor = "Green";
        } else {
            colorString = "Unknown";
            selectedColor = "Unknown";
        }

   
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    SmartDashboard.putString("Selected Color", selectedColor);

    
    }
    public void countRotations() {
        if (colorStringLast != colorString) {
            transitions++;
        }
        colorStringLast = colorString;
        rotations = Math.floor(transitions/8);
        SmartDashboard.putNumber("Rotations", rotations);
        SmartDashboard.putNumber("Transitions", transitions);
    }
    
    
    public void resetRotations() {
        rotations = 0.0;
        transitions = 0;

    }
    
    public void spinWheel() {
        if(rotations < 3) {
            //spin the motor
            countRotations();
        }else {
            //motor power to 0
            System.out.println("You are done");
        }
        
    }

    public void selectColor() {

        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0)
        {
            switch (gameData.charAt(0))
            {   
                case 'B' :
                    desiredColor = "Blue";
                break;
                case 'G' :
                    desiredColor = "Green";
                break;
                case 'R' :
                    desiredColor = "Red";
     
                break;
                case 'Y' :
 
                break;
            default :
            //This is corrupt data
            break;
        }
        } else {
  
        }

        if (desiredColor.equals(selectedColor)) {
            //motor speed 0
        }else {
            //spin the motor
        }

    }
   
   

}