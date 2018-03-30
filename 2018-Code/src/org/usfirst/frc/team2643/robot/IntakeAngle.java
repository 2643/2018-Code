package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IntakeAngle {
	
	private final WPI_TalonSRX angleMotor;
	
	public IntakeAngle(WPI_TalonSRX a) {
		angleMotor = a;
		angleMotor.setNeutralMode(NeutralMode.Brake);
	}
	
	/**
	 * @param Enter desired speed of motor; -1<x<1
	 */
	public void angleIntake(double speed) {
		angleMotor.set(speed);
	}
	
	public void angleUsingButtons(int buttonUp, int buttonDown) {
		if(RobotMap.opBoard.getRawButton(buttonUp)) {
			RobotMap.inclineMotor.set(0.3);
		}
		else if(RobotMap.opBoard.getRawButton(buttonDown)) {
			RobotMap.inclineMotor.set(-0.3);
		}
		else {
			RobotMap.inclineMotor.set(0);
		}
	}
	
}
