package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class RobotMovementMethods {
	
	
	public static boolean stoppedBeforeTurning = false;
	public static boolean finishedTurning = false;
	
	
	//TODO TEST THIS SEPERATELY BEFORE TESTING AUTO CODE!!!
	//turns the robot right
	public static void turnRight(){
		int state = 1;
		
		switch(state){
			case 1:
				if(RobotMap.debug == true)
					System.out.println("First Case for turnRight() method");
				
				
				if(stoppedBeforeTurning == false){
					Robot.setAll(0);
					Robot.leftEncoder.reset();
					Robot.rightEncoder.reset();
					stoppedBeforeTurning = true;
					finishedTurning = false;
					state = 2;
				}
				break;
			case 2:
				if(RobotMap.debug == true)
					System.out.println("Second case for turnRight() method");
				
				
				if(-Robot.leftEncoder.get() < RobotMap.ticksTo90 && Robot.rightEncoder.get() < RobotMap.ticksTo90){
					Robot.setLeftMotors(0.4);
					Robot.setRightMotors(-0.4);
				}else{
					Robot.setAll(0);
					stoppedBeforeTurning = false;
					finishedTurning = true;
				}
				break;
					
		}
	}
	
	//TODO TEST THIS SEPERATELY BEFORE TESTING AUTO CODE!!!
	//turns the robot left
	public static void turnLeft(){
		int state = 1;
		
		switch(state){
			case 1: 
				if(RobotMap.debug == true)
					System.out.println("First Case for turnLeft() method");
				
				if(stoppedBeforeTurning == false){
					Robot.setAll(0);
					stoppedBeforeTurning = true;
					finishedTurning = false;
					state = 2;
				}
				break;
				
			case 2:
				if(RobotMap.debug == true)
					System.out.println("Second Case for turnLeft() method");
				
				if((Robot.leftEncoder.get() < RobotMap.ticksTo90) && (-Robot.rightEncoder.get() < RobotMap.ticksTo90)){
					Robot.setLeftMotors(-0.4);
					Robot.setRightMotors(0.4);
				}else{
					Robot.setAll(0);
					stoppedBeforeTurning = false;
					finishedTurning = true;
				}
				break;
		}
	}
}
