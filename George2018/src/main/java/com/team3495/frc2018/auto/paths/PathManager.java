package com.team3495.frc2018.auto.paths;

public class PathManager
{
    public static Path straightLine;
    private PathManager()
    {
        straightLine = new Path(Paths.Test.straightLine);
    }

    private static PathManager instance = null;
    
    public static PathManager getInstance() {
        if(instance == null) instance = new PathManager();
        return instance;
    }
}
