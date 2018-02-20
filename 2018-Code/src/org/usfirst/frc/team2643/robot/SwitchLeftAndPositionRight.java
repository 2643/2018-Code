package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class SwitchLeftAndPositionRight {

	public static void runPeriodic(){
		switch(AutoState.state){
		
		//this will release the arms
		case 0:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 0: Robot will release arms");
			}
			AutoState.state = Robot.intake.autoIntake(
					AutoState.state, RobotMap.inputCubeSpeed, 0.5);
			break;
		}
		//the robot will go forward until the it goes a little bit past the switch
		case 1:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 1: Move forward until past the switch");
			}
			int encoderGoal = EnvironmentVariables.ticksToPassSwitch;
			AutoState.state = Robot.drive.autoMove(AutoState.state, encoderGoal);
			break;
		}
		//the robot will turn left 90 degrees
		case 2:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 2: Turn left 90 degrees");
			}
			double turnGoal = -90;
			AutoState.state = Robot.drive.autoTurn(AutoState.state, turnGoal);
			break;
		}

		//the robot will move along the back of the wall until it passes the left end of the switch
		case 3:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 3: Move along back wall and pass the left end of the switch");
			}
			
			int encoderGoal = EnvironmentVariables.ticksWidthOfSwitch;
			AutoState.state = Robot.drive.autoMove(AutoState.state, encoderGoal);
			break;
		}
		//the robot will turn 90 degrees right
		case 4:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 4: Turn 90 degrees right");
			}

			AutoState.state = Robot.drive.autoTurn(AutoState.state, -90);
			break;
		}
		//the robot will move forward so that it is next to the switch
		case 5:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 5: Move forward until in the middle of the length of the switch");
			}
			int moveGoal = EnvironmentVariables.ticksLengthOfSwitch/2;
			AutoState.state = Robot.drive.autoMove(AutoState.state, moveGoal);
			break;
		}
		//the robot will turn 90 degrees left to face the switch plate
		case 6:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 6: Turn 90 degrees left to face the switch plate");
			}
			AutoState.state = Robot.drive.autoTurn(AutoState.state, -90);
			break;
		}
		//the robot will drop the cube
		case 7:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 7: Drop the cube onto the switch");
			}
			int elevateGoal = 500;
			AutoState.state++;// = Robot.elevator.autoElevate(AutoState.state, elevateGoal);
			break;
		}
		//the robot will outtake the cube
		case 8:
		{	
			if(RobotMap.DEBUG){
				System.out.println("SwitchLeftAndPositionRight Case 8: Outtake the cube");
			}
			AutoState.state = Robot.intake.autoIntake(AutoState.state, RobotMap.outputCubeSpeed, 1);
			break;
		}
		//program is done
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
