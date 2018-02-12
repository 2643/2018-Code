package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;

public class Drive {
	
	public static final double MAX_ALLOWABLE_CURRENT = 2;
	
	private final WPI_TalonSRX leftDrive1;
	private final WPI_TalonSRX leftDrive2;
	private final WPI_TalonSRX leftDrive3; 
	private final WPI_TalonSRX rightDrive1;
	private final WPI_TalonSRX rightDrive2; 
	private final WPI_TalonSRX rightDrive3; 
	
	private double multiplier = 1;
	
	private double leftCurrent;
	private double rightCurrent;	
	
	public Drive(
			WPI_TalonSRX l1, WPI_TalonSRX l2, WPI_TalonSRX l3,
			WPI_TalonSRX r1, WPI_TalonSRX r2, WPI_TalonSRX r3)
	{
		leftDrive1 = l1;
		leftDrive2 = l2;
		leftDrive3 = l3;
		rightDrive1 = r1;
		rightDrive2 = r2;
		rightDrive3 = r3;
	}
	
	public double getCurrent()
	{
		return leftCurrent + rightCurrent;
	}
	
	public int getLeftEncoder()
	{
		return leftDrive1.getSensorCollection().getQuadraturePosition();
	}

	public int getRightEncoder()
	{
		return rightDrive1.getSensorCollection().getQuadraturePosition();
	}

	public int getAverageEncoder()
	{
		return((getRightEncoder() + getLeftEncoder())/2);
	}
	
	public int getMaxEncoder()
	{
		return Math.max(getRightEncoder(), getLeftEncoder());
	}
	
	public int getMinEncoder()
	{
		return Math.min(getRightEncoder(), getLeftEncoder());
	}
	
	public void resetLeftEncoder()
	{
		leftDrive1.getSensorCollection().setQuadraturePosition(0,0);
	}
	
	public void resetRightEncoder()
	{
		rightDrive1.getSensorCollection().setQuadraturePosition(0,0);
	}
	
	public void resetAll()
	{
		resetLeftEncoder();
		resetRightEncoder();
	}
	
	public void setLeftMotors(double speed) {
		double val = speed*multiplier;
		leftDrive1.set(-val);
		leftDrive2.set(-val);
		leftDrive3.set(-val);
		leftCurrent = val;
	}
	/**
	 * Sets all motors on the right side of the robot to the given value
	 * @param The speed to set the motors to
	 */
	public void setRightMotors(double speed) {
		double val = speed*multiplier;
		rightDrive1.set(val);
		rightDrive2.set(val);
		rightDrive3.set(val);
		rightCurrent = val;
	}
	
	/**
	 * Sets the robot to a certain speed
	 * @param speed The speed to set the motor to. Make sure it is not too fast or you will consume too much voltage
	 */
	public void setAllMotors(double speed) { //Set all of the motors to the given value. 
		setLeftMotors(speed);
		setRightMotors(speed);
	}
	/**
	 * Stops the robot
	 */
	public void stopAllMotors()
	{
		setAllMotors(0);
	}
	
}
