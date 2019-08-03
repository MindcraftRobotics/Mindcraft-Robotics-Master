package com.team3495.frc2018.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team3495.frc2018.Constants;
import com.team3495.frc2018.Ports;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Intake
{
    private TalonSRX intake_left;
    private TalonSRX intake_right;
    private DoubleSolenoid double_s;
    private boolean isOpen;
    public enum RollerState {
        INTAKING,
        HOLDING,
        OUTTAKING,
        IDLE
    }
    public enum PistonState {
        OPEN,
        CLOSED
    }
    private RollerState roller_state;
    private PistonState piston_state;
    private static Intake instance = null;

    public static Intake getInstance() {
        if(instance == null) instance = new Intake();
        return instance;
    }

        private Intake() 
        {
            intake_left = new TalonSRX(Ports.INTAKE_LEFT);
            intake_right = new TalonSRX(Ports.INTAKE_RIGHT);
            double_s = new DoubleSolenoid(1,2);

            intake_right.follow(intake_left);
            intake_right.setInverted(false);
            intake_left.setInverted(false);
            intake_left.configVoltageCompSaturation(Constants.Intake.kMaxVoltage, 10);
            intake_left.enableVoltageCompensation(true);
            //intake_left.configContinuousCurrentLimit(30, 10); TODO
            roller_state = RollerState.IDLE;
            piston_state = PistonState.OPEN;
        }
        public void sendInputNormalized(double input)
        {
         intake_left.set(ControlMode.PercentOutput, input);
        }
    
       public void sendInputVolts(double volts)
       {
         volts /= Constants.Intake.kMaxVoltage;
         sendInputNormalized(volts);
        }
       public void requestState(RollerState state)
       {
        this.roller_state = state;
        switch(this.roller_state){
            case INTAKING: sendInputVolts(Constants.Intake.kIntaking);
            break;
            case OUTTAKING: sendInputVolts(Constants.Intake.kOuttaking);
            break;
            case HOLDING: sendInputVolts(Constants.Intake.kHolding);
            break;
            case IDLE:
            default: sendInputVolts(0.0);
        }
        
      }

      public void requestState(PistonState state){
        this.piston_state = state;
        switch(this.piston_state) {
            case OPEN: openIntake();
            break;
            case CLOSED: closeIntake();
            break;
            default: closeIntake();
        }
      }

      public void openIntake() {
         double_s.set(DoubleSolenoid.Value.kForward);  
      }

      public void closeIntake() {
         double_s.set(DoubleSolenoid.Value.kReverse);
      }
    
    
}