package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class SwitchRightAndPositionRight {
	
	public static int autoProgramState = 0;
	public static Timer outtakeTimer = new Timer();
	public static boolean resetTimer = false;
	
	public static void runPeriodic(){
		switch(autoProgramState){
		//the robot will release the arms
		case 0:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 0: Robot will release arms");
			}

			if(!AutoState.inttaking)
			{
				Robot.intake.setUpIntake(0.5, RobotMap.outputCubeSpeed);
				AutoState.inttaking = true;
			}
			else
			{
				if(Robot.intake.executeIntake()) 
				{
					Robot.intake.finishIntake();
					AutoState.inttaking = false; 
					autoProgramState++; 
				}
			}
			break;

		//the robot will move forward until right next to the switch
		case 1:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 1: Move forward until next to the switch");
			}
			int encoderGoal = EnvironmentVariables.ticksToMiddleOfSwitch;
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
		//the robot will turn ninety degrees left to face the switch
		case 2:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 2: Turn 90 degrees left");
			}

			if(!AutoState.turning)
			{
				Robot.drive.setUpTurn(-EnvironmentVariables.ticksTo90);
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
		//the robot will drop the cube on the switch
		case 3: 
			//TODO drop the cube onto the switch
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 3: Drop the cube");
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
		 //the robot will outtake the cube
		case 4:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 4: Outtake the cube");
			}
			if(!AutoState.inttaking)
			{
				Robot.intake.setUpIntake(1, RobotMap.outputCubeSpeed);
				AutoState.inttaking = true;
			}
			else
			{
				if(Robot.intake.executeIntake()) 
				{
					Robot.intake.finishIntake();
					AutoState.inttaking = false; 
					autoProgramState++; 
				}
			}
			break;
		case 5:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 5: Program is done");
			}
			break;
		}
	}
}
