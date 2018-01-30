package org.usfirst.frc.team2643.robot;

public class SwitchLeftAndPositionLeft {


	//state variable for switch statement 
	public static int autoProgramState = 0;

	/**
	 * This is the auto routine that runs when our robot is on the left and our switch is on the left. 
	 * Case 1: the robot will move forward until right next to the switch
	 * Case 2: the robot will rotate ninety degrees right to face the switch
	 * Case 3: the robot will drop the cube on the switch
	 */
	public static void runPeriodic(){

		switch(autoProgramState){
		//the robot will attempt to release arms
		case 0:
			if(RobotMap.DEBUG){
				System.out.println("GO GO POWER RANGERS!!!!!!");
			}

			//if arms are not releasing, then set up to release the arms
			if(!AutoState.armsReleasing)
			{
				RobotMovementMethods.setUpReleaseArms();
				AutoState.armsReleasing = true;
			}
			//otherwise if the arms are releasing, then continue releasing arms
			else
			{
				/*
				 * If the robot has finished turning in order to release the arms, 
				 * do set up to finish releasing the arms, and make armsReleasing false because 
				 * the robot is finished, and move to the next state
				 */
				boolean isFinished = RobotMovementMethods.executeTurn();
				if(isFinished)
				{
					RobotMovementMethods.finishReleaseArms();
					autoProgramState = 1;
					AutoState.armsReleasing = false;
				}
			}
			break;

			//the robot will move forward until right next to the switch
		case 1:
			if(RobotMap.DEBUG){
				System.out.println("First Case for SwitchLeftAndPositionLeft");
				System.out.println("Robot will move forward until at the switch");
			}

			//The robot will go forward until one of the encoders reaches the switch
			if(RobotMap.leftEncoder.get() < EnvironmentVariables.ticksToSwitch 
					&& RobotMap.rightEncoder.get() < EnvironmentVariables.ticksToSwitch)
			{
				RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
				
				if(RobotMap.DEBUG){
					System.out.println("Moving to the switch");
				}
			}
			else
			{
				autoProgramState = 2;
			}
			break;

		//the robot will turn ninety degrees right to face the switch
		case 2:
			if(RobotMap.DEBUG){
				System.out.println("Second Case for SwitchLeftAndPositionLeft");
				System.out.println("Robot will turn ninety degrees right to face the switch");
			}

			//the robot will turn ninety degrees right to face the switch
			if(!AutoState.turning)
			{
				//set up to turning and set turning to true because it will start to turn soon
				RobotMovementMethods.setUpTurn(EnvironmentVariables.ticksTo90);
				AutoState.turning = true;
			}
			else
			{
				//if the robot has finished turning...
				boolean isFinished = RobotMovementMethods.executeTurn();
				if(isFinished)
				{
					/* Finish the turn, 
					 * set turning to false because the robot is no longer turning,
					 * and move to the next state
					 */
					RobotMovementMethods.finishTurn();
					autoProgramState = 3;
					AutoState.turning = false;
				}
			}
			break;

			//the robot will drop the cube on the switch
		case 3: 
			//TODO drop the cube onto the switch
			break;
		}
	}
}
