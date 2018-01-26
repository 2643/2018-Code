package org.usfirst.frc.team2643.robot;

public class SwitchLeftAndPositionLeft {
	public static void runPeriodic(){
		
		switch(RobotMap.autoProgramState){
			case 1:
				if(RobotMap.debug == true){
					System.out.println("First Case for SwitchLeftAndPositionLeft");
					System.out.println("Robot will move forward until at the switch");
				}
				
				//the robot will move forward until it reaches the switch
				if(Robot.leftEncoder.get() < RobotMap.ticksToSwitch && Robot.rightEncoder.get() < RobotMap.ticksToSwitch){
					Robot.setAll(RobotMap.autoSpeed);
				}else{ //move to state 2
					RobotMap.autoProgramState = 2;
				}
			case 2:
				if(RobotMap.debug == true){
					System.out.println("Second Case for SwitchLeftAndPositionLeft");
					System.out.println("Robot will turn ninety degrees right to face the switch");
				}
				
				//the robot will turn ninety degrees right to face the switch
				if(!RobotMap.finishedTurning){
					RobotMovementMethods.turnRight();
				}else{
					RobotMap.autoProgramState = 3;
				}
				break;
			case 3: 
				//TODO drop the cube onto the switch
				break;
		}
	}
}
