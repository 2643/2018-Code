package org.usfirst.frc.team2643.robot;

public class RobotMovementMethods {
	/*Notes: 
	 * the wheels on the outside of the turn need to moving faster
	 * than the wheels on the inside of the turn 
	 */
	
	//TODO TEST THIS SEPERATELY BEFORE TESTING AUTO CODE!!!
	//turns the robot right
	public static void turnRight(){
		Robot.setLeftMotors(0.4);
		Robot.setRightMotors(-0.2);
	}
	
	//TODO TEST THIS SEPERATELY BEFORE TESTING AUTO CODE!!!
	//turns the robot left
	public static void turnLeft(){
		Robot.setRightMotors(0.4);
		Robot.setLeftMotors(-0.2);
	}
}
