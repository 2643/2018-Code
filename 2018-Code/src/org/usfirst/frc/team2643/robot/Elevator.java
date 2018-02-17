package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.Joystick;

public class Elevator
{
	/**
	 * Initialize Elevator
	 * 
	 * @param lsm
	 *            - linear slide motor
	 */

	public Elevator()
	{
		defaultPIDLSMotor();
	}

	/**
	 * Initialize Elevator
	 * 
	 * @param lsm
	 *            - linear slide motor
	 * @param profile
	 *            - PID profile
	 */
	public Elevator(int profile)
	{
		defaultPIDLSMotor();
		RobotMap.elevator1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, profile, 20);
	}

	/**
	 * Drop elevator before the start of teleop?
	 */
	public void dropElevator()
	{
		while (!RobotMap.elevatorLimitSwitch.get())
		{
			RobotMap.elevator1.set(-0.55);
		}
	}

	/**
	 * Default PID profile (0)
	 */
	public void defaultPIDLSMotor()
	{
		RobotMap.elevator1.selectProfileSlot(EnvironmentVariables.defaultPID, 0);
		RobotMap.elevator1.config_kF(0, EnvironmentVariables.PIDF, 20);
		RobotMap.elevator1.config_kP(0, EnvironmentVariables.PIDP, 20);
		RobotMap.elevator1.config_kI(0, EnvironmentVariables.PIDI, 20);
		RobotMap.elevator1.config_kD(0, EnvironmentVariables.PIDD, 20);
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
		RobotMap.elevator1.selectProfileSlot(profile, 0);
		RobotMap.elevator1.config_kF(0, fVal, 20);
		RobotMap.elevator1.config_kP(0, pVal, 20);
		RobotMap.elevator1.config_kI(0, iVal, 20);
		RobotMap.elevator1.config_kD(0, dVal, 20);
	}

	/**
	 * Get encoder value for the elevator
	 * 
	 * @return - encoder value for the elevator
	 */
	public int getEncoderValues()
	{
		return RobotMap.elevator1.getSensorCollection().getQuadraturePosition() / 2;
	}

	public void resetElevatorEncoder()
	{
		RobotMap.elevator1.getSensorCollection().setQuadraturePosition(0, 20);
	}

	/**
	 * Convert Feet to encoders and move
	 * 
	 * @param pos
	 *            - Movement by feet
	 */
	public void moveElevatorToPosFeet(double feet)
	{
		int feetT = (int) (RobotMap.ticksPerFoot * feet);
		RobotMap.elevator1.set(ControlMode.Position, feetT);
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
		RobotMap.elevator1.set(ControlMode.Position, inchT);
	}

	/**
	 * move elevator by movement rate
	 * 
	 * @param value
	 *            - double value (can be used with joystick values)
	 */
	public void moveElevatorWithInput(Joystick stick)
	{
		double value = stick.getRawAxis(1);
		System.out.println(RobotMap.elevatorLimitSwitch.get());
		if (Math.abs(getEncoderValues()) > EnvironmentVariables.maxEncoderValue)
		{
			if (value < 0)
			{
				RobotMap.elevator1.set(ControlMode.PercentOutput, value);
			}
		} else if (RobotMap.elevatorLimitSwitch.get())
		{
			if (value > 0)
			{
				RobotMap.elevator1.set(ControlMode.PercentOutput, value);
			}
		} else
		{
			RobotMap.elevator1.set(ControlMode.PercentOutput, value);
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
				RobotMap.elevator1.set(ControlMode.PercentOutput, EnvironmentVariables.moveDownSpeed);
			else if (RobotMap.elevatorLimitSwitch.get())
				if (stick.getPOV() == 0)
					RobotMap.elevator1.set(ControlMode.PercentOutput, EnvironmentVariables.moveUpSpeed);
				else if (stick.getPOV() == 0)
					RobotMap.elevator1.set(ControlMode.PercentOutput, EnvironmentVariables.moveUpSpeed);
				else if (stick.getPOV() == 180)
					RobotMap.elevator1.set(ControlMode.PercentOutput, EnvironmentVariables.moveDownSpeed);
				else
					RobotMap.elevator1.set(0.0);
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
