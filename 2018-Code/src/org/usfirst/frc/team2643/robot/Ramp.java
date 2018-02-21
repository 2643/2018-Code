package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

public class Ramp
{
	private final Servo rr;
	private final WPI_TalonSRX rw;
	boolean ableToRelease = false;
	Timer time = new Timer();

	public Ramp(Servo rampRelease, WPI_TalonSRX rampWinch)
	{
		this(rampRelease, rampWinch, 0, 0.2, 0.2, 0, 0);
	}

	public Ramp(Servo rampRelease, WPI_TalonSRX rampWinch, int profile, double pidF, double pidP, double pidI,
			double pidD)
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
		// rw.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, profile, 20);
	}

	/*
	 * public double getEncoder() { return
	 * rw.getSensorCollection().getQuadraturePosition() / 2.0; }
	 * 
	 * public void resetEncoder() {
	 * rw.getSensorCollection().setQuadraturePosition(0, 0); }
	 */

	public void keepRampUp()
	{
		rr.setAngle(0);
	}

	public void releaseRamp(Joystick board)
	{
		if (board.getRawButton(RobotMap.safetyButton) && board.getRawButton(RobotMap.rampReleaseButton))
		{
			if (RobotMap.inTestStage)
			{
				rr.setAngle(180);
				ableToRelease = true;
			} else if (!RobotMap.inTestStage && time.getMatchTime() < 30)
			{
				rr.setAngle(180);
				ableToRelease = true;
			}
		}
	}

	public void winchDown(Joystick board)
	{
		if (board.getRawButton(RobotMap.safetyButton) && board.getRawButton(RobotMap.rampDownButton))
		{
			if(RobotMap.inTestStage)
			{
				rw.set(-1);
			}
			else if (ableToRelease)
			{
				rw.set(-1);
			}
		}
		else
		{
			rw.set(0);
		}
	}
	
	public void winchUp(Joystick board)
	{
		if (board.getRawButton(RobotMap.safetyButton) && board.getRawButton(RobotMap.rampUpButton))
		{
			if(RobotMap.inTestStage)
			{
				rw.set(1);
			}
			else if (ableToRelease)
			{
				rw.set(1);
			}
		}
		else
		{
			rw.set(0);
		}
	}
}
