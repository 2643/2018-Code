package org.usfirst.frc.team2643.robot;

public class SwitchLeftAndPositionLeft {
	public static void runPeriodic(){
		int state = 1;
		
		switch(state){
			case 1:
				if(RobotMap.debug == true){
					System.out.println("First Case for SwitchLeftAndPositionLeft");
					System.out.println("Robot will move forward until at the switch");
				}
				
				//the robot will move forward until it reaches the switch
				if(Robot.leftEncoder.get() < RobotMap.ticksToSwitch && Robot.rightEncoder.get() < RobotMap.ticksToSwitch){
					Robot.setAll(RobotMap.autoSpeed);
				}else{ //move to state 2
					state = 2;
				}
			case 2:
				if(RobotMap.debug == true){
					System.out.println("Second Case for SwitchLeftAndPositionLeft");
					System.out.println("Robot will turn ninety degrees right to face the switch");
				}
				
				//the robot will turn ninety degrees right to face the switch
				if(!RobotMovementMethods.finishedTurning){
					RobotMovementMethods.turnRight();
				}else{
					state = 3;
				}
				break;
			case 3: 
				//TODO drop the cube onto the switch
				break;
		}
	}
}
