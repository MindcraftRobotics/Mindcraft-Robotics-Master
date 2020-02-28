package frc.robot;    // top level, easy access from everywhere else

public class Ports {             // public: we want it to be accessible, right?
/**
 * public: should be accessible by other classes. if this were private, we couldn't see it.
 * static: this is weird. so if it's not static, you have to instantiate the class (new Ports()) in order to use it. since it's static, we can just say Ports.THINGY
 *     it's like saying "this belongs to the class itself, not an instance of the class"
 * final: don't allow anyone or anything to change this value after it's compiled. instead of a "variable", we should really call it a "constant".
 * int: because port numbers are integers
 */
    public static final int DRIVETRAIN_LEFT_MASTER = 7; // 1
    public static final int DRIVETRAIN_LEFT_MASTER2 = 6; //2

    public static final int DRIVETRAIN_RIGHT_MASTER = 3;
    public static final int DRIVETRAIN_RIGHT_MASTER2 = 1;
    public static final int COLOR_SPINNER = 8;
    public static final int SHOOTER_TALON_OUT = 5;
    public static final int SHOOTER_TALON_IN = 2;
    public static final int LIFT_TALON = 9;
    public static final int WRENCH_TALON = 10;
    



    public static final int JOYSTICK_LEFT = 0;
    public static final int JOYSTICK_RIGHT = 1;
    public static final int JOYSTICK_CODRIVER = 2;
    public static final int XBOX_CODRIVER = 3;


    
    /*
     *Rest of all the talons, encoders, etc. go here 
     * 
     * 
     */
}