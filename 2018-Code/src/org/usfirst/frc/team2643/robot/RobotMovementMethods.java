package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class RobotMovementMethods {
	
	
	public static boolean stoppedBeforeTurning = false;
	public static boolean finishedTurning = false;
	
	
	//TODO TEST THIS SEPERATELY BEFORE TESTING AUTO CODE!!!
	/**
	 * turnRight()
	 * 		makes the robot turn 90 degrees
	 */
	public static void turnRight(){
		int state = 1;
		
		switch(state){
			case 1:
				//if debug is true, print debug statements
				if(RobotMap.debug == true)
					System.out.println("First Case for turnRight() method");
				
				//if the robot has not stopped before turning...
				if(stoppedBeforeTurning == false){
					
					//set all of the drive motors to zero
					Robot.setAll(0);
					
					//reset the encoders before turning
					Robot.leftEncoder.reset();
					Robot.rightEncoder.reset();
					
					//the robot has stopped before turning
					stoppedBeforeTurning = true;
					
					//but it has not turned yet
					finishedTurning = false;
					state = 2;
				}
				break;
			case 2:
				
				//if the debug statements are set to true, print the debug statements
				if(RobotMap.debug == true)
					System.out.println("Second case for turnRight() method");
				
				//if the robot has not turned 90 degrees,
				if(Math.abs(Robot.leftEncoder.get()) < RobotMap.ticksTo90 && Math.abs(Robot.rightEncoder.get()) < RobotMap.ticksTo90){
					
					//set the left and right motors to opposite values so that it rotates
					Robot.setLeftMotors(0.4);
					Robot.setRightMotors(-0.4);
					
				}else{
					
					//once it is finished turning, set all the drive motors to zero
					Robot.setAll(0);
					
					//reset the encoders after turning
					Robot.leftEncoder.reset();
					Robot.rightEncoder.reset();
					
					//the robot has finished turning and stoppedBeforeTurning needs to be reset 
					stoppedBeforeTurning = false;
					finishedTurning = true;
				}
				break;					
		}
	}
	
	//TODO TEST THIS SEPERATELY BEFORE TESTING AUTO CODE!!!
	/**
	 * turnLeft() makes the robot turn left 90 degrees
	 */
	public static void turnLeft(){
		int state = 1;
		
		switch(state){
			case 1: 
				
				//if debug is set to true. then print the debug statements
				if(RobotMap.debug == true)
					System.out.println("First Case for turnLeft() method");
			
				//if the robot has not stopped before turning...
				if(stoppedBeforeTurning == false){
					
					//set all of the drive motors to zero
					Robot.setAll(0);
					
					//reset the encoders before turning
					Robot.leftEncoder.reset();
					Robot.rightEncoder.reset();
					
					//the robot has stopped...
					stoppedBeforeTurning = true;
				
					//but it has not finished turning
					finishedTurning = false;
					
					state = 2;
				}
				break;
				
			case 2:
				
				//if debug is true, print the debug statements
				if(RobotMap.debug == true)
					System.out.println("Second Case for turnLeft() method");
				
				//if the robot has not turned ninety degrees...
				if(Math.abs(Robot.leftEncoder.get()) < RobotMap.ticksTo90 && Math.abs(-Robot.rightEncoder.get()) < RobotMap.ticksTo90){
					
					//set the drive motors to opposite values to rotate the robot
					Robot.setLeftMotors(-0.4);
					Robot.setRightMotors(0.4);
				}
				else //else the robot has turned ninety degrees
				{
					//set the motors to zero after turning
					Robot.setAll(0);
					
					//reset the encoders after turning
					Robot.leftEncoder.reset();
					Robot.rightEncoder.reset();
					
					//reset this after turning
					stoppedBeforeTurning = false;
					
					//the robot has finished turning
					finishedTurning = true;
				}
				break;
		}
	}
	
	public void liftToSwitchHeight() {
		
	}
}