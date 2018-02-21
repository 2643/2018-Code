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
		while (RobotMap.elevatorLimitSwitch.get())
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
	public int getEncoder()
	{
		return elevator.getSensorCollection().getQuadraturePosition() / 2;
	}

	public void resetEncoder()
	{
		elevator.getSensorCollection().setQuadraturePosition(0, 20);
	}

	/**
	 * Convert Feet to encoders and move
	 * 
	 * @param pos
	 *            - Movement by feet
	 */
	public void setPositionFeet(double feet)
	{
		int feetT = (int) (RobotMap.ticksPerFoot * feet);
		System.out.println("Moving to tick: " + feetT);
		setPosition(feetT);
	}

	/**
	 * Move elevator to position in ticks
	 */
	public void setPosition(double tick)
	{
		elevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 20);
		if (tick > EnvironmentVariables.maxEncoderValue)
		{
			elevator.set(ControlMode.Position, EnvironmentVariables.maxEncoderValue);
		} else
		{
			elevator.set(ControlMode.Position, tick);
		}
	}

	/**
	 * Convert inches to encoder and move
	 * 
	 * @param pos
	 *            - movement in inches
	 */
	public void setPosInches(int inch)
	{
		int inchT = RobotMap.ticksPerInch * inch;
		setPosition(inchT);
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

		// System.out.println(!RobotMap.elevatorLimitSwitch.get());
		if (!RobotMap.elevatorLimitSwitch.get())
		{
			resetEncoder();
			if (value > 0)
			{
				elevator.set(ControlMode.PercentOutput, value * 1.5);
			} else
			{
				elevator.set(ControlMode.PercentOutput, 0);
			}
		} else if (Math.abs(getEncoder()) > EnvironmentVariables.maxEncoderValue)
		{
			if (value < 0)
			{
				elevator.set(ControlMode.PercentOutput, value * 1.5);
			} else
			{
				elevator.set(ControlMode.PercentOutput, 0);
			}
		} else
		{
			elevator.set(ControlMode.PercentOutput, value * 1.5);
		}
	}

	public void moveDownToLimit()
	{
		if (RobotMap.elevatorLimitSwitch.get())
		{
			elevator.set(-0.25);
		} else
		{
			elevator.set(0);
			resetEncoder();
		}
	}

	public void moveUsingPot(double value)
	{
		value += 1;
		// System.out.println("New Pot Value: " + value);
		value = (int) (value * (EnvironmentVariables.maxEncoderValue / 2.0));
		// System.out.println("Value of encoder: " + value);

		setPosition(value);

		if (value == 0)
		{
			moveDownToLimit();
		} else
		{
			setPosition(value);
		}
	}

	public String getElevatorCurrent()
	{
		return "Elevator Current: " + elevator.getOutputCurrent();
	}

	/**
	 * move elevator using POV on controller with a constant speed
	 * 
	 * @param stick
	 *            - joystick controller
	 */
	public void moveElevatorUsingPOV(Joystick stick)
	{
		if (Math.abs(getEncoder()) > EnvironmentVariables.maxEncoderValue)
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

	public void presetLocations()
	{
		if (RobotMap.opStick.getRawButton(1))
		{
			System.out.println("moving to 2 feet!");
			setPosInches(1);
		} else if (RobotMap.opStick.getRawButton(2))
		{
			System.out.println("move to 3.5 feet!");
			setPosInches(7);
		} else if (RobotMap.opStick.getRawButton(3))
		{
			System.out.println("move to 5 feet!");
			setPosInches(15);
		}
	}

	public void testButtoFunctionalityElevator()
	{
		if (RobotMap.opStick.getRawButton(1))
		{
			System.out.println("moving to 2 feet!");
			setPositionFeet(2);
		} else if (RobotMap.opStick.getRawButton(2))
		{
			System.out.println("move to 3.5 feet!");
			setPositionFeet(3.5);
		} else if (RobotMap.opStick.getRawButton(3))
		{
			System.out.println("move to 5 feet!");
			setPositionFeet(5);
		} else if (RobotMap.opStick.getRawButton(4))
		{
			System.out.println("MAX feet 6");
			setPositionFeet(6);
		}

		/*
		 * if (RobotMap.driveStick.getRawButton(1)) { resetEncoder(); }
		 */
	}
}
