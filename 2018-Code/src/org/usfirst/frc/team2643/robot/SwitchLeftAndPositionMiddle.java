package org.usfirst.frc.team2643.robot;

public class SwitchLeftAndPositionMiddle 
{
	public static int autoProgramState = 0;

	/**
	 * This is the auto routine that runs when our robot is on the left and our switch is on the left. 
	 * Case 1: the robot will move forward until right next to the switch
	 * Case 2: the robot will rotate ninety degrees right to face the switch
	 * Case 3: the robot will drop the cube on the switch
	 */
	public static void runPeriodic(){ 

		switch(autoProgramState){
		case 0:
		{
			if(RobotMap.DEBUG)
			{
				System.out.println("Zeroth Case for SwitchLeftPositionMiddle");
				System.out.println("Robot will attempt to release arms");
			}

			if(!AutoState.armsReleasing)
			{
				RobotMovementMethods.setUpReleaseArms();
				AutoState.armsReleasing = true;
			}
			else
			{
				boolean isFinished = RobotMovementMethods.executeTurn();
				if(isFinished)
				{
					RobotMovementMethods.finishReleaseArms();
					autoProgramState = 1;
					AutoState.armsReleasing = false;
				}
			}
			break;
		}
		case 1:
		{
			if(RobotMap.DEBUG)
			{
				System.out.println("First Case for SwitchLeftAndPositionLeft");
				System.out.println("Robot will move forward until halfway at switch");
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
		case 2:
		{
			if(RobotMap.DEBUG){
				System.out.println("Second Case for SwitchLeftAndPositionLeft");
				System.out.println("Robot will turn ninety degrees left to go sideways");
			}
			
			if(!AutoState.turning)
			{
				RobotMovementMethods.setUpTurn(-EnvironmentVariables.ticksTo90);
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
		}
		case 3:
		{
			if(RobotMap.DEBUG)
		
			{
				System.out.println("First Case for SwitchLeftAndPositionLeft");
				System.out.println("Robot will move forward until the edge of the switch");
			}

			int encoderGoal = EnvironmentVariables.ticksWidthOfSwitch;
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
		case 4:
		{
			if(RobotMap.DEBUG){
		
				System.out.println("Fourth case for SwitchLeftAndPositionLeft");
				System.out.println("Robot will turn ninety degrees right to face forward");
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
				System.out.println("First Case for SwitchLeftAndPositionLeft");
				System.out.println("Robot will move forward until at the center of the switch");
			}

			int encoderGoal = EnvironmentVariables.ticksToSwitch/2;
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
		}
	}
}
