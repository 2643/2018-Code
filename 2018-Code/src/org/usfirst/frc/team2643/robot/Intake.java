package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Joystick;

public class Intake {
	// Sets the intakes and speed
	private final WPI_TalonSRX leftIntake;
	private final WPI_TalonSRX rightIntake;
	private double speed = 0;
	
	/**
	 * Constructor for intake
	 * 
	 * @param left Left Intake
	 * @param right Right Intake
	 */
	public Intake(WPI_TalonSRX left, WPI_TalonSRX right)
	{
		leftIntake = left;
		rightIntake = right;
		limitCurrent(leftIntake);
		limitCurrent(rightIntake);
	}
	/**
	 * Gets speed
	 * @return speed
	 */
	public double getSpeed()
	{
		return speed;
	}
	/**
	 * Limits the current
	 * 
	 * @param motor The motor to limit the current
	 */
	public void limitCurrent(WPI_TalonSRX motor) {
		motor.configContinuousCurrentLimit(38, 0);
		motor.configPeakCurrentLimit(40, 0);
		motor.configPeakCurrentDuration(100, 0);
		motor.enableCurrentLimit(true);
	}
	
	/**
	 * Sets the intake motor according to button input
	 * @param board the operator board with the buttons used
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
	 * Sets the left intake to the negative of value
	 * 
	 * @param value Value of the speed (-1 < x < 1)
	 */
	public void setSpeedLeft(double value)
	{
		leftIntake.set(-value);
	}
	
	/**
	 * Sets the right intake to value
	 * 
	 * @param value Value of the speed (-1 < x < 1)
	 */
	public void setSpeedRight(double value)
	{
		rightIntake.set(value);
	}
	
	/**
	 * Sets the speed
	 * 
	 * @param speed Speed (-1 < x < 1)
	 */
	public void setSpeed(double speed)
	{
		this.speed = speed;
		setSpeedLeft(speed);
		setSpeedRight(speed);
	}
	/**
	 * Stops the robot
	 */
	public void stop()
	{
		setSpeed(0);
	}
	
	/**
	 * use if using joysticks
	 * @param x
	 * @param y
	 */
	public void intake(double x, double y) {
		if(x>0.05) {
			leftIntake.set(x);
			rightIntake.set(x);
		}
		else if(y>0.05) {
			leftIntake.set(y);
			rightIntake.set(-y);
		}
		else {
			leftIntake.set(0);
			rightIntake.set(0);
		}
	}
}
