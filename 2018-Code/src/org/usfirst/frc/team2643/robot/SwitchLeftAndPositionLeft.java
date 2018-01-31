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
					System.out.println("SwitchLeftAndPositionMiddle Case 0: Robot will release arms");
				}

				if(!AutoState.armsReleasing)
				{
					RobotMovementMethods.setUpReleaseArms();
					AutoState.armsReleasing = true;
				}
				else
				{
					boolean isFinished = RobotMovementMethods.executeReleaseArms();
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
					System.out.println("SwitchLeftAndPositionLeft Case 1: The robot will move forward until it is right next to the switch");
				}

				//The robot will go forward until one of the encoders reaches the switch
				if(RobotMap.leftEncoder.get() < EnvironmentVariables.ticksToMiddleOfSwitch 
						&& RobotMap.rightEncoder.get() < EnvironmentVariables.ticksToMiddleOfSwitch)
				{
					RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
					
				}
				else
				{
					autoProgramState = 2;
				}
				break;

			//the robot will turn ninety degrees right to face the switch
			case 2:
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 2: robot will turn 90 degrees right to face the switch.");
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
						autoProgramState = 3;
						AutoState.turning = false;
					}
				}
				break;

			//the robot will drop the cube on the switch
			case 3: 
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 3: Robot will drop the cube onto the switch.");
				}
				//TODO drop the cube onto the switch
				autoProgramState = 4;
				break;
			
			case 4:
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositoinLeft Case 4: Program is done");
				}
				break;
		}
	}
}
