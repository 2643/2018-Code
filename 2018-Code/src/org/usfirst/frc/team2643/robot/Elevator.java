package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

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
		lsMotor.selectProfileSlot(EnvironmentVariables.defaultPID, 0); 
		lsMotor.config_kF(0, EnvironmentVariables.PIDF, 20); 
		lsMotor.config_kP(0, EnvironmentVariables.PIDP, 20); 
		lsMotor.config_kI(0, EnvironmentVariables.PIDI, 20); 
		lsMotor.config_kD(0, EnvironmentVariables.PIDD, 20);
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
	 * Get encoder value for the elevator
	 * @return - encoder value for the elevator
	 */
	public int getEncoderValues()
	{
		return lsMotor.getSensorCollection().getQuadraturePosition() / 2;
	}
	
	/**
	 * Convert Feet to encoders and move
	 * @param pos - Movement by feet
	 */
	public void moveElevatorToPosFeet(int pos)
	{
		pos = RobotMap.feetToTicks(pos);
		lsMotor.set(ControlMode.Position, pos);
	}
	
	/**
	 * Convert inches to encoder and move
	 * @param pos - movement in inches
	 */
	public void moveElevatorToPosInches(int pos)
	{
		pos = RobotMap.feetToTicks(pos/12);
		lsMotor.set(ControlMode.Position, pos);
	}
	
	/**
	 * move elevator by movement rate
	 * @param value - double value (can be used with joystick values)
	 */
	public void moveElevatorWithInput(double value)
	{
		if(getEncoderValues() > EnvironmentVariables.maxEncoderValue)
			if(value < 0)
				lsMotor.set(ControlMode.PercentOutput, value);
		else
			lsMotor.set(ControlMode.PercentOutput, value);
	}
	
	/**
	 * move elevator using POV on controller with a constant speed
	 * @param stick - joystick controller
	 */
	public void moveElevatorUsingPOV(Joystick stick)
	{
		if(getEncoderValues() > EnvironmentVariables.maxEncoderValue)
			if(stick.getPOV() == 180)
				lsMotor.set(ControlMode.PercentOutput, EnvironmentVariables.moveDownSpeed);
		else
			if(stick.getPOV() == 0)
				lsMotor.set(ControlMode.PercentOutput, EnvironmentVariables.moveUpSpeed);
			else if(stick.getPOV() == 180)
				lsMotor.set(ControlMode.PercentOutput, EnvironmentVariables.moveDownSpeed);
	}
}
