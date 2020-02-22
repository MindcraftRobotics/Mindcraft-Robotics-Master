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

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.controlsystem.TeleThreeJoysticks;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controlsystem.*;

public class Shooter
{
    // Talons and junk 
    private TalonSRX shooter_outtake;
    private TalonSRX shooter_intake;
    private DoubleSolenoid solenoidPiston;
    
    //constructor
    private Shooter()
    {
        shooter_intake = new TalonSRX(Ports.SHOOTER_TALON_IN);
        shooter_outtake = new TalonSRX(Ports.SHOOTER_TALON_OUT);
        solenoidPiston = new DoubleSolenoid(0, 1);
        solenoidPiston.set(Value.kReverse);
        

        shooter_outtake.setInverted(false);
        shooter_intake.setInverted(false);

        shooter_outtake.configNominalOutputForward(0,10);
        shooter_outtake.configNominalOutputReverse(0,10);
        shooter_outtake.configPeakOutputForward(1,10);
        shooter_outtake.configPeakOutputReverse(-1,10);
        shooter_intake.configNominalOutputForward(0,10);
        shooter_intake.configNominalOutputReverse(0,10);
        shooter_intake.configPeakOutputForward(1,10);
        shooter_intake.configPeakOutputReverse(-1,10);

    }
    private static Shooter instance = null;

    public static Shooter getInstance() {
        if(instance == null) instance = new Shooter();
        return instance;
    }
    public void shoot()
    {
        
        
    }
    public void raise() {
        solenoidPiston.set(Value.kForward);
    }
    public void lower() {
        solenoidPiston.set(Value.kReverse);
    }
    public Value getSolenoidPosition() {
        return solenoidPiston.get();
    }





}





