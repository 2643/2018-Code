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
	public static final int armsForwardEncoderGoal = 500;
	public static final int armsBackwardEncoderGoal = 400;
	
	public static boolean turning = false;
	public static int leftEncoderGoal;
	public static int rightEncoderGoal;

	
	public static boolean moving = false;
	public static int encoderGoal;
	
	// Power safeties
	// If limitMotorOverElevator is true, if the elevator is drawing
	// a lot of power, it will limit how fast the motors can go.
	// If limitMotorOverElevator is false, and the motors are drawing
	// a lot of power, it will not allow you to raise the elevator.
	public static double motorPower = 0;
	public static double motorPowerLimit = 0.3; // Indicator for when to limit elevator, or upper limit for motors if elevator is limiting
	public static boolean limitMotorOverElevator = true;
	public static double elevatorPowerLimit = 0.4; // Indicator for when to limit motor, or upper limit for elevator if motors are limiting
	public static double elevatorPower = 0;
	//TODO: Check values (make sure they make sense)
}
