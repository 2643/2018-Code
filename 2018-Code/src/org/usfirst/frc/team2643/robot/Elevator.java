package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Elevator
{
	private WPI_TalonSRX lsMotor;
	
	/**
	 * Initialize Elevator 
	 * @param lsm - linear slide motor
	 */
	public Elevator(WPI_TalonSRX lsm)
	{
		lsMotor = lsm;
		defaultPIDLSMotor();
		lsMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 20);
	}
	
	/**
	 * Initialize Elevator 
	 * @param lsm - linear slide motor
	 * @param profile - PID profile
	 */
	public Elevator(WPI_TalonSRX lsm, int profile)
	{
		lsMotor = lsm;
		defaultPIDLSMotor();
		lsMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, profile, 20);
	}
	
	/**
	 * Default PID profile (0)
	 */
	public void defaultPIDLSMotor()
	{
		lsMotor.selectProfileSlot(0, 0); 
		lsMotor.config_kF(0, 0.2, 20); 
		lsMotor.config_kP(0, 0.2, 20); 
		lsMotor.config_kI(0, 0, 20); 
		lsMotor.config_kD(0, 0, 20);
	}
	
	/**
	 * Configure PID profile
	 * @param profile - profile (0 or 1)
	 * @param fVal - f constant
	 * @param pVal - position constant
	 * @param iVal - integral constant
	 * @param dVal - derivative constant
	 */
	public void configPIDProfile(int profile, double fVal, double pVal, double iVal, double dVal)
	{
		lsMotor.selectProfileSlot(profile, 0); 
		lsMotor.config_kF(0, fVal, 20); 
		lsMotor.config_kP(0, pVal, 20); 
		lsMotor.config_kI(0, iVal, 20); 
		lsMotor.config_kD(0, dVal, 20);
	}
	
	/**
	 * Convert Feet to encoders and move
	 * @param pos - Movement by feet
	 */
	public void moveElevatorToPosFeet(int pos)
	{
		pos = pos * EnvironmentVariables.lsEncodersToFeet;
		lsMotor.set(ControlMode.Position, pos);
	}
	
	/**
	 * Convert inches to encoder and move
	 * @param pos
	 */
	public void moveElevatorToPosInches(int pos)
	{
		pos = pos * EnvironmentVariables.lsEncodersToInches;
		lsMotor.set(ControlMode.Position, pos);
	}
	
	/**
	 * move elevator by movement rate
	 * @param value - double value (can be used with joystick values)
	 */
	public void moveElevatorWithInput(double value)
	{
		lsMotor.set(ControlMode.PercentOutput, value);
	}
}
