/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Ports;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class Elevator {
    private CANSparkMax elevator_spark;
    private CANSparkMax elevator_spark_2;


    private Elevator() {
        elevator_spark = new CANSparkMax(Ports.EXAMPLE_SPARK, MotorType.kBrushless);
        elevator_spark_2 = new CANSparkMax(Ports.EXAMPLE_SPARK_2, MotorType.kBrushless);

        //elevator_spark.setInverted(false);
        //elevator_spark_2.setInverted(true);
    }
       //instance junk
       private static Elevator instance = null;

       public static Elevator getInstance() {
           if(instance == null) instance = new Elevator();
           return instance;
       }

    public void setElevatorPower(double power)
    {
        elevator_spark.set(power);
        elevator_spark_2.set(power);
    }

    public void update()
    {

    }
}
