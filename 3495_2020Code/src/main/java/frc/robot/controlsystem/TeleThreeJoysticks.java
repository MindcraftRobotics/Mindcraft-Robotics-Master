package frc.robot.controlsystem;

import frc.robot.controlsystem.RoboSystem;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

import frc.robot.Ports;
import frc.robot.Util;
import frc.robot.Constants;
//import frc.robot.subsystem.Limelight;
//import frc.robot.subsystems.NavX;
//import frc.robot.subsystems.Back_Intake;

//import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleThreeJoysticks
{
    public RoboSystem robosystem;
    public Joystick driverLeft;
    public Joystick driverRight;
    public Joystick coDriver;
    public UsbCamera cameraFront;
    public UsbCamera cameraBack;
    public UsbCamera camera;
    public VideoSink server;
    


    private TeleThreeJoysticks()
    {
        robosystem = RoboSystem.getInstance();
        driverLeft = new Joystick(Ports.JOYSTICK_LEFT);
        driverRight = new Joystick(Ports.JOYSTICK_RIGHT);
        coDriver = new Joystick(Ports.JOYSTICK_CODRIVER);
        camera = CameraServer.getInstance().startAutomaticCapture(0);
        //camera = CameraServer.setQuality(50);
        
    } 

    private void driver()
    {
    
        double left = Util.deadbandAndBound(-driverLeft.getRawAxis(Constants.ControlAxes.yAxis),
        Constants.TankDriver.Deadbands.Left.kMinReverse, 
        Constants.TankDriver.Deadbands.Left.kMinForward,
        -1.0, 1.0);
        double right = Util.deadbandAndBound(-driverRight.getRawAxis(Constants.ControlAxes.yAxis),
        Constants.TankDriver.Deadbands.Right.kMinReverse, 
        Constants.TankDriver.Deadbands.Right.kMinForward,
        -1.0, 1.0);
        
        robosystem.drivetrain.setPower(left, right);
        robosystem.lift.manualAdjust(coDriver.getY());

        
        if(coDriver.getRawButtonReleased(7)) {
            robosystem.lift.lvl1();
        }else{
            robosystem.lift.setPower(0);
        }
        if(coDriver.getRawButtonReleased(8)) {
            robosystem.lift.lvl2();
        }else{
            robosystem.lift.setPower(0);
        }
        
        if(coDriver.getPOV() == 0){
            robosystem.lift.goUp();
            
        }else if(coDriver.getPOV() == 180){
            robosystem.lift.goDown();
        } else {
            robosystem.lift.setPower(0);
        }
        
        

        if (driverRight.getRawButton(2)) {
            robosystem.colorwheel.spinWheel();
        }
        if (driverRight.getRawButton(3)) {
            robosystem.colorwheel.selectColor();
        }
        if (driverLeft.getRawButtonReleased(5)) {
            robosystem.colorwheel.resetRotations();
        }  
        
       
}

    
    private void coDriver()
    {
        //robosystem.colorwheel.setPower(coDriver.getY());
        if(coDriver.getRawButton(2)) {
            robosystem.shooter.shoot();
        }else if(coDriver.getRawButton(4)) {
            robosystem.shooter.intake();
        }else{
            robosystem.shooter.intakeStopIntake();
            robosystem.shooter.intakeStopShoot();
        }
        
        /*
        
        if(coDriver.getRawButtonReleased(1) && robosystem.shooter.getSolenoidPosition() == false) {
            robosystem.shooter.raise();
        }else if(coDriver.getRawButtonReleased(1) && robosystem.shooter.getSolenoidPosition() == true) {
            robosystem.shooter.lower();
        }

        /* */
        if(coDriver.getRawButtonReleased(5) && robosystem.colorwheel.getSolenoidPosition() == false)
        {
            robosystem.colorwheel.raise();

        }else if(coDriver.getRawButtonReleased(6) && robosystem.colorwheel.getSolenoidPosition() == true){
            robosystem.colorwheel.lower();

        }
        
    }

    
    
    public void update()
    {
        driver();
        coDriver();
        
    }

    private static TeleThreeJoysticks instance = null;

    public static TeleThreeJoysticks getInstance() {
        if(instance == null) instance = new TeleThreeJoysticks();
        return instance;

}
}
