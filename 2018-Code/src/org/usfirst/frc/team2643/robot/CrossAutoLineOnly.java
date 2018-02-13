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
				Robot.drive.setUpMove(EnvironmentVariables.autoLineDistance);
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
		case 1:
		{
			break;
		}
		}
	}
}
