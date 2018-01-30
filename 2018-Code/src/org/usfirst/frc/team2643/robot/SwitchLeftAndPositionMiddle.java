package org.usfirst.frc.team2643.robot;

public class SwitchLeftAndPositionMiddle 
{
	public static int autoProgramState = 0;

	/**
	 * Might change this to just going forward and dropping the cube onto the switch
	 * 		Just need to make sure that the robot is not placed in the path of the cube pile
	 */
	public static void runPeriodic(){ 

		switch(autoProgramState){
			case 0:
			
				if(RobotMap.DEBUG)
				{
					System.out.println("SwitchLeftAndPositionMiddle Case 0: Robot will attempt to release arms");
				}
			

				//if the arms are not releasing, set up to release the arms
				if(!AutoState.armsReleasing)
				{
					RobotMovementMethods.setUpReleaseArms();
					AutoState.armsReleasing = true;
				}
				//otherwise, if the robot is releasing the arms, continue releasing the arms by turning
				else
				{ 
					boolean isFinished = RobotMovementMethods.executeTurn();
					if(isFinished)
					{
						/*
						 * If the robot has finished turning to release the arms,
						 * 	move to the next state and make armsReleasing because
						 * 	the arms are no longer releasing
						 */
						RobotMovementMethods.finishReleaseArms();
						autoProgramState = 1;
						AutoState.armsReleasing = false;
					}
				}
				break;
		
			//the robot will move halfway to the switch
			case 1:
			{
				if(RobotMap.DEBUG)
				{
					System.out.println("SwitchLeftAndPositionMiddle Case 1: Robot moving halfway to switch");
				}
			
				int encoderGoal = EnvironmentVariables.ticksToSwitch/2;
				if(RobotMap.leftEncoder.get() < encoderGoal
						&& RobotMap.rightEncoder.get() < encoderGoal)
				{
					RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
				}
				else
				{
					autoProgramState = 2;
				}
				break;
			}
			
			//the robot will turn 90 degrees left
			case 2:
			{
				if(RobotMap.DEBUG){
					System.out.println("Second Case for SwitchLeftAndPositionLeft");
					System.out.println("Robot will turn ninety degrees left to go sideways");
				}
			
				//if the robot isn't turning, set up to turn, make turning true because 
				//the robot is going to start turning
				if(!AutoState.turning)
				{
					RobotMovementMethods.setUpTurn(-EnvironmentVariables.ticksTo90);
					AutoState.turning = true;
				}
				else
				{
					//if the robot has finished turning....
					boolean isFinished = RobotMovementMethods.executeTurn();
					if(isFinished)
					{
						//set up to finish the run,
						//make turning false, because the robot is turning, 
						//and move the next case
						RobotMovementMethods.finishTurn();
						autoProgramState = 3;
						AutoState.turning = false;
					}
				}
				break;
			}
			
			//the robot will move until it almost reaches the left end of the switch
			case 3:
			{
				if(RobotMap.DEBUG)
				{
					System.out.println("SwitchLeftAndPositionMiddle Case 3: Robot moving until halfway to switch");
				}

				//the robot will move at cruising speed until it reaches the left end of the switch
				int encoderGoal = (EnvironmentVariables.ticksToSwitch)/2;
				if(RobotMap.leftEncoder.get() < encoderGoal 
						&& RobotMap.rightEncoder.get() < encoderGoal)
				{
					RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
				}
				//then move to the next case
				else
				{
					autoProgramState = 2;
				}
				break;
			}
			
			//the robot will turn 90 degrees right
			case 4:
			{
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionMiddle Case 4: Robot will turn 90 degrees right");
				}

				//if the robot has not started turning, set up for the turn 
				//and make turning true because the robot is going to start turning
				if(!AutoState.turning)
				{
					RobotMovementMethods.setUpTurn(EnvironmentVariables.ticksTo90);
					AutoState.turning = true;
				}
				else
				{
					//if the robot is finished turning,
					//    finish the turn,
					//    make turning false because the robot is no longer turning
					//    move to the next case
					boolean isFinished = RobotMovementMethods.executeTurn();
					if(isFinished)
					{
						RobotMovementMethods.finishTurn();
						autoProgramState = 5;
						AutoState.turning = false;
					}
				}
				break;
			}	
			case 5:
			{
				if(RobotMap.DEBUG)
				{
					System.out.println("SwitchLeftAndPositionMiddle Case 5: The robot will move until in reaches the left end of the switch");
				}
				
				//the robot will move until it almost reaches the left end of the switch
				int encoderGoal = EnvironmentVariables.ticksLengthOfSwitch/2;
				if(RobotMap.leftEncoder.get() < encoderGoal
					&& RobotMap.rightEncoder.get() < encoderGoal)
				{	
					RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
				}
				else
				{
					autoProgramState = 6;
				}
				break;
			}
			
			//the robot will turn 90 degrees right to face the switch
			case 6:
			{
				if(RobotMap.DEBUG){	
					System.out.println("SwitchLeftAndPositionMiddle Case 6: robot will turn right to face the switch");
				}
				if(!AutoState.turning)
				{
					RobotMovementMethods.setUpTurn(EnvironmentVariables.ticksTo90);
					AutoState.turning = true;
				}
				else
				{
					boolean isFinished = RobotMovementMethods.executeTurn();
					if(isFinished)
					{
						RobotMovementMethods.finishTurn();
						autoProgramState = 7;
						AutoState.turning = false;
					}
				}
				break;
			}
			
			case 7:
				break;
		}
	}
}
