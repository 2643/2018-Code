package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IntakeAngle {
	
	private final WPI_TalonSRX angleMotor;
	
	public IntakeAngle(WPI_TalonSRX a) {
		angleMotor = a;
	}
	
	/**
	 * @param Enter desired speed of motor; -1<x<1
	 */
	public void angleIntake(double speed) {
		angleMotor.set(speed);
	}
}