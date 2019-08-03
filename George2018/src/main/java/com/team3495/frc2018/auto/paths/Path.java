package com.team3495.frc2018.auto.paths;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class Path
{
    public Waypoint[] points;
    public Trajectory trajectory;
    public Trajectory.Config config;
    public TankModifier modifier;
    public Path(Waypoint[] points)
    {
        this.points = points;
        config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
        trajectory = Pathfinder.generate(points, config);
        modifier = new TankModifier(trajectory).modify(0.5);

    }


   

}
