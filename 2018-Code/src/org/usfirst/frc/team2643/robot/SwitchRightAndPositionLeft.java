package org.usfirst.frc.team2643.robot;

public class SwitchRightAndPositionLeft {
	public static int autoProgramState = 0;
	public static void runPeriodic(){
		switch(autoProgramState){
			//the robot 
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
				
			//the robot will go slightly past the switch
			case 1: 
				if(RobotMap.DEBUG){
					System.out.println("case 1 for SwitchRightAndPositionLeft");
				}
				
				//the robot will move forward until it reaches the switch
				if(RobotMap.leftEncoder.get() < EnvironmentVariables.ticksToSwitch 
						&& RobotMap.rightEncoder.get() < EnvironmentVariables.ticksToSwitch)
				{
					RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
				}
				else
				{
					autoProgramState = 2;
				}
				break;
			
			//the robot will turn left 90 degrees
			case 2:
				if(RobotMap.DEBUG){
					System.out.println("case 3 for SwitchRightAndPositionLeft");
				}
					
				//the robot will turn ninety degrees right
				if(!AutoState.turning)
				{
					RobotMovementMethods.setUpTurn(EnvironmentVariables.ticksTo90);
					AutoState.turning = true;
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
			
			//the robot will go along the length of the switch but stop right before the end
			case 3:
				if(RobotMap.DEBUG){
					System.out.println("case 4 for SwitchRightAndPositionLeft");
				}
				
				if(RobotMap.leftEncoder.get() < EnvironmentVariables.ticksForLengthOfSwitch
						&& RobotMap.rightEncoder.get() < EnvironmentVariables.ticksForLengthOfSwitch){
					RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
				}
				else{
					autoProgramState = 4;
				}
				break;
				
			//the robot will rotate 90 degrees right to face the switch
			case 4:
				if(RobotMap.DEBUG){
					System.out.println("case 4 for SwitchRightAndPositionLeft");
				}
				
				if(!AutoState.turning)
				{
					RobotMovementMethods.setUpTurn(EnvironmentVariables.ticksTo90);
					AutoState.turning = true;
				}
				else
				{
					boolean isFinished = RobotMovementMethods.executeTurn();
					if(isFinished){
						RobotMovementMethods.finishTurn();
						autoProgramState = 4;
						AutoState.turning = false;
					}
				}
				break;
				
			//the robot will drop the cube onto the switch
			case 5:
				if(RobotMap.DEBUG){
					System.out.println("case 5 for SwitchRightAndPositionLeft");
				}
				
				//TODO
		}
	}
}
