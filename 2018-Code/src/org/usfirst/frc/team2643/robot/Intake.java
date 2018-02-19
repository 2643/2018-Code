package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class Intake {
	private final WPI_TalonSRX leftIntake;
	private final WPI_TalonSRX rightIntake;
	private double speed = 0;
	private Timer timer;
	private double timeGoal;
	
	public Intake(WPI_TalonSRX left, WPI_TalonSRX right)
	{
		leftIntake = left;
		rightIntake = right;
		timer = new Timer();
	}
	
	public double getSpeed()
	{
		return speed;
	}
	
	/**
	 * use this for buttons
	 */
	public void intake(Joystick board)
	{
		if(board.getRawButton(RobotMap.intakeLeftButton))
		{
			setSpeedLeft(-1);
		}
		else if(board.getRawButton(RobotMap.outtakeLeftButton))
		{
			setSpeedLeft(1);
		}
		else if(board.getRawButton(RobotMap.bothIntakeButton))
		{
			setSpeed(1);
		}
		else if(board.getRawButton(RobotMap.intakeRightButton))
		{
			setSpeedRight(1);
		}
		else if(board.getRawButton(RobotMap.outtakeRightButton))
		{
			setSpeedRight(-1);
		}
		else if(board.getRawButton(RobotMap.bothOuttakeButton))
		{
			setSpeed(-1);
		}
		else
		{
			moveBothIntake(0);
		}
	}
	
	public void setSpeedLeft(double value)
	{
		leftIntake.set(-value);
	}
	
	public void setSpeedRight(double value)
	{
		rightIntake.set(value);
	}
	
	public void setSpeed(double speed)
	{
		this.speed = speed;
		setSpeedLeft(speed);
		setSpeedRight(speed);
	}
	
	public void stop()
	{
		setSpeed(0);
	}
	
	public void setUpIntake(double timeSec, double speed)
	{
		AutoState.robotState = AutoState.INTAKING;
		timer.reset();
		timer.start();
		timeGoal = timeSec;
		setSpeed(speed);
	}	
	
	public boolean executeIntake()
	{
		if(timer.get() >= timeGoal)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void finishIntake()
	{
		stop();
		timeGoal = 0;
		timer.stop();
		timer.reset();
	}
	
	public int autoIntake(int currentcase, double speed, double time)
	{
		if(!AutoState.inttaking)
		{
			setUpIntake(time, speed);
			AutoState.inttaking = true;
		}
		else
		{
			if(executeIntake()) 
			{
				finishIntake();
				AutoState.inttaking = false; 
				currentcase++;
			}
		}
		return currentcase;
	}
	
	/**
	 * use if using joysticks
	 * @param x
	 * @param y
	 */
	public void intake(double x, double y) {
		if(x>0.05) {
			leftIntake.set(-x);
			rightIntake.set(x);
		}
		else if(y>0.05) {
			leftIntake.set(y);
			rightIntake.set(-y);
		}
		else {
			leftIntake.set(0);
			rightIntake.set(0);
		}
	}
}
