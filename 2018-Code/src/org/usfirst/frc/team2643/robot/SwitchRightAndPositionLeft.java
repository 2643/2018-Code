package org.usfirst.frc.team2643.robot;

public class SwitchRightAndPositionLeft {
	public static int autoProgramState = 0;
	public static void runPeriodic(){
		switch(autoProgramState){
		
			//the robot will release the arms by going forwards, then backwards
			case 0:
				if(RobotMap.DEBUG){
					System.out.println("SwitchRightAndPositionLeft Case 0: Robot will release arms");
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
					System.out.println("SwitchRightAndPositionLeft Case 1: the robot will go slightly past the switch");
				}
				
				//the robot will move forward until it reaches the switch
				if(RobotMap.leftEncoder.get() < EnvironmentVariables.ticksToPassSwitch 
						&& RobotMap.rightEncoder.get() < EnvironmentVariables.ticksToPassSwitch)
				{
					RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
				}
				else
				{
					autoProgramState = 2;
				}
				break;
			
			//the robot will turn right 90 degrees
			case 2:
				if(RobotMap.DEBUG){
					System.out.println("SwitchRightAndPositionLeft Case 2: the robot will turn right 90 degrees");
				}
	
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
					System.out.println("SwitchRightAndPositionLeft Case 3: the robot will go along the back length of the switch, but stop before the end");
				}
				
				if(RobotMap.leftEncoder.get() < EnvironmentVariables.ticksWidthOfSwitch
						&& RobotMap.rightEncoder.get() < EnvironmentVariables.ticksWidthOfSwitch){
					RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
				}
				else{
					autoProgramState = 4;
				}
				break;
				
			//the robot will rotate 90 degrees right to face the switch
			case 4:
				if(RobotMap.DEBUG){
					System.out.println("SwitchRightAndPositionLeft Case 4: the robot will turn 90 degrees right to face the switch");
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
						autoProgramState = 5;
						AutoState.turning = false;
					}
				}
				break;
				
			//the robot will drop the cube onto the switch
			case 5:
				if(RobotMap.DEBUG){
					System.out.println("SwitchRightAndPositionLeft Case 5: the robot will drop the cube onto the switch");
				}
				autoProgramState = 6;
				//TODO
				
			//the program is done
			case 6: 
				if(RobotMap.DEBUG){
					System.out.println("SwitchRightAndPositionLeft Case 6: Program is done");
				}
				break;
		}
	}
}
