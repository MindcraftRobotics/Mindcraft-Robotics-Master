package com.team3495.frc2018.auto.modes;

import com.team3495.frc2018.auto.actions.*;

public class StartRightScaleRight extends AutoModeBase 
{
    private boolean dropCube;
    public StartRightScaleRight(boolean dropCube)
    {
        this.dropCube = dropCube;
    }

    @Override public void routine()
    {
        runAction(new CloseIntakeAction());
        runAction(new VoltageDriveAction(4.5,4.5));
        runAction(new WaitSecondsAction(4.05));
        runAction(new VoltageDriveAction(2.50,5.10));
        runAction(new WaitSecondsAction(1.30));
        runAction(new VoltageDriveAction(0,0));
        if(dropCube)
        {
            runAction(new RaiseArmAction());
            runAction(new WaitSecondsAction(1.50));
            runAction(new IdleArmAction());
            runAction(new WaitSecondsAction(1.5));
            runAction(new OuttakingAction());
            runAction(new WaitSecondsAction(2));
            runAction(new IdleIntakeAction());
        }
       
        

    }
}