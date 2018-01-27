package org.usfirst.frc.team2643.robot;

public class CrossAutoLineOnly {
	public static void runPeriodic(){
		if(RobotMap.leftEncoder.get() < RobotMap.autoLineDistance && RobotMap.rightEncoder.get() < RobotMap.autoLineDistance){
			RobotMovementMethods.setAll(RobotMap.autoSpeed);
		}else{
			RobotMovementMethods.setAll(0);
		}
	}
}
