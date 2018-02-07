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
			else if(RobotMovementMethods.executeReleaseArms())
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

		//the robot will go along the back wall of the switch until it is almost at the end
		case 3:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 3: Robot will go along the back of the switch until it is almost at the end.");
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
		//the robot will rotate 90 right so that it is facing the switch plate 
		case 4:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 4: the robot will turn 90 degrees right to face the switch plate");
			}

			if(!AutoState.turning){
				RobotMovementMethods.setUpTurn(-EnvironmentVariables.ticksTo90);
				AutoState.turning = true;
			}
			else if(RobotMovementMethods.executeTurn()){
				RobotMovementMethods.finishTurn();
				AutoState.turning = false;
				autoProgramState++;
			}
			break;
		}
		case 5:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 5: the robot will move 90 to the center of the switch plate");
			}
			int encoderGoal = EnvironmentVariables.ticksLengthOfSwitch/2;
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
		case 6:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 6: Robot will turn to face the switch");
			}
			if(!AutoState.turning){
				RobotMovementMethods.setUpTurn(-EnvironmentVariables.ticksTo90);
				AutoState.turning = true;
			}
			else if(RobotMovementMethods.executeTurn()){
				RobotMovementMethods.finishTurn();
				AutoState.turning = false;
				autoProgramState++;
			}
			break;
		}
		case 7:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 7: Robot will drop cube");
			}
			//DROP DAT CUBE 
			//...no
			//[GUNSHOTS]
			// ooOOoof!!!!
			//HE DED
			// r. i. p.
			break;
		}
		case 8:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 8: Program is done");
			}
			break;
		}
		}
	}
}
