package org.usfirst.frc.team2643.robot;

public class SwitchLeftAndPositionRight {

	public static int autoProgramState = 0;

	public static void runPeriodic(){
		switch(autoProgramState){

		//this will release the arms
		case 0:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 0: Robot will release arms");
			}

			if(!AutoState.armsReleasing)
			{
				RobotMovementMethods.setUpReleaseArms();
				AutoState.armsReleasing = true;
			}
			else if(RobotMovementMethods.executeTurn())
			{
				RobotMovementMethods.finishReleaseArms();
				AutoState.armsReleasing = false;
				autoProgramState++;
			}
			break;
		}
		//the robot will go forward until the it goes a little bit past the switch
		case 1:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 1: Robot will go forward until it is a little bit past the switch");
			}
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
		//the robot will turn left 90 degrees
		case 2:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 2: Robot will turn 90 degrees");
			}

			if(!AutoState.turning){
				RobotMovementMethods.setUpTurn(-EnvironmentVariables.ticksTo90);
				AutoState.turning = false;
			}
			else if(RobotMovementMethods.executeTurn())
			{
				RobotMovementMethods.finishTurn();
				autoProgramState = 3;
				AutoState.turning = false;

			}
			break;
		}

		//the robot will go along the back wall of the switch until it is almost at the end
		case 3:
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 3: Robot will go along the back of the switch until it is almost at the end.");
			}

			if(RobotMap.leftEncoder.get() < EnvironmentVariables.ticksWidthOfSwitch
					&& RobotMap.rightEncoder.get() < EnvironmentVariables.ticksWidthOfSwitch)
			{
				RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
			} 
			else
			{
				autoProgramState = 4;
			}
			break;

			//the robot will rotate 90 right so that it is facing the switch plate 
		case 4:
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 4: the robot will turn 90 degrees right to face the switch plate");
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
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 5: Robot will drop the cube onto the switch");
			}
			autoProgramState = 6;
			//drop the cube onto the switch
			break;

		case 6:
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 6: Program is done");
			}
			break;
		}
	}
}
