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
	public static final byte INTAKING = 4;
	
	public static boolean turning = false;
	public static boolean elevating = false;
	public static boolean moving = false;
	public static boolean inttaking = false;
}
