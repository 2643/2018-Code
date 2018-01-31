package org.usfirst.frc.team2643.robot;

public class SwitchRightAndPositionRight {
	
	public static int autoProgramState = 0;
	
	public static void runPeriodic(){
		switch(autoProgramState){
		case 0:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 0: Robot will release arms");
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

		//the robot will move forward until right next to the switch
		case 1:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight case 1: the robot will move forward until it is right next to the switch");
			}

			//the robot will move forward until it reaches the switch
			if(RobotMap.leftEncoder.get() < EnvironmentVariables.ticksToMiddleOfSwitch 
					&& RobotMap.rightEncoder.get() < EnvironmentVariables.ticksToMiddleOfSwitch)
			{
				RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
			}
			else
			{
				autoProgramState = 2;
			}
			break;

		//the robot will turn ninety degrees left to face the switch
		case 2:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 2: the robot will turn 90 degrees left to face the switch");
			}

			if(!AutoState.turning)
			{
				RobotMovementMethods.setUpTurn(-EnvironmentVariables.ticksTo90);
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

			//the robot will drop the cube on the switch
		case 3: 
			//TODO drop the cube onto the switch
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 3: the robot will drop the cube onto the switch");
			}
			autoProgramState = 4;
			break;
		case 4:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 4: program is done");
			}
			break;
		}
	}
}
