package com.team3495.frc2018.auto.modes;

import com.team3495.frc2018.auto.actions.*;
import com.team3495.frc2018.auto.paths.PathManager;

public class PathTester extends AutoModeBase 
{
    @Override public void routine()
    {
        runAction(new CloseIntakeAction());
        runAction(new FollowPathAction(PathManager.straightLine));
       
    }
}