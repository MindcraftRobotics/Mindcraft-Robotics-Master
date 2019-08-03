package frc.robot;    // top level, easy access from everywhere else

public class Ports {             // public: we want it to be accessible, right?
/**
 * public: should be accessible by other classes. if this were private, we couldn't see it.
 * static: this is weird. so if it's not static, you have to instantiate the class (new Ports()) in order to use it. since it's static, we can just say Ports.THINGY
 *     it's like saying "this belongs to the class itself, not an instance of the class"
 * final: don't allow anyone or anything to change this value after it's compiled. instead of a "variable", we should really call it a "constant".
 * int: because port numbers are integers
 */
    public static final int DRIVETRAIN_LEFT_MASTER = 3;
    public static final int DRIVETRAIN_LEFT_SLAVE = 0;
    public static final int DRIVETRAIN_LEFT_2NDSLAVE = 4;
    public static final int DRIVETRAIN_RIGHT_MASTER = 1;
    public static final int DRIVETRAIN_RIGHT_SLAVE = 10;
    public static final int DRIVETRAIN_RIGHT_2NDSLAVE = 9;
    public static final int GROUND_INTAKE_LEFT = 7;
    public static final int GROUND_INTAKE_RIGHT = 2;
    public static final int GROUND_INTAKE_WRIST = 5;
    public static final int FRONT_INTAKE_WRIST = 6;
    public static final int FRONT_INTAKE_CARGO = 8;

    public static final int EXAMPLE_SPARK = 1;
    public static final int EXAMPLE_SPARK_2 = 2;

    public static final int ARM_LEFT = 10;
    public static final int ARM_RIGHT = 8;

    public static final int INTAKE_RIGHT = 9;
    public static final int INTAKE_LEFT = 11;
    public static final int SOLENOID_FORWARD = 0;
    public static final int SOLENOID_REVERSE = 19;

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