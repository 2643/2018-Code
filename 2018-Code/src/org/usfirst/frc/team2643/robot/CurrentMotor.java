package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CurrentMotor extends WPI_TalonSRX{
	
	private double maxAmps;
	
	
	public CurrentMotor(int port, double maxamps)
	{
		super(port);
		maxAmps = maxamps;
	}
	
	
	/**
	 * percent output mode is now redirected
	 */
	@Override
	public void set(double val)
	{
		setAmps(val*maxAmps);
	}
	/**
	 * 
	 * @param valAmp the current in amps
	 */
	public void setAmps(double valAmp)
	{
		valAmp = Utils.clamp(valAmp, -maxAmps, valAmp);
		super.set(ControlMode.Current, valAmp);
	}
	
	public void setMaxAmps(double newMax)
	{
		maxAmps = newMax;
	}
}
