package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class SwitchLeftAndPositionMiddle {
	/**
	 * Might change this to just going forward and dropping the cube onto the switch
	 * 		Just need to make sure that the robot is not placed in the path of the cube pile
	 */
	public static void runPeriodic(){ 

		switch(AutoState.state){

		//the robot will release the arms by going forward and then backwards
		case 0:
		{
			if(RobotMap.DEBUG)
			{
				System.out.println("SwitchLeftAndPositionMiddle Case 0: Robot will attempt to release arms");
			}
			AutoState.state = Robot.intake.autoIntake(AutoState.state, RobotMap.inputCubeSpeed, 0.5);
			break;
		}
		//the robot will move halfway to the switch
		case 1:
		{
			if(RobotMap.DEBUG)
			{
				System.out.println("SwitchLeftAndPositionMiddle Case 1: Move halfway to switch");
			}
			int encoderGoal = EnvironmentVariables.ticksToMiddleOfSwitch/2;
			AutoState.state = Robot.drive.autoMove(AutoState.state, encoderGoal);
			break;
		}
		//the robot will turn 90 degrees left
		case 2:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionMiddle Case 2: Turn 90 degrees left");
			}
			
			AutoState.state = Robot.drive.autoTurn(AutoState.state, -90);
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
			AutoState.state = Robot.drive.autoMove(AutoState.state, encoderGoal);
			break; 
		}
		//the robot will turn 90 degrees right
		case 4:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionMiddle Case 4: Turn 90 degrees right");
			}

			AutoState.state = Robot.drive.autoTurn(AutoState.state, 90);

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
			AutoState.state = Robot.drive.autoMove(AutoState.state, encoderGoal);
			break;

		}
		//the robot will drop the cube
		case 6:
		{
			if(RobotMap.DEBUG){	
				System.out.println("SwitchLeftAndPositionMiddle Case 6: Drop the cube onto the switch");
			}

			AutoState.state = Robot.elevator.autoElevate(AutoState.state, 500);
			
			break;
		}
		//the robot will outtake the cube
		case 7:
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionMiddle Case 7: Outtake the cube.");
			}

			AutoState.state = Robot.intake.autoIntake(AutoState.state, RobotMap.outputCubeSpeed, 1);
			break;
		case 8:
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionMiddle Case 8: Program is done");
			}
			break;
		}
	}
}
