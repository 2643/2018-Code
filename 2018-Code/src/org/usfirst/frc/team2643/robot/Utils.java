package org.usfirst.frc.team2643.robot;

public class Utils {
	public static int getSign(double num)
	{
		return (int)Math.signum(num);
	}
	
	/**
	 * Checks if the current encoder value has reached its goal
	 * @param val the current value of the encoder
	 * @param goal the goal to be reached
	 * @return true if the goal has been reached, false if the goal has not
	 */
	public static boolean checkIfReachedGoal(double val, double goal)
	{
		if(goal == 0)
		{
			return true;
		}
		else if(goal < 0)
		{
			if(val < goal)
			{
				return true;
			}
		}
		else if(goal > 0)
		{
			if(val > goal)
			{
				return true;
			}
		}
		return false;
}
	
}
