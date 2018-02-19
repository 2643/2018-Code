package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake {
	private WPI_TalonSRX leftIntake;
	private WPI_TalonSRX rightIntake;
	
	public Intake(WPI_TalonSRX left, WPI_TalonSRX right)
	{
		leftIntake = left;
		rightIntake = right;
	}
	
	public void setUpIntake()
	{
		AutoState.robotState = AutoState.INTAKING;
	}
	
	public static void intake(double x, double y) {
		if(x>0.05) {
			RobotMap.leftIntake.set(-x);
			RobotMap.rightIntake.set(x);
		}
		else if(y>0.05) {
			RobotMap.leftIntake.set(y);
			RobotMap.rightIntake.set(-y);
		}
		else {
			RobotMap.leftIntake.set(0);
			RobotMap.rightIntake.set(0);
		}
	}
}
