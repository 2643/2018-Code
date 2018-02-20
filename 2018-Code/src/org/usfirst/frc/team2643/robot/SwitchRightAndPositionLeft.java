package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class SwitchRightAndPositionLeft {

	public static void runPeriodic(){
		switch(AutoState.state){

		//the robot will release the arms
		case 0:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 0: Robot will release arms");
			}

			AutoState.state = Robot.intake.autoIntake(AutoState.state, RobotMap.inputCubeSpeed, 0.5);
			break;
		}
		//the robot will go past the switch
		case 1: 
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 1: Move forward until past the switch");
			}

			int encoderGoal = EnvironmentVariables.ticksToMiddleOfSwitch;
			AutoState.state = Robot.drive.autoMove(AutoState.state, encoderGoal);
			break;
		}
		//the robot will turn right 90 degrees
		case 2:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 2: Turn 90 degrees right");
			}

			AutoState.state = Robot.drive.autoTurn(AutoState.state, 90);
			
			break;
		}
		//the robot will move along the back wall and stop after it passes the left end of the switch
		case 3:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 3: Move along back wall and stop after it passes the left end of the switch");
			}
			int encoderGoal = EnvironmentVariables.ticksWidthOfSwitch;
			AutoState.state = Robot.drive.autoMove(AutoState.state, encoderGoal);
			break;
		}
		//the robot will turn 90 degrees right 
		case 4:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 4: Turn 90 degrees right");
			}

			AutoState.state = Robot.drive.autoTurn(AutoState.state, 90);
			break;
		}
		//the robot will move forward until in the middle of the length of the switch
		case 5:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 5: Move forward until in the middle of the length of the switch");
			}
			int encoderGoal = EnvironmentVariables.ticksLengthOfSwitch/2;
			AutoState.state = Robot.drive.autoMove(AutoState.state, encoderGoal);
			break;
		}
		//the robot will turn 90 degrees right to face the switch
		case 6:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 6: Turn 90 degrees right");
			}

			AutoState.state = Robot.drive.autoTurn(AutoState.state, 90);
			break;
		}
		//the robot will drop the cube 
		case 7:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 7: Drop the cube");
			}

			AutoState.state++;// = Robot.elevator.autoElevate(AutoState.state, 500);
			break;
		}
		//program is done
		case 8:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 8: Outtake the cube");
			}

			AutoState.state = Robot.intake.autoIntake(AutoState.state, RobotMap.outputCubeSpeed, 2);
			break;
		}
		case 9:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 9: Program is done");
			}
			break;
		}
		}
	}
}