package com.team3495.frc2018.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team3495.frc2018.Constants;
import com.team3495.frc2018.Ports;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Arm
{
    private TalonSRX arm_left;
    private TalonSRX arm_right;
    private static Arm instance = null;
    public enum State {
        GOING_DOWN,
        HOLDING,
        GOING_UP
    }
    private State state;

    public static Arm getInstance() {
        if(instance == null) instance = new Arm();
        return instance;
    }

        private Arm() {
            arm_left= new TalonSRX(Ports.ARM_LEFT);
            arm_right = new TalonSRX(Ports.ARM_RIGHT);

            arm_left.follow(arm_right);
            arm_right.configVoltageCompSaturation(Constants.Arm.kMaxVoltage, 10);
            arm_right.enableVoltageCompensation(true);
            arm_right.setInverted(false);
            arm_left.setInverted(true);
            arm_right.configOpenloopRamp(0.5, 10);
            state = State.HOLDING;
        }
        public void sendInputNormalized(double input)
        {
            arm_right.set(ControlMode.PercentOutput, input);
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
             case GOING_UP: sendInputVolts(Constants.Arm.kRaiseVoltage + Constants.Arm.kSteadyStateFeedforward);
             break;
             case GOING_DOWN: sendInputVolts(Constants.Arm.kLowerVoltage + Constants.Arm.kSteadyStateFeedforward);
             break;
             case HOLDING:
             default: sendInputVolts(Constants.Arm.kSteadyStateFeedforward);
         }
        }
        
    
}