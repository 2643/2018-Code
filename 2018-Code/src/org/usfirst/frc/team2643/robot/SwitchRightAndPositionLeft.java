package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

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

			autoProgramState = Robot.intake.autoIntake(autoProgramState, RobotMap.inputCubeSpeed, 0.5);
			break;
		}
		//the robot will go past the switch
		case 1: 
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 1: Move forward until past the switch");
			}

			int encoderGoal = EnvironmentVariables.ticksToMiddleOfSwitch;
			autoProgramState = Robot.drive.autoMove(autoProgramState, encoderGoal);
			break;
		}
		//the robot will turn right 90 degrees
		case 2:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 2: Turn 90 degrees right");
			}

			autoProgramState = Robot.drive.autoTurn(autoProgramState, 90);
			
			break;
		}
		//the robot will move along the back wall and stop after it passes the left end of the switch
		case 3:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 3: Move along back wall and stop after it passes the left end of the switch");
			}
			int encoderGoal = EnvironmentVariables.ticksWidthOfSwitch;
			autoProgramState = Robot.drive.autoMove(autoProgramState, encoderGoal);
			break;
		}
		//the robot will turn 90 degrees right 
		case 4:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionLeft Case 4: Turn 90 degrees right");
			}

			autoProgramState = Robot.drive.autoTurn(autoProgramState, 90);
			break;
		}
		//the robot will move forward until in the middle of the length of the switch
		case 5:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 5: Move forward until in the middle of the length of the switch");
			}
			int encoderGoal = EnvironmentVariables.ticksLengthOfSwitch/2;
			autoProgramState = Robot.drive.autoMove(autoProgramState, encoderGoal);
			break;
		}
		//the robot will turn 90 degrees right to face the switch
		case 6:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 6: Turn 90 degrees right");
			}

			autoProgramState = Robot.drive.autoTurn(autoProgramState, 90);
			break;
		}
		//the robot will drop the cube 
		case 7:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 7: Drop the cube");
			}

			autoProgramState = Robot.elevator.autoElevate(autoProgramState, 500);
			break;
		}
		//program is done
		case 8:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 8: Outtake the cube");
			}

			autoProgramState = Robot.intake.autoIntake(autoProgramState, RobotMap.outputCubeSpeed, 2);
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