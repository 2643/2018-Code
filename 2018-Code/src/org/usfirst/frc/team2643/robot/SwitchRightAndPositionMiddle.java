package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class SwitchRightAndPositionMiddle {

	public static int autoProgramState = 0;
	public static Timer outtakeTimer = new Timer();

	public static void runPeriodic(){
		switch(autoProgramState){

		//the robot will release the arms
		case 0:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionMiddle Case 0: Release the arms");
			}

			if(!AutoState.armsReleasing)
			{
				Robot.drive.setUpReleaseArms();
				AutoState.armsReleasing = true;
			}
			else if(Robot.drive.executeReleaseArms())
			{
				Robot.drive.finishReleaseArms();
				AutoState.armsReleasing = false;
				autoProgramState++;

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
			if(outtakeTimer.get() < 3){
				Intake.intake(-0.5, -0.5);
			}else{
				outtakeTimer.stop();
				autoProgramState++;
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
