package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CurrentMotor {
	
	private final WPI_TalonSRX motor;
	private double maxAmps;
	
	
	public CurrentMotor(WPI_TalonSRX m, double maxamps)
	{
		motor = m;
		maxAmps = maxamps;
	}
	
	public SensorCollection getSensorCollection()
	{
		return motor.getSensorCollection();
	}
	/**
	 * percent output mode
	 */
	public void set(double val)
	{
		setAmps(val*maxAmps);
	}
	/**
	 * 
	 * @param valAmp the current ina mps
	 */
	public void setAmps(double valAmp)
	{
		valAmp = Utils.clamp(valAmp, -maxAmps, valAmp);
		motor.set(ControlMode.Current, valAmp);
	}
	
	public void setMaxAmps(double newMax)
	{
		maxAmps = newMax;
	}
	
}
