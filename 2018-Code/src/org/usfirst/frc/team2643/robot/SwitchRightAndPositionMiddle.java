package org.usfirst.frc.team2643.robot;

public class SwitchRightAndPositionMiddle {
	
	public static int autoProgramState = 0;
	public static void runPeriodic(){
		switch(autoProgramState){
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
					boolean isFinished = RobotMovementMethods.executeTurn();
					if(isFinished)
					{
						RobotMovementMethods.finishReleaseArms();
						autoProgramState = 1;
						AutoState.armsReleasing = false;
					}
				}
				break;
				
			case 1:
				if(RobotMap.DEBUG){
					System.out.println("SwitchRightAndPositionMiddle Case 1: Robot moving forward until it hits the switch");
				}
				
				if(RobotMap.leftEncoder.get() < EnvironmentVariables.ticksToBeforeSwitch 
						&& RobotMap.rightEncoder.get() < EnvironmentVariables.ticksToBeforeSwitch){
					RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
				}
				else{
					autoProgramState = 2;
				}
				break;
				
			case 2: 
				//drop the cube
				
			case 3:
				break;
		}
	}
}
