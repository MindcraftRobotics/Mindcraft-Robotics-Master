package frc.robot;

public class Constants { // MODIFY BASED ON OUR ROBOT
    public static final double kLoopTime = 0.02;//seconds
    public static final String k_path_name = "Unnamed_1";

    public static class Drivetrain 
    {
        public static final double kWheelBase = 2.13;    // ft    // try as hard as you can to keep your units consistent: if you use feet, use feet, not inches
        public static final double kMaxVoltage = 11.5;
        public static final double k_wheel_diameter = 6.0 / 12.0;
        public static final int k_ticks_per_revolution = 1024;
        public static final double k_max_velocity = 6.0;
        public static final float Kp = -0.05f;
        public static final float min_command = 0.05f;
    
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
        public static final int cargoIn = 2;
        public static final int cargoOut = 3;
        public static final int wristIn = 4;
        public static final int wristOut = 5;

    }
}
    
}