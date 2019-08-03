package com.team3495.frc2018.auto.modes;

import com.team3495.frc2018.auto.actions.*;

public class StartLeftSwitchLeft extends AutoModeBase 
{
    private boolean dropCube;
    public StartLeftSwitchLeft(boolean dropCube)
    {
        this.dropCube = dropCube;
    }

    @Override public void routine()
    {
        runAction(new CloseIntakeAction());
        runAction(new VoltageDriveAction(4.6,3.5));
        runAction(new WaitSecondsAction(2.495));
        runAction(new VoltageDriveAction(0,0));
        if(dropCube)
        {
            runAction(new RaiseArmAction());
            runAction(new WaitSecondsAction(1));
            runAction(new IdleArmAction());
            runAction(new OuttakingAction());
            runAction(new WaitSecondsAction(2));
            runAction(new IdleIntakeAction());
        }
       
        

    }
}