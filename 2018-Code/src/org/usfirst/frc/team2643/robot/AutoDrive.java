package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class AutoDrive extends Drive
{
	private GyroScope gyro;
	private double degreesGoal;
	private int leftEncoderGoal;
	private int rightEncoderGoal;
	
	
	public AutoDrive(WPI_TalonSRX l1, WPI_TalonSRX l2, WPI_TalonSRX l3, WPI_TalonSRX r1, WPI_TalonSRX r2, WPI_TalonSRX r3, GyroScope gyroscope)
	{
		super(l1, l2, l3, r1, r2, r3);
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
		gyro.reset();
		degreesGoal = degrees;
		setLeftSpeed(Utils.getSign(degreesGoal)*RobotMap.cruisingSpeed);
		setRightSpeed(-Utils.getSign(degreesGoal)*RobotMap.cruisingSpeed);
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
	
	
	/**
	 * Prepares the robot for a move
	 */
	public void setUpMove(int ticks)
	{
		AutoState.robotState = AutoState.MOVING;
		//stop all drive motors and reset everything
		resetAllEncoder();
		stopAllSpeed();

		leftEncoderGoal = ticks;
		rightEncoderGoal = ticks; 
	}


	public boolean executeMove()
	{ 
		//if it has reached its goal yet
		if(Utils.checkIfReachedGoal(getRightEncoder(), leftEncoderGoal) 
				|| Utils.checkIfReachedGoal(getLeftEncoder(), rightEncoderGoal))
		{
			return true;
		}
		else
		{
			//set the motor in the correct direction
			setLeftSpeed(
					Utils.getSign(leftEncoderGoal)*RobotMap.cruisingSpeed);
			setRightSpeed(
					Utils.getSign(rightEncoderGoal)*RobotMap.cruisingSpeed);
			return false;
		}
	}

	public void finishMove()
	{
		AutoState.robotState = AutoState.NOTHING;
		//stop all drive motors and reset everything
		stopAllSpeed();
		resetAllEncoder();
		leftEncoderGoal = 0;
		rightEncoderGoal = 0;
	}
	
	public int autoMove(int currentcase, int ticks)
	{
		if(!AutoState.moving)
		{
			setUpMove(ticks);
			AutoState.moving = true;
		}
		else if(executeMove())
		{
			finishMove();
			AutoState.moving = false;
			currentcase++;
		}
		return currentcase;
	}
	
	public int autoTurn(int currentcase, double degrees)
	{
		if(!AutoState.turning)
		{
			setUpGyroTurn(degrees);
			AutoState.turning = true;
		}	
		else
		{ 
			if(executeGyroTurn())
			{
				finishGyroTurn();
				AutoState.turning = false;
				currentcase++;
			}
		}
		return currentcase;
	}
}
