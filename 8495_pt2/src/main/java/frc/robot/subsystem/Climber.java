package frc.robot.subsystem;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.fasterxml.jackson.databind.util.ArrayBuilders.DoubleBuilder;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;

import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.controlsystem.TeleThreeJoysticks;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controlsystem.*;


public class Climber {
    TalonSRX climberController;
    Encoder climberEncoder;

    private Climber() {
        climberController = new TalonSRX(1);
      
        climberEncoder = new Encoder(0, 1);
        climberController.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

    }
    public double getPosition() {
        return climberController.getSensorCollection().getQuadraturePosition();
    }
    public double getVelocity() {
        return climberController.getSensorCollection().getQuadratureVelocity();
    }
    public void setPower(int power) {
        climberController.set(ControlMode.PercentOutput, power);
    }
    public void rise() {
        climberController.set(ControlMode.PercentOutput, .5);
    }
    public void fall() {
        climberController.set(ControlMode.PercentOutput, -.5);
    }
}