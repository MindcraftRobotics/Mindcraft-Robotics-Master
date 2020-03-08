

package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Ports;



public class Lift extends PIDSubsystem{
    TalonSRX liftTalon = new TalonSRX(Ports.LIFT_TALON);
    VictorSPX wrenchTalon = new VictorSPX(Ports.WRENCH_TALON);
    
    private Lift() {
        super("Lift", 6.5, 0.0, 0.65); // P compares set point with actual value * resulting error 
        liftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
        this.setPercentTolerance(0.5);
        liftTalon.setSelectedSensorPosition(0);

       
        
        this.enable();


    }
    
    private static Lift instance = null;

    public static Lift getInstance() {
        if(instance == null) instance = new Lift();
        return instance;
    }
    public void initDefaultCommand() {


    }
    protected double returnPIDInput() {
         return -liftTalon.getSelectedSensorPosition();
    }
    public boolean atSetpoint() {
        return this.atSetpoint();
    }
    public void lvl1() {
        this.enable();
        this.setSetpoint(10000.0);
        //liftTalon.set(ControlMode.PercentOutput, 1);
    }
    public void runWench() {
        wrenchTalon.set(ControlMode.PercentOutput, -.5);
    }
    public void stopWench() {
        wrenchTalon.set(ControlMode.PercentOutput, 0);
    }
    public void lvl2() {
        this.enable();
        this.setSetpoint(15000.0);
       // liftTalon.set(ControlMode.PercentOutput, 1);
    }
    public void ground() {  
        this.enable();
        this.setSetpoint(0.0);

    }
    public double getDistance() {
        return -liftTalon.getSelectedSensorPosition();
    }
    public void zeroSensor() {
        liftTalon.setSelectedSensorPosition(0);
    }

    
    
    public void manualAdjust(double power) {
        this.disable();
        liftTalon.set(ControlMode.PercentOutput, power);
    }
    public void goUp() {
        this.disable();
        liftTalon.set(ControlMode.PercentOutput, 1);
    }
    public void goDown() {
        this.disable();
        liftTalon.set(ControlMode.PercentOutput, -1);
    }
    public void stop() {
        this.disable();
        liftTalon.set(ControlMode.PercentOutput, 0);
    }

    
    protected void usePIDOutput(double output) {
        liftTalon.set(ControlMode.Velocity, output);
    }
    
  
    public void setPower(double power) {
        this.enable();
        this.setSetpointRelative(power);
    }
   
}