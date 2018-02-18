package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;

public class Ramp
{
	private final Servo rr;
	private final WPI_TalonSRX rw;
	
	public Ramp(Servo rampRelease, WPI_TalonSRX rampWinch)
	{
		this(rampRelease, rampWinch, 0, 0.2, 0.2, 0, 0);
	}
	
	public Ramp(Servo rampRelease, WPI_TalonSRX rampWinch, int profile, double pidF, double pidP, double pidI, double pidD)
	{
		rr = rampRelease;
		rw = rampWinch;
		configPIDProfile(profile, pidF, pidP, pidI, pidD);
	}
	
	public void configPIDProfile(int profile, double fVal, double pVal, double iVal, double dVal)
	{
		rw.setSensorPhase(true);
		rw.selectProfileSlot(profile, 0);
		rw.config_kF(0, fVal, 20);
		rw.config_kP(0, pVal, 20);
		rw.config_kI(0, iVal, 20);
		rw.config_kD(0, dVal, 20);
		rw.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, profile, 20);
	}
	
	public double getEncoder()
	{
		return rw.getSensorCollection().getQuadraturePosition() / 2.0;
	}
	
	public void resetEncoder()
	{
		rw.getSensorCollection().setQuadraturePosition(0, 0);
	}
	
	public void releaseRamp()
	{
		rr.set(1);
	}
	
	public void winchUp()
	{
		rw.set(1);
	}
}
