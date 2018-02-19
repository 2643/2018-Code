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
		}
		//the robot will move forward until it hits the switch
		case 1:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionMiddle Case 1: Move forward until you hit the switch");
			}
			int encoderGoal = EnvironmentVariables.ticksToBeforeSwitch;
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
		//the robot will drop the cube onto the switch
		case 2: 
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionMiddle Case 2: Drop the cube onto the switch");
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
		case 3:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionMiddle Case 3: Outtake the cube");
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
		case 4:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionMiddle Case 4: Program is done");
			}
			break;
		}
	}
}
