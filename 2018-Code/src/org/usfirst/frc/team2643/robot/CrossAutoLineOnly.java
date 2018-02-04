package org.usfirst.frc.team2643.robot;

public class CrossAutoLineOnly {
	
	public static int autoProgramState = 0;
	
	public static void runPeriodic(){
		
		switch(autoProgramState)
		{
		case 0:
		{
			if(!AutoState.moving)
			{
				RobotMovementMethods.setUpMove(EnvironmentVariables.autoLineDistance);
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
		case 1:
		{
			break;
		}
		}
	}
}
