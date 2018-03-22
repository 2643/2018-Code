package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Drive
{

	private final WPI_TalonSRX leftDriveMaster;
	private final WPI_TalonSRX leftDriveSlave1;
	private final WPI_TalonSRX leftDriveSlave2;
	private final WPI_TalonSRX rightDriveMaster;
	private final WPI_TalonSRX rightDriveSlave1;
	private final WPI_TalonSRX rightDriveSlave2;
	
	private double currentLeftGoal;
	private double currentRightGoal;
	
	public Drive(WPI_TalonSRX l1, WPI_TalonSRX l2, WPI_TalonSRX r1, WPI_TalonSRX r2)
	{
		this(l1, l2, null, r1, r2, null, null);
	}
	
	public Drive(WPI_TalonSRX l1, WPI_TalonSRX l2,  WPI_TalonSRX l3, WPI_TalonSRX r1, WPI_TalonSRX r2, WPI_TalonSRX r3, GyroScope gyro)
	{
		leftDriveMaster = l1;
		leftDriveSlave1 = l2;
		leftDriveSlave2 = l3;
		rightDriveMaster = r1;
		rightDriveSlave1 = r2;
		rightDriveSlave2 = r3;
		
		currentLimit(leftDriveMaster);
		currentLimit(rightDriveMaster);
				
		rightDriveSlave1.follow(rightDriveMaster);
		leftDriveSlave1.follow(leftDriveMaster);
		rightDriveSlave2.follow(rightDriveMaster);
		leftDriveSlave2.follow(leftDriveMaster);
		
		leftDriveMaster.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 20);
		rightDriveMaster.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 20);
		leftDriveMaster.setSensorPhase(true);
		rightDriveMaster.setSensorPhase(true);
	}

	public void currentLimit(WPI_TalonSRX motor)
	{
		motor.configContinuousCurrentLimit(36, 0);
		motor.configPeakCurrentLimit(40, 0);
		motor.configPeakCurrentDuration(150, 0);
		motor.enableCurrentLimit(true);
	}
	
	// returns left encoder ticks, which is for some reason twice the actual
	public int getLeftEncoder()
	{
		return leftDriveMaster.getSensorCollection().getQuadraturePosition() / 2;
	}

	// returns right encoder ticks, which is for some reason twice the actual
	public int getRightEncoder()
	{
		return rightDriveMaster.getSensorCollection().getQuadraturePosition() / 2;
	}

	public int getAverageEncoder()
	{
		return ((getRightEncoder() + getLeftEncoder()) / 2);
	}

	public int getMaxEncoder()
	{
		return Math.max(getRightEncoder(), getLeftEncoder());
	}

	public int getMinEncoder()
	{
		return Math.min(getRightEncoder(), getLeftEncoder());
	}

	public void resetLeftEncoder()
	{
		leftDriveMaster.getSensorCollection().setQuadraturePosition(0, 0);
	}

	public void resetRightEncoder()
	{
		rightDriveMaster.getSensorCollection().setQuadraturePosition(0, 0);
	}

	public void resetAllEncoder()
	{
		resetLeftEncoder();
		resetRightEncoder();
	}

	public double getCurrentRightGoal()
	{
		return currentRightGoal;
	}

	public double getCurrentLeftGoal()
	{
		return currentLeftGoal;
	}
	

	/**
	 * Sets all motors on the right side of the robot to the given value
	 * 
	 * @param The
	 *            speed to set the motors to
	 */
	public void setLeftSpeed(double speed)
	{
		leftDriveMaster.set(speed);
	}
		
	public void setRightSpeed(double speed)
	{
		rightDriveMaster.set(-speed);
	}

	/**
	 * Sets the robot to a certain speed
	 * 
	 * @param speed
	 *            The speed to set the motor to. Make sure it is not too fast or you
	 *            will consume too much voltage
	 */
	public void setAllSpeed(double speed)
	{ // Set all of the motors to the given value.
		setLeftSpeed(speed);
		setRightSpeed(speed);
	}
	
//	public void setAllCurrent(double speed)
//	{ // Set all of the motors to the given value.
//		setLeftCurrent(speed);
//		setRightCurrent(speed);
//	}

	/**
	 * Stops the robot
	 */
	public void stopAllSpeed()
	{
		setAllSpeed(0);
	}
	
	public void SRXarcadeDrive(double x, double y)
	{
		if(RobotMap.DEBUG) {
			RobotMap.leftDrive1.getOutputCurrent();
			RobotMap.rightDrive1.getOutputCurrent();
		}
		
		if (x < -0.03 || x > 0.03)
		{ // If the given axis is pushed to the left or right, then set them to the value
			// of that axis. 0.05 is the given dead zone and can be increased or decreased.
			// Currently the deadzone is 5%
			setRightSpeed(-x);
			setLeftSpeed(x);
		} else if (y > 0.03 || y < 0.03)
		{ // If the given axis is pushed up or
			setRightSpeed(y);
			setLeftSpeed(y);
		} else
		{ // If no joystick activity, set all motors to 0.
			setAllSpeed(0);
		}
	}

	public void SRXtankDrive(double x, double y)
	{ // Very basic tank drive.
		if(RobotMap.DEBUG) {
			RobotMap.leftDrive1.getOutputCurrent();
			RobotMap.rightDrive1.getOutputCurrent();
		}
		setLeftSpeed(x);
		setRightSpeed(y);
	}
}
