package com.team3495.frc2018.auto.modes;

import com.team3495.frc2018.auto.actions.*;

public class DoNothing extends AutoModeBase 
{
    @Override public void routine()
    {
        runAction(new CloseIntakeAction());
        runAction(new VoltageDriveAction(5.0,5.0));
        runAction(new WaitSecondsAction(5.0));
        runAction(new VoltageDriveAction(0,0));
       
    }
}