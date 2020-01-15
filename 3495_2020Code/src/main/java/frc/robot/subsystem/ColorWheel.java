package frc.robot.subsystem;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Notifier;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorSensorV3.RawColor;
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
   
    private final Color kBlueTarget = ColorMatch.makeColor(0.123, 0.422, 0.453);
    private final Color kGreenTarget = ColorMatch.makeColor(0.172, 0.573, 0.253);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.145);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.125);
    private final Color kFellowTarget = ColorMatch.makeColor(0.427, 0.509, 0.064);
    private final ColorMatch m_colorMatcher = new ColorMatch();
    String colorString;
    String selectedColor;
    String colorStringLast;
    String desiredColor;
    String expectedLeftColor;
    String expectedRightColor;
    private double rotations;
    private int transitions;
    
    

    
    private ColorWheel() {
       
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
        m_colorMatcher.addColorMatch(kFellowTarget);
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
        int redValue = m_colorSensor.getRed();
        int blueValue = m_colorSensor.getBlue();
        int greenValue = m_colorSensor.getGreen();
        int total = redValue + blueValue + greenValue;
        double redPercentage = redValue/total;
        double bluePercentage = blueValue/total;
        double greenPercentage = greenValue/total;
        double redPercentageNormalized = redPercentage - 0.1;
        double greenPercentageNormalized = greenPercentage - 0.3;
        double bluePercentageNormalized = bluePercentage - 0.1;
        Color currentNormalizedColor = redPercentageNormalized, greenPercentageNormalized, bluePercentageNormalized;
        
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == kBlueTarget) {
            colorString = "Blue";
            selectedColor = "Red";
            expectedRightColor = "Yellow";
            expectedLeftColor = "Green"; 
            
        } else if (match.color == kRedTarget) {
            colorString = "Red";
            selectedColor = "Blue";
            expectedRightColor = "Green";
            expectedLeftColor = "Yellow";
        } else if (match.color == kGreenTarget) {
            colorString = "Green";
            selectedColor = "Yellow";
            expectedRightColor = "Blue";
            expectedLeftColor = "Red";
        } else if (match.color == kYellowTarget) {
            colorString = "Yellow";
            selectedColor = "Green";
            expectedRightColor = "Red";
            expectedLeftColor = "Blue";
        }else if (match.color == kFellowTarget) {
            colorString = "Fellow";
        } else {
            colorString = "Unknown";
            selectedColor = "Unknown";
        }

   
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    System.out.println(detectedColor);
    SmartDashboard.putNumber("Raw Red", redValue);
    SmartDashboard.putNumber("Raw Blue", blueValue);
    SmartDashboard.putNumber("Raw Green", greenValue);
    
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    SmartDashboard.putString("Selected Color", selectedColor);

    
    }
    public void countRotations() {
        if (colorStringLast != colorString) {
            //if it is moving clockwise
            if(colorString == "Green") {
                if (colorStringLast == "Fellow") {
                    transitions--;
                }
            }
            //if it is moving counterclockwise
            if (colorString == "Red") {
                if (colorStringLast == "Fellow") {
                    transitions--;
                }
            }
             
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
    public void equalColorStringLast() {
        colorStringLast = colorString;
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
                    desiredColor = "Yellow";
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