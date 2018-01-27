package org.usfirst.frc.team2643.robot;

public class SwitchLeftAndPositionMiddle {
	public static void runPeriodic(){
		switch(RobotMap.autoProgramState){
			case 1: 
				if(RobotMap.debug == true){
					System.out.println("First Case for the SwitchLeftAndPositionMiddle");
					System.out.println("The robot will move forward slightly past the switch");
				}
				
				if(Robot.leftEncoder.get() < RobotMap.ticksToPassSwitch 
						&& Robot.rightEncoder.get() < RobotMap.ticksToPassSwitch){
					Robot.setAll(RobotMap.autoSpeed);
				}
				else{
					RobotMap.autoProgramState = 2;
				}
				break;
				
			case 2:
				if(RobotMap.debug == true){
					System.out.println("Second Case for the SwitchLeftAndPositionMiddle");
					System.out.println("The robot will turn ninety degrees right");
				}
				
				if(!RobotMap.finishedTurning){
					RobotMovementMethods.turnRight();
				}else{
					RobotMap.autoProgramState = 3;
				}
				break;
				
			case 3:
				if(RobotMap.debug == true){
					System.out.println("Third Case for the SwitchLeftAndPositionMidle switch statement.");
					System.out.println("The robot will move forward until at the other end of the switch");
				}
				
				if(Robot.leftEncoder.get() < RobotMap.ticksForLengthOfSwitch
						&& Robot.rightEncoder.get() < RobotMap.ticksForLengthOfSwitch){
					Robot.setAll(RobotMap.autoSpeed);
				}else{
					RobotMap.autoProgramState = 3;
				}
				break;
		}
	}
}
