package org.usfirst.frc.team2643.robot;

public class SwitchRightAndPositionMiddle {

	public static int autoProgramState = 0;

	public static void runPeriodic(){
		switch(autoProgramState){

		//the robot will release the arms
		case 0:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionMiddle Case 0: Robot turning to release the arms");
			}

			if(!AutoState.armsReleasing)
			{
				RobotMovementMethods.setUpReleaseArms();
				AutoState.armsReleasing = true;
			}
			else if(RobotMovementMethods.executeReleaseArms())
			{
				RobotMovementMethods.finishReleaseArms();
				AutoState.armsReleasing = false;
				autoProgramState++;

			}
			break;
		}
		//the robot will move forward until it hits the switch
		case 1:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionMiddle Case 1: Robot moving forward until it hits the switch");
			}
			int encoderGoal = EnvironmentVariables.ticksToBeforeSwitch;
			if(!AutoState.moving)
			{
				RobotMovementMethods.setUpMove(encoderGoal);
				AutoState.moving = true;
			}
			else if(RobotMovementMethods.executeMove())
			{
				RobotMovementMethods.finishMove();
				AutoState.moving = false;
				autoProgramState++;
			}
			break;
		}
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
