package org.usfirst.frc.team2643.robot;

public class CrossAutoLineOnly {
	public static void runPeriodic(){
		if(RobotMap.leftEncoder.get() < EnvironmentVariables.autoLineDistance && RobotMap.rightEncoder.get() < EnvironmentVariables.autoLineDistance){
			RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
		}else{
			RobotMovementMethods.setAll(0);
		}
	}
}
