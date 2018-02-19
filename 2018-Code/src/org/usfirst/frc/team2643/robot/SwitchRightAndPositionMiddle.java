package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class SwitchRightAndPositionMiddle {

	public static int autoProgramState = 0;

	public static void runPeriodic(){
		switch(autoProgramState){

		//the robot will release the arms
		case 0:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionMiddle Case 0: Release the arms");
			}

			autoProgramState = Robot.intake.autoIntake(autoProgramState, RobotMap.inputCubeSpeed, 0.5);
			break;
		}
		//the robot will move forward until it hits the switch
		case 1:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionMiddle Case 1: Move forward until you hit the switch");
			}
			int encoderGoal = EnvironmentVariables.ticksToBeforeSwitch;
			autoProgramState = Robot.drive.autoMove(autoProgramState, encoderGoal);
			break;
		}
		//the robot will drop the cube onto the switch
		case 2: 
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionMiddle Case 2: Drop the cube onto the switch");
			}

			autoProgramState = Robot.elevator.autoElevate(autoProgramState, 500);
			break;
		//the robot will outtake the cube
		case 3:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionMiddle Case 3: Outtake the cube");
			}

			autoProgramState = Robot.intake.autoIntake(autoProgramState, RobotMap.outputCubeSpeed, 2);
			break;
		case 4:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionMiddle Case 4: Program is done");
			}
			break;
		}
	}
}
