package org.usfirst.frc.team2643.robot;

/**
 * This class gathers variables for the state and auto machine into one class
 * 
 */
public class AutoState {
	
	
	public static int robotState;
	
	//states the robot may be in
	public static final byte NOTHING = 0;
	public static final byte TURNING = 1;
	public static final byte MOVING = 2;
	public static final byte ELEVATING = 3;
	
	//if the arms have been released
	public static boolean armsReleased = false;
	public static boolean movingForwardToReleaseArm = false;
	public static boolean movingBackwardToRelaseArm = false;
	
	
	public static boolean turning = false;
	public static int leftEncoderGoal;
	public static int rightEncoderGoal;

	
	


}
/*
 * ArrayList taskList = new ArrayList<>
 * 
 */
