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
					if(RobotMovementMethods.executeReleaseArms())
					{
						RobotMovementMethods.finishReleaseArms();
						AutoState.armsReleasing = false;
						autoProgramState++;
					}
				}
				break;

			//the robot will move forward until right next to the switch
			case 1:
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 1: Robot go forward until next to the switch");
				}

				
				int encoderGoal = EnvironmentVariables.ticksToMiddleOfSwitch;
				if(!AutoState.moving)
				{
					RobotMovementMethods.setUpMove(encoderGoal);
					AutoState.moving = true;
				}
				else if(RobotMovementMethods.executeMove())
				{
					RobotMovementMethods.finishMove();
					AutoState.moving = false;
					autoProgramState++;
				}
				break;

			//the robot will turn ninety degrees right to face the switch
			case 2:
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 2: Turn right to face the switch");
				}

				
				if(!AutoState.turning)
				{
					RobotMovementMethods.setUpTurn(EnvironmentVariables.ticksTo90);
					AutoState.turning = true;
				}	
				else
				{ 
					if(RobotMovementMethods.executeTurn())
					{
						RobotMovementMethods.finishTurn();
						AutoState.turning = false;
						autoProgramState++;
					}
				}
				break;

			//the robot will drop the cube on the switch
			case 3: 
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 3: Robot will drop the cube onto the switch.");
				}
				//TODO drop the cube onto the switch
				autoProgramState++;
				break;
			
			case 4:
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 4: Program is done");
				}
				break;
		}
	}
}
