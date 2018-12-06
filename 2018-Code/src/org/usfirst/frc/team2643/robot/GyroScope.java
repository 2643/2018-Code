package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class GyroScope
{
	/* TODO */
	ADXRS450_Gyro gyro;
//	private double kp = 0.18;
//	private double ki = 0.00125;
//	private double kd = 2 * 0.18;
//	private double pVal = 0;
//	private double pAc = 0;
//	private double pTotal = 0;
//	private int x = 0;
//	private BuiltInAccelerometer ac = new BuiltInAccelerometer();

	public GyroScope()
	{
		gyro = new ADXRS450_Gyro();
	}

	public double getAngle()
	{
		double g = gyro.getAngle();
		return (g/18.0)*90;
	}
	
	public void reset()
	{
		gyro.reset();
	}
	
	public void PID()
	{
		
	}

	public void calibrate()
	{
		gyro.calibrate();
	}
}
