package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class AutoDrive extends Drive
{
	
	public AutoDrive(WPI_TalonSRX l1, WPI_TalonSRX l2, WPI_TalonSRX r1, WPI_TalonSRX r2)
	{
		super(l1, l2, r1, r2);
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

	public void setUpMove(int ticks)
	{
		AutoState.robotState = AutoState.MOVING;
		resetAllEncoder();
		setAllMotorPosition(ticks);
	}

	public boolean executeMove()
	{
		boolean isFinished = false;
		if(Math.abs(getCurrentLeftGoal() -getLeftEncoder()) < RobotMap.ACCEPTABLE_ENCODER_ERROR ||
				Math.abs(getCurrentRightGoal()-getRightEncoder()) < RobotMap.ACCEPTABLE_ENCODER_ERROR)
		{
			isFinished = true;
		}
		return isFinished;
	}

	public void finishMove()
	{
		AutoState.robotState = AutoState.NOTHING;
		resetAllEncoder();
		setAllMotorPosition(0);
	}


	/**
	 * Sets up release arms
	 */
	public void setUpReleaseArms() {
		AutoState.robotState = AutoState.MOVING;
		resetAllEncoder();
		setAllMotorPosition(AutoState.armsForwardEncoderGoal);
		AutoState.movingForwardToReleaseArms = true;
	}

	/**
	 *Releases the arms in the beginning of the match 
	 * @return Returns if it is finished yet
	 */
	public boolean executeReleaseArms(){
		//whether the method is complete
		boolean isFinished = false;

		//This means that it moves forward to shake the arm
		if(AutoState.movingForwardToReleaseArms)
		{
			if(Math.abs(getCurrentLeftGoal() -getLeftEncoder()) > RobotMap.ACCEPTABLE_ENCODER_ERROR ||
					Math.abs(getCurrentRightGoal()-getRightEncoder()) > RobotMap.ACCEPTABLE_ENCODER_ERROR)
			{
				resetAllEncoder();
				setAllMotorPosition(-AutoState.armsBackwardEncoderGoal);
				AutoState.movingForwardToReleaseArms = false;
				AutoState.movingBackwardToReleaseArms = true;
			}
		}
		else if(AutoState.movingBackwardToReleaseArms)
		{
			if(Math.abs(getCurrentLeftGoal() -getLeftEncoder()) > RobotMap.ACCEPTABLE_ENCODER_ERROR ||
					Math.abs(getCurrentRightGoal()-getRightEncoder()) > RobotMap.ACCEPTABLE_ENCODER_ERROR)
			{
				AutoState.movingBackwardToReleaseArms = false;
				isFinished = true;
			}
		}
		else
		{
			isFinished = true;
		}
		return isFinished;
	}
	/**
	 * Cleans up after robot finishes releasing
	 */
	public void finishReleaseArms()
	{
		AutoState.robotState = AutoState.NOTHING;
		resetAllEncoder();
		setAllMotorPosition(0); 
		AutoState.movingForwardToReleaseArms = false;
		AutoState.movingBackwardToReleaseArms = false;
	}
	
}
