package com.team3495.frc2018.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team3495.frc2018.Constants;
import com.team3495.frc2018.Ports;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Climber
{
    private TalonSRX climber;
    private static Climber instance = null;
    public enum State {
        IDLE,
        GOING_UP
    }
    private State state;

    public static Climber getInstance() {
        if(instance == null) instance = new Climber();
        return instance;
    }

        private Climber() {
            climber= new TalonSRX(Ports.CLIMB);

            climber.configVoltageCompSaturation(Constants.Climber.kMaxVoltage, 10);
            state = State.IDLE;
        }
        public void sendInputNormalized(double input)
        {
            climber.set(ControlMode.PercentOutput, input);
       }
       public void sendInputVolts(double input)
       {
         input /= Constants.Arm.kMaxVoltage;
         sendInputNormalized(input);
        }
        public void requestState(State state)
        {
         this.state = state;
         switch(this.state){
             case GOING_UP: climber.set(ControlMode.PercentOutput, -1);
             break;
             case IDLE:
             default: sendInputVolts(0.0);
         }
        }
        
    
}