package org.usfirst.frc.team2643.robot;

public class EnvironmentVariables {
	
	/**
	 * |----|--|----|
	 * |    |  |    |
	 * |    |  |    | Length of the switch
	 * |----|--|----|
	 *   Width of the switch
	 */
	
	
	//distance from the starting position to the auto line in encoder ticks
	public static final int autoLineDistance = 3703; //TODO

	//encoder ticks it takes for the robot to turn 90 degrees
	//to turn right, make this positive
	//to turn left, make this negative
	
	public static final int ticksTo90 = 150; //TODO

	//the distance in encoder ticks from the starting position to the middle of the field switch
	public static final int ticksToMiddleOfSwitch = 4000; //TODO
	
	//the distance in encoder ticks from the starting position to the fence facing the driver station
	public static final int ticksToBeforeSwitch = 0; //TODO
	
	//encoder ticks for the switch elevator height from bottom limit switch
	public static final int ticksToSwitchHeight = 0; //TODO	
	
	//encoder ticks for length of the switch the switch
	public static final int ticksToPassSwitch = 5000; //TODO

	//encoder ticks for the width of the field switch
	public static final int ticksWidthOfSwitch = 4000; //TODO
	
	//encoder ticks for the length of the field switch
	public static final int ticksLengthOfSwitch = 0;//TODO
}
