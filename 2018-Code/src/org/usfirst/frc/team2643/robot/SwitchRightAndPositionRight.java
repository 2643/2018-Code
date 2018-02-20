package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class SwitchRightAndPositionRight {
	
	public static void runPeriodic(){
		switch(AutoState.state){
		//the robot will release the arms
		case 0:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 0: Robot will release arms");
			}

			AutoState.state = Robot.intake.autoIntake(AutoState.state, RobotMap.inputCubeSpeed, 0.5);
			break;

		//the robot will move forward until right next to the switch
		case 1:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 1: Move forward until next to the switch");
			}
			int encoderGoal = EnvironmentVariables.ticksToMiddleOfSwitch;
			AutoState.state = Robot.drive.autoMove(AutoState.state, encoderGoal);
			break;
		}
		//the robot will turn ninety degrees left to face the switch
		case 2:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 2: Turn 90 degrees left");
			}

			AutoState.state = Robot.drive.autoTurn(AutoState.state, -90);
			break;
		}
		//the robot will drop the cube on the switch
		case 3: 
			//TODO drop the cube onto the switch
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 3: Drop the cube");
			}

			AutoState.state++;// = Robot.elevator.autoElevate(AutoState.state, 500);
			break;
		 //the robot will outtake the  cube
		case 4:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 4: Outtake the cube");
			}

			AutoState.state = Robot.intake.autoIntake(AutoState.state, RobotMap.outputCubeSpeed, 2);
			break;
		case 5:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 5: Program is done");
			}
			break;
		}
	}
}
