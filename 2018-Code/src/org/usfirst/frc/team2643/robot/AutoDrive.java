package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class AutoDrive extends Drive
{
	private GyroScope gyro;
	private double degreesGoal;
	private double currentLeftGoal;
	private double currentRightGoal;
	
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
	
	public void setUpPositionMove(int ticks)
	{
		AutoState.robotState = AutoState.MOVING;
		resetAllEncoder();
		setAllMotorPosition(ticks);
	}

	public boolean executePositionMove()
	{
		if(Math.abs(getCurrentLeftGoal() -getLeftEncoder()) < RobotMap.ACCEPTABLE_ENCODER_ERROR ||
				Math.abs(getCurrentRightGoal()-getRightEncoder()) < RobotMap.ACCEPTABLE_ENCODER_ERROR)
		{
			return true;
		}
		else 
		{
			System.out.println(getLeftEncoder());
			return false;
		}
	}

	public void finishPositionMove()
	{
		AutoState.robotState = AutoState.NOTHING;
		resetAllEncoder();
		setAllMotorPosition(0);
	}
	
	/**
	 * Prepares the robot for a move
	 */
	public void setUpMove(int ticks)
	{
		AutoState.robotState = AutoState.MOVING;
		//stop all drive motors and reset everything
		Robot.drive.resetAllEncoder();
		Robot.drive.stopAllSpeed();

		currentLeftGoal = ticks;
	}


	public boolean executeMove()
	{ 
		//if it has reached its goal yet
		if(Utils.checkIfReachedGoal(Robot.drive.getRightEncoder(), currentRightGoal) 
				|| Utils.checkIfReachedGoal(Robot.drive.getLeftEncoder(), currentLeftGoal))
		{
			return true;
		}
		else
		{
			//set the motor in the correct direction
			Robot.drive.setRightSpeed(
					Utils.getSign(currentRightGoal)*RobotMap.cruisingSpeed);
			Robot.drive.setLeftSpeed(
					Utils.getSign(currentLeftGoal)*RobotMap.cruisingSpeed);
			return false;
		}
	}

	public void finishMove()
	{
		AutoState.robotState = AutoState.NOTHING;
		//stop all drive motors and reset everything
		Robot.drive.stopAllSpeed();
		Robot.drive.resetAllEncoder();
		currentLeftGoal = 0;
		currentRightGoal = 0;
	}
	
}
