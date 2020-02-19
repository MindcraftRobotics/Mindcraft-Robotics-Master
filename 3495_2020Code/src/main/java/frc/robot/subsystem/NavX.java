package frc.robot.subsystem;


import com.kauailabs.navx.frc.AHRS;


import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.I2C;
import frc.robot.controlsystem.RoboSystem;





public class NavX{
    private AHRS gyro;
    private RoboSystem robosystem;
    PIDController turnController;
    double rotateToAngleRate;
    boolean rotateToAngle;
    double currentAngle;
    static final double kP = 0.03;
    static final double kI = 0.00;
    static final double kD = 0.00;
    static final double kF = 0.00;
    private double currentRotationRate;

    static final double kToleranceDegrees = 2.0f;


    private NavX() {
        gyro = new AHRS(SerialPort.Port.kMXP);
        currentAngle = gyro.getCompassHeading();

    }
    private static NavX instance = null;
    public static NavX getInstance() {
        if(instance == null) instance = new NavX();
        return instance;
    }


   

    public double currentAngle() {
        return currentAngle;
    }
    public boolean calibrate() {
        return gyro.isCalibrating();
    }
        

   
    public void update()
    {

    }
   
}