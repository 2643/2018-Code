package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IntakeAngle {
	
	private final WPI_TalonSRX angleMotor;
	/**
	 * Constructor for angle motor
	 * @param a a is the angle motor and it is a WPI talonSRX motor
	 */
	public IntakeAngle(WPI_TalonSRX a) {
		angleMotor = a;
		angleMotor.setNeutralMode(NeutralMode.Brake);
	}
	
	/**
	 *  Sets the speed of the angle motor
	 * @param Enter desired speed of motor; -1<x<1
	 */
	public void angleIntake(double speed) {
		angleMotor.set(speed);
	}
	/**
	 * Tells if the motor goes up or down depending on the what  button you press
	 * @param buttonUp it is a joystick botton that makes the motors go up
	 * @param buttonDownit is also a joystick button that makes the motors go down
	 */
	
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
