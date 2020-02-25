package frc.robot.subsystem;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Notifier;






import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.controlsystem.TeleThreeJoysticks;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controlsystem.*;

public class Drivetrain
{
    //talons, subsystems, etc
    private TalonFX drivetrain_leftMaster;
    private TalonFX drivetrain_leftMaster2;

    private TalonFX drivetrain_rightMaster;
    private TalonFX drivetrain_rightMaster2;
    private Encoder leftEncoder;
    private Encoder rightEncoder;


   
    private Notifier m_follower_notifier;

    //enums/state variables


    //constructors
    private Drivetrain()
    {
        drivetrain_leftMaster = new TalonFX(Ports.DRIVETRAIN_LEFT_MASTER);
        drivetrain_leftMaster2 = new TalonFX(Ports.DRIVETRAIN_LEFT_MASTER2);
       
        drivetrain_rightMaster = new TalonFX(Ports.DRIVETRAIN_RIGHT_MASTER);
        drivetrain_rightMaster2 = new TalonFX(Ports.DRIVETRAIN_RIGHT_MASTER2);

        

        //drivetrain_rightSlave.follow(drivetrain_rightMaster);
        
        //drivetrain_leftSlave.follow(drivetrain_leftMaster);
      
      // With the falcon motors, master and slave talons won't work, therefore I changed all the slave talons to it's own master talons temporarilys

        drivetrain_rightMaster.configVoltageCompSaturation(Constants.Drivetrain.kMaxVoltage, 10);
        drivetrain_rightMaster.enableVoltageCompensation(true); 
        drivetrain_rightMaster.setInverted(false); // originally false
        drivetrain_rightMaster2.setInverted(false); // originally false
        

        drivetrain_leftMaster.setInverted(true);//make sure to check when the actual bot comes around originally true
        drivetrain_leftMaster2.setInverted(true); // originally true

        drivetrain_leftMaster.configVoltageCompSaturation(Constants.Drivetrain.kMaxVoltage, 10);
        drivetrain_leftMaster2.configVoltageCompSaturation(Constants.Drivetrain.kMaxVoltage, 10);
        drivetrain_leftMaster.enableVoltageCompensation(true);
        drivetrain_leftMaster2.enableVoltageCompensation(true);
        drivetrain_leftMaster.setNeutralMode(NeutralMode.Brake);
        drivetrain_leftMaster2.setNeutralMode(NeutralMode.Brake);
        drivetrain_rightMaster.setNeutralMode(NeutralMode.Brake);
        drivetrain_rightMaster2.setNeutralMode(NeutralMode.Brake);

        drivetrain_rightMaster.configNominalOutputForward(0, 10);
        drivetrain_rightMaster2.configNominalOutputForward(0, 10);

        drivetrain_rightMaster.configNominalOutputReverse(0, 10);
        drivetrain_rightMaster2.configNominalOutputReverse(0, 10);

        drivetrain_leftMaster.configNominalOutputForward(0, 10);
        drivetrain_leftMaster2.configNominalOutputForward(0, 10);
        drivetrain_leftMaster.configNominalOutputReverse(0, 10);
        drivetrain_leftMaster2.configNominalOutputReverse(0, 10);
        
        drivetrain_rightMaster.configPeakOutputForward(.75, 10);
        drivetrain_rightMaster2.configPeakOutputForward(.75, 10);

        drivetrain_rightMaster.configPeakOutputReverse(-.75, 10);
        drivetrain_rightMaster2.configPeakOutputReverse(-.75, 10);

        drivetrain_leftMaster.configPeakOutputForward(.75, 10);
        drivetrain_leftMaster2.configPeakOutputForward(.75, 10);
        drivetrain_leftMaster.configPeakOutputReverse(-.75, 10);
        drivetrain_leftMaster2.configPeakOutputReverse(-.75, 10);


      
        drivetrain_leftMaster.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);
        drivetrain_leftMaster2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);

        drivetrain_rightMaster.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);
        drivetrain_rightMaster2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);


        drivetrain_leftMaster.setSensorPhase(true);
        drivetrain_leftMaster2.setSensorPhase(true);

        drivetrain_rightMaster.setSensorPhase(true);
        drivetrain_rightMaster2.setSensorPhase(true);


        drivetrain_rightMaster.selectProfileSlot(0, 0);
        drivetrain_rightMaster2.selectProfileSlot(0, 0);

        drivetrain_rightMaster.config_kP(0, .2, 10);
        drivetrain_rightMaster2.config_kP(0, .2, 10);

        drivetrain_rightMaster.config_kI(0, 0, 10);
        drivetrain_rightMaster2.config_kI(0, 0, 10);

        drivetrain_rightMaster.config_kD(0, 0, 10);
        drivetrain_rightMaster2.config_kD(0, 0, 10);

        drivetrain_rightMaster.config_kF(0, .2, 10);
        drivetrain_rightMaster2.config_kF(0, .2, 10);


        drivetrain_leftMaster.selectProfileSlot(0, 0);
        drivetrain_leftMaster2.selectProfileSlot(0, 0);

        drivetrain_leftMaster.config_kP(0, .2, 10);
        drivetrain_leftMaster2.config_kP(0, .2, 10);

        drivetrain_leftMaster.config_kI(0, 0, 10);
        drivetrain_leftMaster2.config_kI(0, 0, 10);

        drivetrain_leftMaster.config_kD(0, 0, 10);
        drivetrain_leftMaster2.config_kD(0, 0, 10);

        drivetrain_leftMaster.config_kF(0, .2, 10);
        drivetrain_leftMaster2.config_kF(0, .2, 10);


        drivetrain_leftMaster.configMotionCruiseVelocity(15000, 30);
        drivetrain_leftMaster2.configMotionCruiseVelocity(15000, 30);

        drivetrain_rightMaster.configMotionCruiseVelocity(15000, 30);
        drivetrain_rightMaster2.configMotionCruiseVelocity(15000, 30);

        drivetrain_leftMaster.configMotionAcceleration(6000, 30);
        drivetrain_leftMaster2.configMotionAcceleration(6000, 30);

        drivetrain_rightMaster.configMotionAcceleration(6000, 30);
        drivetrain_rightMaster2.configMotionAcceleration(6000, 30);

      
      
    }

    //instance junk
    private static Drivetrain instance = null;

    public static Drivetrain getInstance() {
        if(instance == null) instance = new Drivetrain();
        return instance;
    }
 
    //methods for controlling
        /* @param left input to left side of drivetrain, in [-1.0, 1.0]
        * @param right input to right side of drivetrain, in [-1.0, 1.0]
        */
     public void setPower(double left, double right)
    {
     drivetrain_leftMaster.set(ControlMode.PercentOutput, left);
     drivetrain_leftMaster2.set(ControlMode.PercentOutput, left);

     
     drivetrain_rightMaster.set(ControlMode.PercentOutput, right); 
     drivetrain_rightMaster2.set(ControlMode.PercentOutput, right); 

    }

    public void setCurrentClosedLoop(double left, double right) //joystick times 40, ANYTHING MORE THAN 40 BUSTS BREAKER!!!
    {
       drivetrain_leftMaster.set(ControlMode.Current, left*40);
       drivetrain_leftMaster2.set(ControlMode.Current, left*40);

       drivetrain_rightMaster.set(ControlMode.Current, right*40);
       drivetrain_rightMaster2.set(ControlMode.Current, right*40);

    }

    public void velocityControl(double left, double right)
    {
       double targetVelLeft = left * 1000 * 4096 / 600;  // left * RPM * encoder units/ encoder units
       double targetVelRight = right * 1000 * 4096 / 600; // right * RPM * encoder units/ encoder units
       drivetrain_leftMaster.set(ControlMode.Velocity, targetVelLeft);
       drivetrain_leftMaster2.set(ControlMode.Velocity, targetVelLeft);

       drivetrain_rightMaster.set(ControlMode.Velocity, targetVelRight);
       drivetrain_rightMaster2.set(ControlMode.Velocity, targetVelRight);

    }
    public void velocityControlAuton(double left, double right)
    {
       double targetVelLeft = left * 70.5 * 4096 / 600;  // left * RPM * encoder units/ encoder units
       double targetVelRight = right * 70.5 * 4096 / 600; // right * RPM * encoder units/ encoder units
       drivetrain_leftMaster.set(ControlMode.Velocity, targetVelLeft);
       drivetrain_leftMaster2.set(ControlMode.Velocity, targetVelLeft);

       drivetrain_rightMaster.set(ControlMode.Velocity, targetVelRight);
       drivetrain_rightMaster2.set(ControlMode.Velocity, targetVelRight);

    }
    public void PercentOutputAuton(double left, double right)
    {
       drivetrain_leftMaster.set(ControlMode.PercentOutput, left);
       drivetrain_leftMaster2.set(ControlMode.PercentOutput, left);

       drivetrain_rightMaster.set(ControlMode.PercentOutput, right);
       drivetrain_rightMaster2.set(ControlMode.PercentOutput, right);

    }

   public void sendInputVolts(double leftVoltage, double rightVoltage)
   {
      drivetrain_leftMaster.set(ControlMode.PercentOutput, leftVoltage/Constants.Drivetrain.kMaxVoltage);
      drivetrain_leftMaster2.set(ControlMode.PercentOutput, leftVoltage/Constants.Drivetrain.kMaxVoltage);

      drivetrain_rightMaster.set(ControlMode.PercentOutput, rightVoltage/Constants.Drivetrain.kMaxVoltage);
      drivetrain_rightMaster2.set(ControlMode.PercentOutput, rightVoltage/Constants.Drivetrain.kMaxVoltage);

    }
    
    public void sendInputNyooms(double left, double right)
    {
     double leftVolts;
     double leftVolts2;
     double rightVolts;
     double rightVolts2;
     
     leftVolts = (left > 0) ?
        Constants.Drivetrain.Left.Forward.kStaticFrictionFeedForward:
        Constants.Drivetrain.Left.Reverse.kStaticFrictionFeedForward;
     leftVolts += left * ((left > 0) ?
        Constants.Drivetrain.Left.Forward.kVoltsPerFootPerSecond:
        Constants.Drivetrain.Left.Reverse.kVoltsPerFootPerSecond);
      
      leftVolts2 = (left > 0) ?
         Constants.Drivetrain.Left.Forward.kStaticFrictionFeedForward:
         Constants.Drivetrain.Left.Reverse.kStaticFrictionFeedForward;
      leftVolts2 += left * ((left > 0) ?
         Constants.Drivetrain.Left.Forward.kVoltsPerFootPerSecond:
         Constants.Drivetrain.Left.Reverse.kVoltsPerFootPerSecond);

     rightVolts = (right > 0) ?
        Constants.Drivetrain.Right.Forward.kStaticFrictionFeedForward:
        Constants.Drivetrain.Right.Reverse.kStaticFrictionFeedForward;
     rightVolts += right * ((right > 0) ?
        Constants.Drivetrain.Right.Forward.kVoltsPerFootPerSecond:
        Constants.Drivetrain.Right.Reverse.kVoltsPerFootPerSecond);

      rightVolts2 = (right > 0)?
         Constants.Drivetrain.Right.Forward.kStaticFrictionFeedForward:
         Constants.Drivetrain.Right.Reverse.kStaticFrictionFeedForward;

      rightVolts2 += right * ((right > 0) ? 
         Constants.Drivetrain.Right.Forward.kVoltsPerFootPerSecond:
         Constants.Drivetrain.Right.Reverse.kVoltsPerFootPerSecond);

     sendInputVolts(leftVolts, rightVolts);
     sendInputNyooms(leftVolts2, right);
    }
    
    public void zeroSensors()
    {
       drivetrain_leftMaster.setSelectedSensorPosition(0, 0, 30);
       drivetrain_leftMaster2.setSelectedSensorPosition(0, 0, 30);

       drivetrain_rightMaster.setSelectedSensorPosition(0, 0, 30);
       drivetrain_rightMaster2.setSelectedSensorPosition(0, 0, 30);

    }
    public int getLeftEncoderPosition()
    {
      return drivetrain_leftMaster.getSelectedSensorPosition();
    }

    public int getLeft2EncoderPosition(){
       return drivetrain_leftMaster2.getSelectedSensorPosition();
    }
    public int getRightEncoderPosition()
    {
      return drivetrain_rightMaster.getSelectedSensorPosition();
    }

    public int getRight2EncoderPosition(){
       return drivetrain_rightMaster2.getSelectedSensorPosition();
    }
    
    /*
    public void limelightTurning(double leftCommand, double rightCommand)
    {

      double rightVolts = 0.25;
      double leftVolts = 0.25;
      double aim_error = -Limelight.getTx(); //add in
      float heading_error = (float)Limelight.getTx();
      double steering_adjust = Constants.Drivetrain.Kp*aim_error; //add in
      //float steering_adjust = 0.0f;
    

   
    if (Limelight.getTx()> 1.0)
      {
      steering_adjust = Constants.Drivetrain.Kp*heading_error - Constants.Drivetrain.min_command;
    }
   else if (Limelight.getTx() < 1.0) 
      {
      steering_adjust = Constants.Drivetrain.Kp*heading_error + Constants.Drivetrain.min_command;
      }
      leftVolts += (steering_adjust + leftCommand);
      rightVolts += (steering_adjust + rightCommand);

      drivetrain_leftMaster.set(ControlMode.PercentOutput, leftVolts/Constants.Drivetrain.kMaxVoltage);
	  drivetrain_rightMaster.set(ControlMode.PercentOutput, rightVolts/Constants.Drivetrain.kMaxVoltage);
    }

   */
   
    public void update()
    {

    }

   }
