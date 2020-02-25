package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;



public class Lift extends PIDSubsystem{
    TalonSRX liftTalon = new TalonSRX(1);
    Encoder m_liftEncoder = new Encoder(0,1);
    private Lift() {
        super("Lift", 1.0, 0.0, 0.0);
        liftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
        this.setPercentTolerance(5.0);
        this.enable();
        this.ground();

    }
    
    private static Lift instance = null;

    public static Lift getInstance() {
        if(instance == null) instance = new Lift();
        return instance;
    }
    public void initDefaultCommand() {

    }
    protected double returnPIDInput() {
         return m_liftEncoder.getDistance(); 
    }
    public boolean atSetpoint() {
        return this.atSetpoint();
    }
    public void lvl1() {
        this.enable();
        this.setSetpoint(2000);
    }
    public void lvl2() {
        this.enable();
        this.setSetpoint(3000);
    }
    public void ground() {  
        this.enable();
        this.setSetpoint(10);

    }
    public double getDistance() {
        return m_liftEncoder.getDistance();
    }
    public double getDistancePerPulse() {
        return m_liftEncoder.getDistancePerPulse();
    }
    public void setDistancePerPulse(int distancePerPulse) {
        m_liftEncoder.setDistancePerPulse(distancePerPulse);
    }
    
    public void manualAdjust(double power) {
        this.disable();
        liftTalon.set(ControlMode.PercentOutput, power);
    }
    public void goUp() {
        this.disable();
        liftTalon.set(ControlMode.PercentOutput, .50);
    }
    public void goDown() {
        this.disable();
        liftTalon.set(ControlMode.PercentOutput, -.50);
    }

    
    protected void usePIDOutput(double output) {
        liftTalon.set(ControlMode.Velocity, output);
    }
  
    public void setPower(double power) {
        this.enable();
        this.setSetpointRelative(power);
    }
   
}