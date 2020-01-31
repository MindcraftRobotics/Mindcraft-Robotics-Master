package frc.robot;

public class Util
{
    /**
     * make sure low is negative and high is positive
     * @param input to be bounded
     * @param low threshold in the negative direction
     * @param high threshold in the positive direction
     * @param min minimum bound
     * @param max maximum bound
     * @return input but bounded and deadbanded
     */
    public static double deadbandAndBound(double input, double low, double high, double min, double max)
    {
        if(low <= input && input <= high)
        {
            return 0.0;
        }
        if(min >= input)
        {
            return min;
        }
        if(input >= max)
        {
            return max;
        }
        if(input >= high)
        {
            return (input - high)*(max/(max - high));
        }
        if(input <= low)
        {
            return (input - low)*(min/(min - low));
        }
        return 0;//this should never happen boi
    }
}