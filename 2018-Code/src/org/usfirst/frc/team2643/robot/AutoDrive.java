package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class AutoDrive extends Drive
{
	private GyroScope gyro;
	private double degreesGoal;
	
	
	public AutoDrive(WPI_TalonSRX l1, WPI_TalonSRX l2, WPI_TalonSRX r1, WPI_TalonSRX r2, GyroScope gyroscope)
	{
		super(l1, l2, r1, r2);
		gyro = gyroscope;
	}
	public void setUpTurn(int ticks)
	{
		AutoState.robotState = AutoState.TURNING;
		resetAllEncoder();
		setLeftToPosition(-ticks);
		setRightToPosition(ticks);
	}

	public boolean executeTurn()
	{
		boolean isFinished = false;
		if(Math.abs(getCurrentLeftGoal() -getLeftEncoder()) < RobotMap.ACCEPTABLE_ENCODER_ERROR ||
				Math.abs(getCurrentRightGoal()-getRightEncoder()) < RobotMap.ACCEPTABLE_ENCODER_ERROR)
		{
			isFinished = true;
		}
		return isFinished;
	}

	public void finishTurn()
	{
		AutoState.robotState = AutoState.NOTHING;
		resetAllEncoder();
		setAllMotorPosition(0);
	}

	public void setUpGyroTurn(double degrees)
	{
		AutoState.robotState = AutoState.TURNING;
		resetAllEncoder();
		degreesGoal = degrees;
		setLeftSpeed(Utils.getSign(degrees)*RobotMap.cruisingSpeed);
		setRightSpeed(Utils.getSign(degrees)*RobotMap.cruisingSpeed);
	}
	
	public boolean executeGyroTurn()
	{
		if(Utils.checkIfReachedGoal(gyro.getAngle(),degreesGoal))
		{
			return true;
		}
		else
		{	
			return false;
		}
	}
	
	public void finishGyroTurn()
	{
		AutoState.robotState = AutoState.NOTHING;
		resetAllEncoder();
		gyro.reset();
		stopAllSpeed();
	}
	
	public void setUpMove(int ticks)
	{
		AutoState.robotState = AutoState.MOVING;
		resetAllEncoder();
		setAllMotorPosition(ticks);
	}

	public boolean executeMove()
	{
		if(Math.abs(getCurrentLeftGoal() -getLeftEncoder()) < RobotMap.ACCEPTABLE_ENCODER_ERROR ||
				Math.abs(getCurrentRightGoal()-getRightEncoder()) < RobotMap.ACCEPTABLE_ENCODER_ERROR)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	public void finishMove()
	{
		AutoState.robotState = AutoState.NOTHING;
		resetAllEncoder();
		setAllMotorPosition(0);
	}
	
}
