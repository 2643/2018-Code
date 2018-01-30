package org.usfirst.frc.team2643.robot;

public class SwitchLeftAndPositionRight {
	
	public static int autoProgramState = 1;
	public static void runPeriodic(){
		switch(autoProgramState){
		
			//this will release the arms
			case 0:
				if(RobotMap.DEBUG){
					System.out.println("case 0 for SwitchRightAndPositionLeft");
				}

				if(!AutoState.armsReleasing)
				{
					RobotMovementMethods.setUpReleaseArms();
					AutoState.armsReleasing = true;
				}
				else
				{
					boolean isFinished = RobotMovementMethods.executeTurn();
					if(isFinished)
					{
						RobotMovementMethods.finishReleaseArms();
						autoProgramState = 1;
						AutoState.armsReleasing = false;
					}
				}
				break;
			//the robot will go forward until the it goes a little bit past the switch
			case 1:
				if(RobotMap.DEBUG){
					System.out.println("First case for the SwitchLeftAndPositionRight program");
				}
				
				if(RobotMap.leftEncoder.get() < EnvironmentVariables.ticksToPassSwitch 
						&& RobotMap.rightEncoder.get() < EnvironmentVariables.ticksToPassSwitch){
					RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
				}else{
					autoProgramState = 2;
				}
				break;
			
				//the robot will turn right 90 degrees
			case 2:
				if(RobotMap.DEBUG){
					System.out.println("Second case for the SwitchLeftAndPositionRight program");
				}
				
				if(!AutoState.turning){
					RobotMovementMethods.setUpTurn(EnvironmentVariables.ticksTo90);
					AutoState.turning = false;
				}
				else
				{
					boolean isFinished = RobotMovementMethods.executeTurn();
					if(isFinished)
					{
						RobotMovementMethods.finishTurn();
						autoProgramState = 3;
						AutoState.turning = false;
					}
				}
				break;
				
			//the robot will go along the back wall of the switch until it is almost at the end
			case 3:
				if(RobotMap.DEBUG){
					System.out.println("Third case for the SwitchLeftAndPositionRight program");
				}
				
				if(RobotMap.leftEncoder.get() < EnvironmentVariables.ticksWidthOfSwitch
						&& RobotMap.rightEncoder.get() < EnvironmentVariables.ticksWidthOfSwitch){
					RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
				} else{
					autoProgramState = 4;
				}
				break;
			
			//the robot will rotate 90 right so that it is facing the switch plate 
			case 4:
				if(RobotMap.DEBUG){
					System.out.println("Fourth case for the SwitchLeftAndPositionRight program");
				}
				
				if(!AutoState.turning){
					RobotMovementMethods.setUpTurn(EnvironmentVariables.ticksTo90);
					AutoState.turning = true;
				}
				else{
					boolean isFinished = RobotMovementMethods.executeTurn();
					if(isFinished){
						RobotMovementMethods.finishTurn();
						autoProgramState = 5;
						AutoState.turning = false;
					}
				}
				break;
				
			//the robot will drop the cube onto the switch
			case 5:
				//drop the cube onto the switch
				
			case 6:
				break;
		}
	}
}
