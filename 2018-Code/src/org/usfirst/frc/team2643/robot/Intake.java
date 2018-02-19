package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
	
	public void setSpeed(double speed)
	{
		this.speed = speed;
		leftIntake.set(-speed);
		rightIntake.set(speed);
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
	
	public static void intake(double x, double y) {
		if(x>0.05) {
			RobotMap.leftIntake.set(-x);
			RobotMap.rightIntake.set(x);
		}
		else if(y>0.05) {
			RobotMap.leftIntake.set(y);
			RobotMap.rightIntake.set(-y);
		}
		else {
			RobotMap.leftIntake.set(0);
			RobotMap.rightIntake.set(0);
		}
	}
}
