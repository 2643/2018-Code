package org.usfirst.frc.team2643.robot;

public class SwitchRightAndPositionRight {
	
	public static int autoProgramState = 0;
	
	public static void runPeriodic(){
		switch(autoProgramState){
		case 0:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 0: Robot will release arms");
			}

			if(!AutoState.armsReleasing)
			{
				RobotMovementMethods.setUpReleaseArms();
				AutoState.armsReleasing = true;
			}
			else if(RobotMovementMethods.executeTurn())
			{
					RobotMovementMethods.finishReleaseArms();
					AutoState.armsReleasing = false;
					autoProgramState++;
			}
			break;

		//the robot will move forward until right next to the switch
		case 1:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight case 1: the robot will move forward until it is right next to the switch");
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
		}
		//the robot will turn ninety degrees left to face the switch
		case 2:
		{
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 2: the robot will turn 90 degrees left to face the switch");
			}

			if(!AutoState.turning)
			{
				RobotMovementMethods.setUpTurn(-EnvironmentVariables.ticksTo90);
				AutoState.turning = true;
			}
			else if(RobotMovementMethods.executeTurn())
			{
					RobotMovementMethods.finishTurn();
					AutoState.turning = false;
					autoProgramState++;
			}
			break;
		}
		//the robot will drop the cube on the switch
		case 3: 
			//TODO drop the cube onto the switch
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 3: the robot will drop the cube onto the switch");
			}
			autoProgramState = 4;
			break;
		case 4:
			if(RobotMap.DEBUG){
				System.out.println("SwitchRightAndPositionRight Case 4: program is done");
			}
			break;
		}
	}
}
