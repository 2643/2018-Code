package org.usfirst.frc.team2643.robot;

public class SwitchRightAndPositionMiddle {
	
	public static int autoProgramState = 0;
	
	public static void runPeriodic(){
		switch(autoProgramState){
		
			//the robot will release the arms
			case 0:
				if(RobotMap.DEBUG){
					System.out.println("SwitchRightAndPositionMiddle Case 0: Robot turning to release the arms");
				}
				
				if(!AutoState.armsReleasing)
				{
					RobotMovementMethods.setUpReleaseArms();
					AutoState.armsReleasing = true;
				}
				else
				{
					boolean isFinished = RobotMovementMethods.executeReleaseArms();
					if(isFinished)
					{
						RobotMovementMethods.finishReleaseArms();
						autoProgramState = 1;
						AutoState.armsReleasing = false;
					}
				}
				break;
			
			//the robot will move forward until it hits the switch
			case 1:
				if(RobotMap.DEBUG){
					System.out.println("SwitchRightAndPositionMiddle Case 1: Robot moving forward until it hits the switch");
				}
				
				if(RobotMap.leftEncoder.get() < EnvironmentVariables.ticksToBeforeSwitch 
						&& RobotMap.rightEncoder.get() < EnvironmentVariables.ticksToBeforeSwitch){
					RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
				}
				else{
					RobotMovementMethods.stopAll();
					autoProgramState = 2;
				}
				break;
				
			//the robot will drop the cube onto the switch
			case 2: 
				if(RobotMap.DEBUG){
					System.out.println("SwitchRightAndPositionMiddle Case 2: the robot will drop the cube onto the switch");
				}
				autoProgramState = 3;
				//TODO
				break;
				
			case 3:
				if(RobotMap.DEBUG){
					System.out.println("SwitchRightAndPositionMiddle Case 3: Program is done");
				}
				break;
		}
	}
}
