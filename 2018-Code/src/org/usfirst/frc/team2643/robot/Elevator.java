package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class Elevator
{
	private WPI_TalonSRX elevator;
	
	/**
	 * Initialize Elevator
	 * 
	 * @param lsm
	 *            - linear slide motor
	 */
	public Elevator(WPI_TalonSRX liftMotor)
	{
		this(liftMotor, 0);
	}

	/**
	 * Initialize Elevator
	 * 
	 * @param lsm
	 *            - linear slide motor
	 * @param profile
	 *            - PID profile
	 */
	public Elevator(WPI_TalonSRX liftMotor, int profile)
	{
		elevator = liftMotor;
		defaultPIDLSMotor();
		elevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, profile, 20);
	}

	/**
	 * Drop elevator before the start of teleop?
	 */
	public void dropElevator()
	{
		while (!RobotMap.elevatorLimitSwitch.get())
		{
			elevator.set(-0.55);
		}
	}

	/**
	 * Default PID profile (0)
	 */
	public void defaultPIDLSMotor()
	{
		elevator.setSensorPhase(true);
		elevator.selectProfileSlot(EnvironmentVariables.defaultPID, 0);
		elevator.config_kF(0, EnvironmentVariables.PIDF, 20);
		elevator.config_kP(0, EnvironmentVariables.PIDP, 20);
		elevator.config_kI(0, EnvironmentVariables.PIDI, 20);
		elevator.config_kD(0, EnvironmentVariables.PIDD, 20);
		elevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 20);
	}

	/**
	 * Configure PID profile
	 * 
	 * @param profile
	 *            - profile (0 or 1)
	 * @param fVal
	 *            - f constant
	 * @param pVal
	 *            - position constant
	 * @param iVal
	 *            - integral constant
	 * @param dVal
	 *            - derivative constant
	 */
	public void configPIDProfile(int profile, double fVal, double pVal, double iVal, double dVal)
	{
		elevator.setSensorPhase(true);
		elevator.selectProfileSlot(profile, 0);
		elevator.config_kF(0, fVal, 20);
		elevator.config_kP(0, pVal, 20);
		elevator.config_kI(0, iVal, 20);
		elevator.config_kD(0, dVal, 20);
		elevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, profile, 20);
	}

	/**
	 * Get encoder value for the elevator
	 * 
	 * @return - encoder value for the elevator
	 */
	public int getEncoderValues()
	{
		return elevator.getSensorCollection().getQuadraturePosition() / 2;
	}

	public void resetElevatorEncoder()
	{
		elevator.getSensorCollection().setQuadraturePosition(0, 20);
	}

	/**
	 * Convert Feet to encoders and move
	 * 
	 * @param pos
	 *            - Movement by feet
	 */
	public void moveElevatorToPosFeet(double feet)
	{
		elevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 20);
		int feetT = (int) (RobotMap.ticksPerFoot * feet);
		System.out.println("Moving to tick: " + feetT);
		elevator.set(ControlMode.Position, Math.abs(feetT));
	}
	
	/**
	 * Move elevator to position in ticks
	 */
	public void moveElevatorToPosTicks(int tick)
	{
		elevator.set(ControlMode.Position, tick);
	}

	/**
	 * Convert inches to encoder and move
	 * 
	 * @param pos
	 *            - movement in inches
	 */
	public void moveElevatorToPosInches(int inch)
	{
		int inchT = RobotMap.ticksPerInch * inch;
		elevator.set(ControlMode.Position, inchT);
	}

	/**
	 * move elevator by movement rate
	 * 
	 * @param value
	 *            - double value (can be used with joystick values)
	 */
	public void moveElevatorWithInput(double value)
	{
		value = -value;
		//System.out.println(!RobotMap.elevatorLimitSwitch.get() + "   Value: " + value);
		
		if (!RobotMap.elevatorLimitSwitch.get())
		{
			resetElevatorEncoder();
			if (value > 0)
			{
				elevator.set(ControlMode.PercentOutput, value);
			}
			else
			{
				elevator.set(ControlMode.PercentOutput, 0);
			}
		} else if (Math.abs(getEncoderValues()) > EnvironmentVariables.maxEncoderValue)
		{
			if (value < 0)
			{
				elevator.set(ControlMode.PercentOutput, value);
			}
			else
			{
				elevator.set(ControlMode.PercentOutput, 0);
			}
		} else
		{
			elevator.set(ControlMode.PercentOutput, value);
		}
	}

	/**
	 * move elevator using POV on controller with a constant speed
	 * 
	 * @param stick
	 *            - joystick controller
	 */
	public void moveElevatorUsingPOV(Joystick stick)
	{
		if (Math.abs(getEncoderValues()) > EnvironmentVariables.maxEncoderValue)
			if (stick.getPOV() == 180)
				elevator.set(ControlMode.PercentOutput, EnvironmentVariables.moveDownSpeed);
			else if (RobotMap.elevatorLimitSwitch.get())
				if (stick.getPOV() == 0)
					elevator.set(ControlMode.PercentOutput, EnvironmentVariables.moveUpSpeed);
				else if (stick.getPOV() == 0)
					elevator.set(ControlMode.PercentOutput, EnvironmentVariables.moveUpSpeed);
				else if (stick.getPOV() == 180)
					elevator.set(ControlMode.PercentOutput, EnvironmentVariables.moveDownSpeed);
				else
					elevator.set(0.0);
	}

	public void testButtoFunctionalityElevator()
	{
		if (RobotMap.opStick.getRawButton(1))
		{
			System.out.println("moving to 2 feet!");
			moveElevatorToPosFeet(2);
		} else if (RobotMap.opStick.getRawButton(2))
		{
			System.out.println("move to 3.5 feet!");
			moveElevatorToPosFeet(3.5);
		} else if (RobotMap.opStick.getRawButton(3))
		{
			System.out.println("move to 5 feet!");
			moveElevatorToPosFeet(5);
		} else if (RobotMap.opStick.getRawButton(4))
		{
			System.out.println("MAX feet 6");
			moveElevatorToPosFeet(6);
		}

		if (RobotMap.driveStick.getRawButton(1))
		{
			resetElevatorEncoder();
		}
	}
}
