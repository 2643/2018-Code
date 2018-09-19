package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Drive {
	private WPI_TalonSRX
		frontLeft,
		frontRight,
		backLeft,
		backRight;
	
	public Drive(WPI_TalonSRX left1,WPI_TalonSRX left2,WPI_TalonSRX right1,WPI_TalonSRX right2) {
		frontLeft = left1;
		backLeft = left2;
		frontRight = right1;
		backRight = right2;
		
		currentLimit(frontLeft);
		currentLimit(frontRight);
		
		backLeft.follow(frontLeft);
		backRight.follow(frontRight);
	}
	
	public void currentLimit(WPI_TalonSRX motor) {
		motor.configPeakCurrentDuration(150, 0);
		motor.configPeakCurrentLimit(40, 0);
		motor.configContinuousCurrentLimit(35, 0);
		motor.enableCurrentLimit(true);
	}
	
	public void SRXtankDrive(double x, double y)
	{	
		if(RobotMap.driveStick.getRawAxis(1)>0.03 || RobotMap.driveStick.getRawAxis(1)<-0.03) {
			frontLeft.set(x);
		}
		else {
			frontLeft.set(0);
			}
		if(RobotMap.driveStick.getRawAxis(5)>0.03 || RobotMap.driveStick.getRawAxis(5)<-0.03) {
			frontRight.set(-y);
		}
		else {
			frontRight.set(0);
		}
	}
}
