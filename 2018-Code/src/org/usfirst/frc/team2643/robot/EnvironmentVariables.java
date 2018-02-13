package org.usfirst.frc.team2643.robot;

public class EnvironmentVariables {
	/**
	 * Divide all encoder values by two for accuracy
	 */
	/**
	 * |----|--|----|
	 * |    |  |    |
	 * |    |  |    | Length of the switch
	 * |----|--|----|
	 *   Width of the switch
	 */
	
	
	//distance from the starting position to the auto line in encoder ticks
	public static final int autoLineDistance = 0; //TODO

	//encoder ticks it takes for the robot to turn 90 degrees
	//to turn right, make this positive
	//to turn left, make this negative
	
	public static final int ticksTo90 = 0; //TODO

	//the distance in encoder ticks from the starting position to the middle of the field switch
	public static final int ticksToMiddleOfSwitch = 0; //TODO
	
	//the distance in encoder ticks from the starting position to the fence facing the driver station
	public static final int ticksToBeforeSwitch = 0; //TODO
	
	//encoder ticks for the switch elevator height from bottom limit switch
	public static final int ticksToSwitchHeight = 0; //TODO	
	
	//encoder ticks for length of the switch the switch
	public static final int ticksToPassSwitch = 0; //TODO

	//encoder ticks for the width of the field switch
	public static final int ticksWidthOfSwitch = 0; //TODO
	
	//encoder ticks for the length of the field switch
	public static final int ticksLengthOfSwitch = 0;//TODO
	
	public static final int lsEncodersToFeet = 0; //TODO
	public static final int lsEncodersToInches = 0; //TODO
	
	public static final int defaultPID = 0;
	public static final double PIDF = 0.2;
	public static final double PIDP = 0.2;
	public static final double PIDI = 0;
	public static final double PIDD = 0;
	
	public static final int maxEncoderValue = Integer.MAX_VALUE; //TODO
	public static final int moveUpSpeed = 0;
	public static final int moveDownSpeed = 0;
}
