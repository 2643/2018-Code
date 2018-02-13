package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;

public class Drive {

	private final WPI_TalonSRX leftDriveMaster;
	private final WPI_TalonSRX leftDriveSlave;
	private final WPI_TalonSRX rightDriveMaster;
	private final WPI_TalonSRX rightDriveSlave; 
	
	private double currentLeftGoal = 0;
	private double currentRightGoal = 0;
	
	public Drive(
			WPI_TalonSRX l1, WPI_TalonSRX l2,
			WPI_TalonSRX r1, WPI_TalonSRX r2)
	{	
		leftDriveMaster = l1;
		leftDriveSlave = l2;

		leftDriveSlave.set(ControlMode.Follower, leftDriveMaster.getDeviceID());
		
		rightDriveMaster = r1;
		rightDriveSlave = r2;
		
		rightDriveSlave.set(ControlMode.Follower, rightDriveMaster.getDeviceID());
		
		
		leftDriveMaster.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
		rightDriveMaster.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
		leftDriveMaster.setSensorPhase(false);
		rightDriveMaster.setSensorPhase(false);
	}
	
	public void setToPositionMode()
	{
		leftDriveMaster.set(ControlMode.Position,255);
		rightDriveMaster.set(ControlMode.Position,255);
	}
	
	public void setToPercentValue()
	{
		leftDriveMaster.set(ControlMode.PercentOutput, 1);
		rightDriveMaster.set(ControlMode.PercentOutput,1);
	}
	
	
	public int getLeftEncoder()
	{
		return leftDriveMaster.getSensorCollection().getQuadraturePosition()/2;
	}
	
	public int getRightEncoder()
	{
		return rightDriveMaster.getSensorCollection().getQuadraturePosition()/2;
	}
	
	public int getAverageEncoder()
	{
		return((getRightEncoder() + getLeftEncoder())/2);
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
		leftDriveMaster.getSensorCollection().setQuadraturePosition(0,0);
	}
	
	public void resetRightEncoder()
	{
		rightDriveMaster.getSensorCollection().setQuadraturePosition(0,0);
	}
	
	public void resetAllEncoder()
	{
		resetLeftEncoder();
		resetRightEncoder();
	}
	
	public void setLeftMotorPosition(double location)
	{
		leftDriveMaster.set(location);
		currentLeftGoal = location;
	}
	
	public void setRightMotorPosition(double location)
	{
		leftDriveMaster.set(location);
		currentRightGoal = location;
	}
	
	public double getCurrentRightGoal()
	{
		return currentRightGoal;
	}
	
	public double getCurrentLeftGoal()
	{
		return currentLeftGoal;
	}
	
	public void setAllMotorPosition(double location)
	{
		setLeftMotorPosition(location);
		setRightMotorPosition(location);
	}
	
	public void setLeftSpeed(double speed) {
		leftDriveMaster.set(-speed);
	}
	/**
	 * Sets all motors on the right side of the robot to the given value
	 * @param The speed to set the motors to
	 */
	public void setRightSpeed(double speed) {
		rightDriveMaster.set(speed);
	}
	
	/**
	 * Sets the robot to a certain speed
	 * @param speed The speed to set the motor to. Make sure it is not too fast or you will consume too much voltage
	 */
	public void setAllSpeed(double speed) { //Set all of the motors to the given value. 
		setLeftSpeed(speed);
		setRightSpeed(speed);
	}
	/**
	 * Stops the robot
	 */
	public void stopAllSpeed()
	{
		setAllSpeed(0);
	}
	
	public void setUpTurn(int ticks)
	{
		AutoState.robotState = AutoState.TURNING;
		resetAllEncoder();
		setLeftMotorPosition(-ticks);
		setRightMotorPosition(ticks);
	}

	public boolean executeTurn()
	{
		boolean isFinished = false;
		if(Math.abs(getCurrentLeftGoal() -getLeftEncoder()) > RobotMap.ACCEPTABLE_ENCODER_ERROR ||
				Math.abs(getCurrentRightGoal()-getRightEncoder()) > RobotMap.ACCEPTABLE_ENCODER_ERROR)
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
		if(Math.abs(getCurrentLeftGoal() -getLeftEncoder()) > RobotMap.ACCEPTABLE_ENCODER_ERROR ||
				Math.abs(getCurrentRightGoal()-getRightEncoder()) > RobotMap.ACCEPTABLE_ENCODER_ERROR)
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
