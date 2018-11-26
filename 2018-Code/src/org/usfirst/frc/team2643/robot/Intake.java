package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
// 
public class Intake {
	// declaring the motors that take the cubes
	private final WPI_TalonSRX leftIntake;
	private final WPI_TalonSRX rightIntake;
	private double speed = 0;
	/**
	 * Constructor for Intake subsystem
	 * @param left the left motor of the Intake
	 * @param right the right motor of the Intake
	 */
	public Intake(WPI_TalonSRX left, WPI_TalonSRX right)
	{
		leftIntake = left;
		rightIntake = right;
		//Limiting current of leftIntake
		limitCurrent(leftIntake);
		//Limiting current of rightIntake
		limitCurrent(rightIntake);
	}
	
	public double getSpeed()
	{
		return speed;
	}
	
	/**
	 * Limits the current of the motor
	 * @param motor the motor that the current is being limited
	 */
	public void limitCurrent(WPI_TalonSRX motor) {
		motor.configContinuousCurrentLimit(38, 0);
		motor.configPeakCurrentLimit(40, 0);
		motor.configPeakCurrentDuration(100, 0);
		motor.enableCurrentLimit(true);
	}
	
	/**
	 * If the the individual buttons on the board are pressed then set speed to
	 * @param board operator board for the robot
	 */
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
	
	/**
	 * Sets the speed of the leftIntake
	 * @param value the double value of the leftIntake
	 */
	public void setSpeedLeft(double value)
	{
		leftIntake.set(-value);
	}
	
	/**
	 * Sets the speed of the rightIntake
	 * @param value the double value of the rightIntake
	 */
	public void setSpeedRight(double value)
	{
		rightIntake.set(value);
	}
	
	/**
	 * Set the speed of the both the motors at the same time
	 * @param speed the speed that you want to set the motors at
	 */
	public void setSpeed(double speed)
	{
		
		this.speed = speed;
		setSpeedLeft(speed);
		setSpeedRight(speed);
	}
	/**
	 * Stops the motors and sets their speed to zero
	 */
	public void stop()
	{
		setSpeed(0);
	}
	
	/**
	 * It controls the intake motors
	 * @param x the x axis of the joystick
	 * @param y the y axis of the joystick
	 */
	public void intake(double x, double y) {
		if(x > 0.05) {
			leftIntake.set(x);
			rightIntake.set(x);
		}
		else if(y > 0.05) {
			leftIntake.set(y);
			rightIntake.set(-y);
		}
		else {
			leftIntake.set(0);
			rightIntake.set(0);
		}
	}
}
