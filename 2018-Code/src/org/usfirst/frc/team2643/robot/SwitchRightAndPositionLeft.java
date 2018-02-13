package org.usfirst.frc.team2643.robot;

public class SwitchRightAndPositionLeft {
	public static int autoProgramState = 0;
	public static void runPeriodic(){
		switch(autoProgramState){

		//the robot will release the arms
		case 0:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 0: Robot will release arms");
			}

			if(!AutoState.armsReleasing)
			{
				Robot.drive.setUpReleaseArms();
				AutoState.armsReleasing = true;
			}
			else if(Robot.drive.executeTurn())
			{
				Robot.drive.finishReleaseArms();
				autoProgramState++;
				AutoState.armsReleasing = false;
			}
			break;
		}
		//the robot will go past the switch
		case 1: 
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 1: Move forward until past the switch");
			}

			//the robot will move forward until it reaches the switch
			int encoderGoal = EnvironmentVariables.ticksToPassSwitch;
			if(!AutoState.moving)
			{
				Robot.drive.setUpMove(encoderGoal);
				AutoState.moving = true;
			}
			else if(Robot.drive.executeMove())
			{
				Robot.drive.finishMove();
				AutoState.moving = false;
				autoProgramState++;
			}
			break;
		}
		//the robot will turn right 90 degrees
		case 2:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 2: Turn 90 degrees right");
			}

			if(!AutoState.turning)
			{
				Robot.drive.setUpTurn(EnvironmentVariables.ticksTo90);
				AutoState.turning = true;
			}
			else if(Robot.drive.executeTurn())
			{
				Robot.drive.finishTurn();
				AutoState.turning = false;
				autoProgramState++;
			}
			break;
		}
		//the robot will move along the back wall and stop after it passes the left end of the switch
		case 3:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 3: Move along back wall and stop after it passes the left end of the switch");
			}
			int encoderGoal = EnvironmentVariables.ticksWidthOfSwitch;
			if(!AutoState.moving)
			{
				Robot.drive.setUpMove(encoderGoal);
				AutoState.moving = true;
			}
			else if(Robot.drive.executeMove())
			{
				Robot.drive.finishMove();
				AutoState.moving = false;
				autoProgramState++;
			}
			break;
		}
		//the robot will turn 90 degrees right 
		case 4:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 4: Turn 90 degrees right");
			}

			if(!AutoState.turning)
			{
				Robot.drive.setUpTurn(EnvironmentVariables.ticksTo90);
				AutoState.turning = true;
			}
			else if(Robot.drive.executeTurn()){
				Robot.drive.finishTurn();
				AutoState.turning = false;
				autoProgramState++;
			}
			break;
		}
		//the robot will move forward until in the middle of the length of the switch
		case 5:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 5: Move forward until in the middle of the length of the switch");
			}
			int encoderGoal = EnvironmentVariables.ticksLengthOfSwitch/2;
			if(!AutoState.moving)
			{
				Robot.drive.setUpMove(encoderGoal);
				AutoState.moving = true;
			}
			else if(Robot.drive.executeMove())
			{
				Robot.drive.finishMove();
				AutoState.moving = false;
				autoProgramState++;
			}
			break;
		}
		//the robot will turn 90 degrees right to face the switch
		case 6:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 6: Turn 90 degrees right");
			}
			if(!AutoState.turning){
				Robot.drive.setUpTurn(EnvironmentVariables.ticksTo90);
				AutoState.turning = true;
			}
			else if(Robot.drive.executeTurn()){
				Robot.drive.finishTurn();
				AutoState.turning = false;
				autoProgramState++;
			}
			break;
		}
		//the robot will drop the cube 
		case 7:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 7: Drop the cube");
			}
			//drop cube
			break;
		}
		//program is done
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