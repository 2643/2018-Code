package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class SwitchRightAndPositionRight {
	
	public static int autoProgramState = 0;
	public static Timer outtakeTimer = new Timer();
	
	public static void runPeriodic(){
		switch(autoProgramState){
		//the robot will release the arms
		case 0:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 0: Robot will release arms");
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
			autoProgramState = 4;
			break;
		//the robot will outtake the cube
		case 4:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 4: Outtake the cube");
			}
			if(outtakeTimer.get() < 3){
				Intake.intake(-0.5, -0.5);
			}else{
				outtakeTimer.stop();
				autoProgramState = 5;
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
