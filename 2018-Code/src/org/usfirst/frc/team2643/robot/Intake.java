package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class Intake {
	private final WPI_TalonSRX leftIntake;
	private final WPI_TalonSRX rightIntake;
	private double speed;
	
	public Intake(WPI_TalonSRX left, WPI_TalonSRX right)
	{
		leftIntake = left;
		rightIntake = right;
		limitCurrent(leftIntake);
		limitCurrent(rightIntake);
	}
	
	public void limitCurrent(WPI_TalonSRX motor) {
		motor.configContinuousCurrentLimit(38, 0);
		motor.configPeakCurrentLimit(40, 0);
		motor.configPeakCurrentDuration(100, 0);
		motor.enableCurrentLimit(true);
	}
	
	public void intake(Joystick board)
	{
		if(board.getRawButton(RobotMap.fastIntakeButton))
		{
			setSpeed(1);
		}
		else if(board.getRawButton(RobotMap.fastOuttakeButton))
		{
			setSpeed(-1);
		}
		else if(board.getRawButton(RobotMap.slowIntakeButton)) {
			setSpeed(0.5);
		}
		else if(board.getRawButton(RobotMap.slowOuttakeButton)) {
			setSpeed(-0.5);
		}
		else
		{
			setSpeed(0);
		}
	}
	
	public void setSpeedLeft(double value)
	{
		leftIntake.set(-value);
	}
	
	public void setSpeedRight(double value)
	{
		rightIntake.set(value);
	}
	
	public void setSpeed(double speed)
	{
		this.speed = speed;
		setSpeedLeft(speed);
		setSpeedRight(speed);
	}
}
