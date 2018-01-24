package org.usfirst.frc.team2643.robot;

public class CrossAutoLineOnly {
	public static void runPeriodic(){
		if(Robot.leftEncoder.get() < RobotMap.autoLineDistance && Robot.rightEncoder.get() < RobotMap.autoLineDistance){
			Robot.setAll(RobotMap.autoSpeed);
		}else{
			Robot.setAll(0);
		}
	}
}
