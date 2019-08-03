package com.team3495.frc2018.auto.modes;

import com.team3495.frc2018.auto.actions.*;

public class StartRightSwitchRight extends AutoModeBase 
{
    private boolean dropCube;
    public StartRightSwitchRight(boolean dropCube)
    {
        this.dropCube = dropCube;
    }

    @Override public void routine()
    {
        runAction(new CloseIntakeAction());
        runAction(new VoltageDriveAction(3.5,4.6));
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