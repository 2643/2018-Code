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
		
	public static final int lsEncodersToFeet = 0; //TODO
	public static final int lsEncodersToInches = 0; //TODO
	
	public static final int defaultPID = 0;
	public static final double PIDF = 0.5;
	public static final double PIDP = 0.5;
	public static final double PIDI = 0;
	public static final double PIDD = 0;
	
	public static final int maxEncoderValue = 12750; //TODO //From 15000
	public static final int moveUpSpeed = 0;
	public static final int moveDownSpeed = 0;
}
