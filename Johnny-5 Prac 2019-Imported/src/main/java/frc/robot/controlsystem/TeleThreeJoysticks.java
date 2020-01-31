package frc.robot.controlsystem;

import frc.robot.controlsystem.RoboSystem;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.CameraServer;

import frc.robot.Ports;
import frc.robot.Util;
import frc.robot.Constants;
import frc.robot.subsystems.Limelight;
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
    public VideoSink server;
    
    

    private TeleThreeJoysticks()
    {
        robosystem = RoboSystem.getInstance();
        driverLeft = new Joystick(Ports.JOYSTICK_LEFT);
        driverRight = new Joystick(Ports.JOYSTICK_RIGHT);
        coDriver = new Joystick(Ports.JOYSTICK_CODRIVER);
        cameraFront = CameraServer.getInstance().startAutomaticCapture(0);
        cameraBack = CameraServer.getInstance().startAutomaticCapture(1);

        
        
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
        
        //robosystem.drivetrain.velocityControl(left, right);
        
        
        /*
        if(driverLeft.getRawButton(3))
        {
            robosystem.elevator.setElevatorPower(0.15);
        }else if(driverLeft.getRawButton(4))
        {
            robosystem.elevator.setElevatorPower(-0.40); //Remember to check out MLG Shrek has Swag on GitHub
        }else
        {
            robosystem.elevator.setElevatorPower(-0.09); //negative goes up for now. -.15 goes up for now, needs to be lower
        }*/
        
        if(driverRight.getRawButton(11))
        {
            robosystem.back_Intake.setGroundWristPower(1.0);
        }else if(driverRight.getRawButton(12))
        {
            robosystem.back_Intake.setGroundWristPower(-1.0);
        }else
        {
            robosystem.back_Intake.setGroundWristPower(0.0);
        }

        if(driverLeft.getRawButton(5))
        {
            robosystem.back_Intake.setRollerPower(1.0);
        }else if(driverLeft.getRawButton(6))
        {
            robosystem.back_Intake.setRollerPower(-1.0);
        }else
        {
            robosystem.back_Intake.setRollerPower(0.0);
        }

        if(driverRight.getRawButton(5))
        {
            robosystem.front_intake.setCargoPower(0.8);
        }else if(driverRight.getRawButton(6))
        {
            robosystem.front_intake.setCargoPower(-0.8);
        }else
        {
            robosystem.front_intake.setCargoPower(0.0);
        }

        if(driverLeft.getRawButton(11))
        {
            robosystem.front_intake.setWristPower(0.6);
        }else if(driverLeft.getRawButton(12))
        {
            robosystem.front_intake.setWristPower(-0.6);
        }else
        {
            robosystem.front_intake.setWristPower(0.0);
        }
        if(driverRight.getRawButton(6)) {
            robosystem.back_Intake.setBackSolenoid();
        }else {
            robosystem.back_Intake.back_one.set(false);
        }

        if(driverRight.getRawButton(7)) {
            robosystem.front_intake.setSolenoids();
        }else if(driverRight.getRawButtonReleased(7)) {
            robosystem.front_intake.front_one.set(false);
            robosystem.front_intake.front_two.set(false);
        }
    


        
        double rightVolts = 0.25;
        double leftVolts = 0.25;
        double voltAmp = Limelight.getAmp();
        double aim_error = Limelight.getTx(); //add in
        double heading_error = Limelight.getTx();
        double steering_adjust = Constants.Drivetrain.Kp*aim_error;
        double KpAim = -0.1f;
        double min_aim_command = 0.05f;
        double distance_error = Limelight.getTy();
        double KpDistance = -0.1f;
        double distance_adjust = KpDistance * distance_error;






        
    /*if(driverLeft.getRawButton(1)){
            if(Limelight.getTx() > 1.0)
            {
                robosystem.drivetrain.setPower((leftVolts += steering_adjust-Constants.Drivetrain.min_command + distance_adjust) , ( rightVolts -= steering_adjust+Constants.Drivetrain.min_command  - distance_adjust) );
            }else if(Limelight.getTx() < 1.0)
            {
                robosystem.drivetrain.setPower((leftVolts -= steering_adjust-Constants.Drivetrain.min_command + distance_adjust) , ( rightVolts += steering_adjust+Constants.Drivetrain.min_command - distance_adjust) );
            }else if(Limelight.getTx() == 0.0)
            {
                robosystem.drivetrain.setPower(leftVolts, rightVolts);
            }
        }*/
    
  
    
    
        /*if(driverRight.getRawButton(6))
        {
        
            if(Limelight.isTarget() == false) {
                if(NavX.getYaw() < 179 && NavX.getYaw() > 0) {
                    robosystem.drivetrain.setPower(-0.3, 0.05);
                }else if (NavX.getYaw() < 0 && NavX.getYaw() > -179) {
                    robosystem.drivetrain.setPower(0.3, -0.05);
                }
                }else {
                    if (Limelight.getTy() > -2.00) {
                        Limelight.setPipeline(0);
                        if(Limelight.getTx() > 1.0){
                            robosystem.drivetrain.setPower((leftVolts += steering_adjust-Constants.Drivetrain.min_command + distance_adjust) , ( rightVolts -= steering_adjust+Constants.Drivetrain.min_command  - distance_adjust) );
                        }else if(Limelight.getTx() < 1.0){
                            robosystem.drivetrain.setPower((leftVolts -= steering_adjust-Constants.Drivetrain.min_command + distance_adjust) , ( rightVolts += steering_adjust+Constants.Drivetrain.min_command - distance_adjust) );
                        }else if(Limelight.getTx() == 0.0){
                            robosystem.drivetrain.setPower(leftVolts, rightVolts);
                        }
                    }else if(Limelight.getTy() < -2.00) {
                     Limelight.setPipeline(1);
                    
                        if(Limelight.getTx() > 1.0){
                            robosystem.drivetrain.setPower((leftVolts += steering_adjust-Constants.Drivetrain.min_command + distance_adjust) , ( rightVolts -= steering_adjust+Constants.Drivetrain.min_command  - distance_adjust) );
                        }else if(Limelight.getTx() < 1.0){
                            robosystem.drivetrain.setPower((leftVolts -= steering_adjust-Constants.Drivetrain.min_command + distance_adjust) , ( rightVolts += steering_adjust+Constants.Drivetrain.min_command - distance_adjust) );
                        }else if(Limelight.getTx() == 0.0){
                            robosystem.drivetrain.setPower(leftVolts, rightVolts);
        
                    }
                }
            
        }
                  
        }*/
    
}




       
    
        


      
    
    
       
    

        
   

   
    
    private void coDriver()
    {
        if(coDriver.getRawButton(2))
        {
            robosystem.front_intake.setCargoPower(-1.0);
        }else if(coDriver.getRawButton(3))
        {
            robosystem.front_intake.setCargoPower(1.0);
        }else
        {
            robosystem.front_intake.setCargoPower(0.0);
        }

        if(coDriver.getRawButton(2)) {
            robosystem.front_intake.setCargoPower(-1.0);
        }else if(coDriver.getRawButton(3)){
            robosystem.front_intake.setCargoPower(1.0);
        }else {
            robosystem.front_intake.setCargoPower(0.0);
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
