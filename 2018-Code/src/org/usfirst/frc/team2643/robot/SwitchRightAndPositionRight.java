package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class SwitchRightAndPositionRight {
	
	public static int autoProgramState = 0;

	public static void runPeriodic(){
		switch(autoProgramState){
		//the robot will release the arms
		case 0:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 0: Robot will release arms");
			}

			autoProgramState = Robot.intake.autoIntake(autoProgramState, RobotMap.inputCubeSpeed, 0.5);
			break;

		//the robot will move forward until right next to the switch
		case 1:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 1: Move forward until next to the switch");
			}
			int encoderGoal = EnvironmentVariables.ticksToMiddleOfSwitch;
			autoProgramState = Robot.drive.autoMove(autoProgramState, encoderGoal);
			break;
		}
		//the robot will turn ninety degrees left to face the switch
		case 2:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 2: Turn 90 degrees left");
			}

			autoProgramState = Robot.drive.autoTurn(autoProgramState, -90);
			break;
		}
		//the robot will drop the cube on the switch
		case 3: 
			//TODO drop the cube onto the switch
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 3: Drop the cube");
			}

			autoProgramState = Robot.elevator.autoElevate(autoProgramState, 500);
			break;
		 //the robot will outtake the  cube
		case 4:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 4: Outtake the cube");
			}

			autoProgramState = Robot.intake.autoIntake(autoProgramState, RobotMap.outputCubeSpeed, 2);
			break;
		case 5:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 5: Program is done");
			}
			break;
		}
	}
}
