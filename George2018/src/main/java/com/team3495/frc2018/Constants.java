package com.team3495.frc2018;

public class Constants { // MODIFY BASED ON OUR ROBOT
    public static final double kLoopTime = 0.02;//seconds
    public static class Arm 
    {
       public static final double kMaxVoltage = 12.0; 
       public static final double kSteadyStateFeedforward = 1.7;
       public static final double kRaiseVoltage = 8.0;
       public static final double kLowerVoltage = -8.0;
    }
    public static class Climber
    {
        public static final double kMaxVoltage = 12.0;
    }
    public static class Drivetrain 
    {
        public static final double kWheelBase = 3.0;    // ft    // try as hard as you can to keep your units consistent: if you use feet, use feet, not inches
        public static final double kMaxVoltage = 11.5;
        public static final double kWheelDiameter = 4.0;
        public static final int kTicksPerRevolution = 1000;
        public static final double kMaxVelocity = 5;
    
        public static class Left
     {
            public static class Forward
         {
            public static final double kStaticFrictionFeedForward = 0.0;//FIX ME; implement
            public static final double kVoltsPerFootPerSecond = 1.0;//FIX ME; implement

         }
         public static class Reverse
            {
            public static final double kStaticFrictionFeedForward = 0.0;//FIX ME; implement
            public static final double kVoltsPerFootPerSecond = 1.0;//FIX ME; implement
            }
    }
        public static class Right
        {
            public static class Forward
            {
            public static final double kStaticFrictionFeedForward = 0.0;//FIX ME; implement
            public static final double kVoltsPerFootPerSecond = 1.0;//FIX ME; implement
            
            }
             public static class Reverse
             {
            public static final double kStaticFrictionFeedForward = 0.0;//FIX ME; implement
            public static final double kVoltsPerFootPerSecond = 1.0;//FIX ME; implement
            
            }


    }
}
    public static class ControlAxes
    {
        public static final int xAxis = 0;
        public static final int yAxis = 1;
    }
    public static class Intake
    {
        public static final double kMaxVoltage =  12.0;
        public static final double kIntaking = 8.0;
        public static final double kOuttaking = -8.0;
        public static final double kHolding = 0.5;

    }
    public static class TankDriver
    {
        public static final class Deadbands
        {
            public static class Left
            {
                public static final double kMinForward = .1;
                public static final double kMinReverse = -.1;
            }
            public static class Right
            {
                public static final double kMinForward = .1;
                public static final double kMinReverse = -.1;
            }
        }
    }
    public static class JoystickCodriver
    {
        public static class Buttons
    {
        public static final Boolean isActualDriver = false;
        public static final int intakeOut = isActualDriver ? 7 : 4; // mateo wants POV 0/180 for intake
        public static final int intakeIn = isActualDriver ? 5 : 5;  // FIXME: implement as above
        public static final int armRaise = isActualDriver ? 6 : 1;
        public static final int armLower = isActualDriver ? 8 : 2;
        public static final int intakeOpen = isActualDriver ? 1 : 3;
        public static final int climber = isActualDriver ? 11 : 7;
    }
}
    
}