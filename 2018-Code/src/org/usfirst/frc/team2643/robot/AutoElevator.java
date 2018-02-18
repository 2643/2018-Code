package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class AutoElevator {
	
	private final WPI_TalonSRX elevator;
	private int currentGoal;
	
	
	public AutoElevator(WPI_TalonSRX e)
	{
		this(e,0);
	}
	
	public AutoElevator(WPI_TalonSRX e, int profile)
	{
		elevator = e;
		elevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 20);
	}
	
	/**
	 * Default PID profile (0)
	 */
	public void defaultPIDLSMotor()
	{
		elevator.setSensorPhase(true);
		elevator.selectProfileSlot(EnvironmentVariables.defaultPID, 0);
		elevator.config_kF(0, EnvironmentVariables.PIDF, 20);
		elevator.config_kP(0, EnvironmentVariables.PIDP, 20);
		elevator.config_kI(0, EnvironmentVariables.PIDI, 20);
		elevator.config_kD(0, EnvironmentVariables.PIDD, 20);
		elevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 20);
	}

	/**
	 * Configure PID profile
	 * 
	 * @param profile
	 *            - profile (0 or 1)
	 * @param fVal
	 *            - f constant
	 * @param pVal
	 *            - position constant
	 * @param iVal
	 *            - integral constant
	 * @param dVal
	 *            - derivative constant
	 */
	public void configPIDProfile(int profile, double fVal, double pVal, double iVal, double dVal)
	{
		elevator.setSensorPhase(true);
		elevator.selectProfileSlot(profile, 0);
		elevator.config_kF(0, fVal, 20);
		elevator.config_kP(0, pVal, 20);
		elevator.config_kI(0, iVal, 20);
		elevator.config_kD(0, dVal, 20);
		elevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, profile, 20);
	}
	
	/**
	 * Get encoder value for the elevator
	 * @return - encoder value for the elevator
	 */
	public int getEncoder()
	{
		return elevator.getSensorCollection().getQuadraturePosition() / 2;
	}

	public void resetEncoder()
	{
		elevator.getSensorCollection().setQuadraturePosition(0, 20);
	}
	
	public int getGoal()
	{
		return currentGoal;
	}
	
	public static int feetToEncoderTicks(double feet)
	{
		return (int)(feet*RobotMap.feetToTicks(feet));
	}
	
	public void setPosition(int ticks)
	{
		currentGoal = ticks;
		elevator.set(ControlMode.Position, ticks);
	}
	
	public void setUpElevate(int ticks)
	{
		AutoState.robotState = AutoState.ELEVATING;
		resetEncoder();	
		setPosition(ticks);
	}
	
	public boolean executeElevate()
	{
		boolean isFinished = false;
		if(Math.abs(getGoal()-getEncoder()) < RobotMap.ACCEPTABLE_ENCODER_ERROR)
		{
			isFinished = true;
		}
		return isFinished;
	}
	
	public void finishElevate()
	{
		AutoState.robotState = AutoState.NOTHING;
		resetEncoder();
		setPosition(0);
	}
}
