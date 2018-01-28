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
	public static boolean armsReleasing = false;
	public static boolean movingForwardToReleaseArms = false;
	public static boolean movingBackwardToReleaseArms = false;
	public static final int armsEncoderGoal = 500;
	
	public static boolean turning = false;
	public static int leftEncoderGoal;
	public static int rightEncoderGoal;

	
	


}
