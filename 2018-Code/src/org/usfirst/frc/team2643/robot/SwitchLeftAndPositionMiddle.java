package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class SwitchLeftAndPositionMiddle {
	public static int autoProgramState = 0;
	public static Timer outtakeTimer = new Timer();

	/**
	 * Might change this to just going forward and dropping the cube onto the switch
	 * 		Just need to make sure that the robot is not placed in the path of the cube pile
	 */
	public static void runPeriodic(){ 

		switch(autoProgramState){

		//the robot will release the arms by going forward and then backwards
		case 0:
			if(RobotMap.DEBUG)
			{
				System.out.println("SwitchLeftAndPositionMiddle Case 0: Robot will attempt to release arms");
			}

			if(!AutoState.armsReleasing)
			{
				Robot.drive.setUpReleaseArms();
				AutoState.armsReleasing = true;
			}
			else
			{
				if(Robot.drive.executeReleaseArms())
				{
					Robot.drive.finishReleaseArms();
					autoProgramState++;
					AutoState.armsReleasing = false;

				}
			}
			break;

		//the robot will move halfway to the switch
		case 1:
		{
			if(RobotMap.DEBUG)
			{
				System.out.println("SwitchLeftAndPositionMiddle Case 1: Move halfway to switch");
			}

			int encoderGoal = EnvironmentVariables.ticksToBeforeSwitch/2;
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
		//the robot will turn 90 degrees left
		case 2:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionMiddle Case 2: Turn 90 degrees left");
			}

			if(!AutoState.turning)
			{
				Robot.drive.setUpTurn(-EnvironmentVariables.ticksTo90);
				AutoState.turning = true;
			}
			else
			{
				//if it is finished turning
				if(Robot.drive.executeTurn())
				{
					Robot.drive.finishTurn();
					AutoState.turning = false;
					autoProgramState++;
				}
			}
			break;
		}

		//the robot will move until it almost reaches the left end of the switch
		case 3:
		{
			if(RobotMap.DEBUG)
			{
				System.out.println("SwitchLeftAndPositionMiddle Case 3: Move forward until in line with the left end of the switch");
			}

			int encoderGoal = (EnvironmentVariables.ticksWidthOfSwitch)/2;
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
				System.out.println("SwitchLeftAndPositionMiddle Case 4: Turn 90 degrees right");
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
		//Robot will move forward until it reaches the switch
		case 5:
		{
			if(RobotMap.DEBUG)
			{
				System.out.println("SwitchLeftAndPositionMiddle Case 5: Move forward until next to the switch");
			}	

			int encoderGoal = EnvironmentVariables.ticksToBeforeSwitch/2;
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
		//the robot will drop the cube
		case 6:
		{
			if(RobotMap.DEBUG){	
				System.out.println("SwitchLeftAndPositionMiddle Case 6: Drop the cube onto the switch");
			}
			if(!AutoState.elevating)
			{
				Robot.elevator.setUpElevate(500);
				AutoState.elevating = true;
			}	
			else
			{ 
				if(Robot.elevator.executeElevate())
				{
					Robot.elevator.finishElevate();
					AutoState.elevating = false;
					autoProgramState++;
				}
			}
			break;
		}
		//the robot will outtake the cube
		case 7:
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionMiddle Case 7: Outtake the cube.");
			}
			if(outtakeTimer.get() < 3){
				Intake.intake(-0.5, -0.5);
			}else{
				outtakeTimer.stop();
				autoProgramState = 8;
			}
			break;
		case 8:
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionMiddle Case 8: Program is done");
			}
			break;
		}
	}
}
