package org.usfirst.frc.team2643.robot;

public class RobotMap {

	//boolean that tells if the motion to released the intake arms has been done
	public static boolean armsReleased = false;
	
	//for the autonomous state machine
	//DO NOT TOUCH THIS
	static int state = 0;
	
	//distance from the starting position to the auto line in encoder ticks
	static int autoLineDistance = 0; //TODO
	
	//the speed for the robot during autonomous 
	public static double autoSpeed = 0; //TODO
	
	//encoder ticks it takes for the robot to turn 90 degrees
	public static int ticksTo90 = 0; //TODO
	
	//makes it so that it only prints statements when debug is true
	public static boolean debug = false;
	
	//the distance in encoder ticks from the starting position to the field switch
	public static int ticksToSwitch = 0; //TODO
	
	//encoder ticks for the switch elevator height from bottom limit switch
	public static int ticksToSwitchHeight = 0; //TODO
	
	//encoder ticks for slightly past the switch
	public static int ticksToPassSwitch = 0; //TODO
	
	//encoder ticks for the length of the field switch
	public static int ticksForLengthOfSwitch = 0; //TODO
	
	//the state integer for the switch statement in turnRight()
	public static int turnRightState = 1;
	
	//the state integer for the swtich statement in turnLeft()
	public static int turnLeftState = 1;
	
	//the variable that indicates when the robot has finished turning
	public static boolean finishedTurning = false;
	
	//the state integer for all of the auto programs
	public static int autoProgramState = 1;

}
