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
import edu.wpi.first.wpilibj.util.Color8Bit;
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
   
    /*private final Color kBlueTarget = ColorMatch.makeColor(0.123, 0.422, 0.453);
    private final Color kGreenTarget = ColorMatch.makeColor(0.172, 0.573, 0.253);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.145);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.125);
    */
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
    private int transitions;
    private int red;
    private int green;
    private int blue;
    private double redPercentageNormalized;
    private double bluePercentageNormalized;
    private double greenPercentageNormalized;
    
    

    
    
    

    
    private ColorWheel() {
       
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
        m_colorMatcher.addColorMatch(kFellowTarget);
        m_colorMatcher.addColorMatch(kFreenTarget);


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
        double redValue = m_colorSensor.getRed();
        double blueValue = m_colorSensor.getBlue();
        double greenValue = m_colorSensor.getGreen();
        double total = redValue + blueValue + greenValue;
        double redPercentage = redValue/total;
        double bluePercentage = blueValue/total;
        double greenPercentage = greenValue/total;
        redPercentageNormalized = redPercentage - 0.1;
        if (redPercentageNormalized < 0) {
            redPercentageNormalized = 0;
        }
        greenPercentageNormalized = greenPercentage - 0.3;
        if (greenPercentageNormalized < 0) {
            greenPercentageNormalized = 0;
        }
        bluePercentageNormalized = bluePercentage - 0.1;
        if(bluePercentageNormalized < 0) {
            bluePercentageNormalized = 0;
        }
        double newTotal = redPercentageNormalized + greenPercentageNormalized + bluePercentageNormalized;
        double bluePercentageZeroed = bluePercentageNormalized/newTotal;
        double redPercentageZeroed = redPercentageNormalized/newTotal;
        double greenPercentageZeroed = greenPercentageNormalized/newTotal;
        double blueD = bluePercentageZeroed * 255;
        double redD = redPercentageZeroed * 255;
        double greenD = greenPercentageZeroed * 255;
        blue = (int)blueD;
        red = (int)redD;
        green = (int)greenD;
        Color8Bit currentColor8Bit = new Color8Bit(red, green, blue);
        Color currentColor = new Color(currentColor8Bit);
        
        //multiply the new percentages by 255 per color to get a new color 
        ColorMatchResult match = m_colorMatcher.matchClosestColor(currentColor);

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
            selectedColor = "Unknown";
        } else if (match.color == kFreenTarget) {
            colorString = "Freen";
            selectedColor = "Unknown";
        }else {
            colorString = "Unknown";
            selectedColor = "Unknown";
        }

   
    /*SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);*/
    SmartDashboard.putNumber("Red", red);
    SmartDashboard.putNumber("Green", green);
    SmartDashboard.putNumber("Blue", blue);


    SmartDashboard.putNumber("Pre Nomalized Red", redPercentage);
    SmartDashboard.putNumber("Pre Normalized Green", greenPercentage);
    SmartDashboard.putNumber("Pre Normalized Blue", bluePercentage);
    SmartDashboard.putNumber("Normalized Red", redPercentageZeroed);
    SmartDashboard.putNumber("Normalized Green", greenPercentageZeroed);
    SmartDashboard.putNumber("Normalized Blue", bluePercentageZeroed);
    
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    SmartDashboard.putString("Selected Color", selectedColor);
    SmartDashboard.putNumber("Total Intensity", total);
    
    }
    public void countRotations() {
        if (colorStringLast != colorString) {
            //if it is moving counterclockwise
            if(colorString == "Fellow") {
                    transitions--;
                
            }
            if(colorString == "Freen") {
                    transitions--;
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