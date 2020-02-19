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
        super("Lift", 2.0, 0.0, 0.0);
        liftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

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
        this.setSetpoint(1);
    }
    public void lvl2() {
        this.enable();
        this.setSetpoint(2);
    }
    public void ground() {
        this.enable();
        this.setSetpoint(0);

    }
    protected void usePIDOutput(double output) {
        liftTalon.set(ControlMode.Current, output);
    }
    public void setPower(double power) {
        this.disable();
        liftTalon.set(ControlMode.PercentOutput, power);
        this.setSetpointRelative(power);
    }
   
}