package org.usfirst.frc.team2643.robot;

public class RobotMap {
	//boolean that tells if the motion to released the intake arms has been done
	public static boolean armsReleased = false;
	
	//the starting position for the robot at the beginning of the match
	//1-left
	//2-middle
	//3-right
	public static int position = 0;
	
	//the autonomous program to run
	//case 0: the robot just crosses the auto line 
	//case 1: switch-left position-left
	static int state = 0;
}
