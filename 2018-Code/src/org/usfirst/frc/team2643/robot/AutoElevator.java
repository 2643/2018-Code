package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class AutoElevator extends Elevator{
	
	private int currentGoal;
	
	public AutoElevator(WPI_TalonSRX e)
	{
		this(e,0);
	}
	
	public AutoElevator(WPI_TalonSRX e, int profile)
	{
		super(e, profile);
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
		super.setPosition(ticks);
	}
	
	public void setUpElevate(int ticks)
	{
		AutoState.robotState = AutoState.ELEVATING;
		super.resetEncoder();	
		setPosition(ticks);
	}
	
	public boolean executeElevate()
	{
		boolean isFinished = false;
		if(Math.abs(getGoal()-super.getEncoder()) < RobotMap.ACCEPTABLE_ENCODER_ERROR)
		{
			isFinished = true; 
		}
		return isFinished;
	}
	
	public void finishElevate()
	{
		AutoState.robotState = AutoState.NOTHING;
		super.resetEncoder();
		setPosition(0);
	}
	
	public int autoElevate(int currentcase, int ticks)
	{
		if(!AutoState.elevating)
		{
			setUpElevate(ticks);
			AutoState.elevating = true;
		}	
		else
		{ 
			if(executeElevate())
			{
				finishElevate();
				AutoState.elevating = false;
				currentcase++;
			}
		}
		return currentcase;
	}
}
