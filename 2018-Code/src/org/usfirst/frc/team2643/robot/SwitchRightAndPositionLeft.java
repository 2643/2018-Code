package org.usfirst.frc.team2643.robot;

public class SwitchRightAndPositionLeft {
	public static int autoProgramState = 0;
	public static void runPeriodic(){
		switch(autoProgramState){

		//the robot will release the arms by going forwards, then backwards
		case 0:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 0: Robot will release arms");
			}

			if(!AutoState.armsReleasing)
			{
				RobotMovementMethods.setUpReleaseArms();
				AutoState.armsReleasing = true;
			}
			else if(RobotMovementMethods.executeTurn())
			{
				RobotMovementMethods.finishReleaseArms();
				autoProgramState++;
				AutoState.armsReleasing = false;
			}
			break;
		}
		//the robot will go slightly past the switch
		case 1: 
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 1: the robot will go slightly past the switch");
			}

			//the robot will move forward until it reaches the switch
			int encoderGoal = EnvironmentVariables.ticksToPassSwitch;
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
		//the robot will turn right 90 degrees
		case 2:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 2: the robot will turn right 90 degrees");
			}

			if(!AutoState.turning)
			{
				RobotMovementMethods.setUpTurn(EnvironmentVariables.ticksTo90);
				AutoState.turning = true;
			}
			else if(RobotMovementMethods.executeTurn())
			{
				RobotMovementMethods.finishTurn();
				AutoState.turning = false;
				autoProgramState++;
			}
			break;
		}
		//the robot will go along the length of the switch but stop right before the end
		case 3:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 3: the robot will go along the back length of the switch, but stop before the end");
			}
			int encoderGoal = EnvironmentVariables.ticksWidthOfSwitch;
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
		//the robot will rotate 90 degrees right to face the switch
		case 4:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 4: the robot will turn 90 degrees right to face the switch");
			}

			if(!AutoState.turning)
			{
				RobotMovementMethods.setUpTurn(EnvironmentVariables.ticksTo90);
				AutoState.turning = true;
			}
			else if(RobotMovementMethods.executeTurn()){
				RobotMovementMethods.finishTurn();
				AutoState.turning = false;
				autoProgramState++;
			}
			break;
		}
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
