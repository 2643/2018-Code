package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;

public class RobotMap {
	static Joystick driveStick = new Joystick(0);
	static Joystick opBoard = new Joystick(1);
	
	static WPI_TalonSRX
		leftFront = new WPI_TalonSRX(1),
		leftBack = new WPI_TalonSRX(2),
		rightFront = new WPI_TalonSRX(3),
		rightBack = new WPI_TalonSRX(4),
		elevator1 = new WPI_TalonSRX(5),
		elevator2 = new WPI_TalonSRX(6),
		leftIntake = new WPI_TalonSRX(7),
		rightIntake = new WPI_TalonSRX(8);
	
	static DigitalInput elevatorLimitSwitch = new DigitalInput(9);
	
	public static final int fastIntakeButton = 8,
			fastOuttakeButton = 7,
			safetyButton = 1,
			slowIntakeButton = 3,
			slowOuttakeButton = 2,
			elevatorUp = 5,
			elevatorDown = 4,
			switchStates = 9,
			winchUp = 12,
			winchDown = 11;
	
	static final int defaultPID = 0;
	static final double PIDF = 0.5;
	static final double PIDP = 0.5;
	static final double PIDI = 0;
	static final double PIDD = 0;
	
	static double encoderTick = 0;
	
	static final int maxEncoderValue = 9200; //TODO //From 12750
}
