package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class Elevator
{
	private WPI_TalonSRX elevator;
	private WPI_TalonSRX elevatorSlave;
	private boolean atBot = false;
	
	double elevatorIncrease;
	/**
	 * Initialize Elevator
	 * 
	 * @param lsm
	 *            - linear slide motor
	 */
	public Elevator(WPI_TalonSRX liftMotor, WPI_TalonSRX slaveMotor)
	{
		this(liftMotor, slaveMotor, 0);
	}

	/**
	 * Initialize Elevator
	 * 
	 * @param lsm
	 *            - linear slide motor
	 * @param profile
	 *            - PID profile
	 */
	public Elevator(WPI_TalonSRX liftMotor, WPI_TalonSRX slaveMotor, int profile)
	{
		elevator = liftMotor;
		elevatorSlave = slaveMotor;
		defaultPIDLSMotor();
		elevatorSlave.follow(elevator);
		elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 20);
		elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 20);

	}
	
	public void currentLimit() {
		elevator.configContinuousCurrentLimit(35, 0);
		elevator.configPeakCurrentLimit(39, 0);
		elevator.configPeakCurrentDuration(150, 0);
		elevator.enableCurrentLimit(true);
	}
	
	/**
	 * Drop elevator before the start of teleop?
	 */
	public void dropElevator()
	{
		if(!RobotMap.elevatorLimitSwitch.get()) {
			elevator.set(0);
			resetEncoder();
		}
		else{
			
			elevator.set(-0.4);
		}
		
		if(RobotMap.DEBUG)
		{
			System.out.println("E Limit Switch: " + RobotMap.elevatorLimitSwitch.get());
		}
	}

	public void dropElevator(double speed)
	{
		while (RobotMap.elevatorLimitSwitch.get())
		{
			elevator.set(-speed);
			//System.out.println("Limit Switch got?: " + RobotMap.elevatorLimitSwitch.get());
		}
		atBot = true;
		resetEncoder();
	}
	
	/**
	 * Default PID profile (0)
	 */
	public void defaultPIDLSMotor()
	{
		elevator.setSensorPhase(true);
		elevator.selectProfileSlot(RobotMap.defaultPID, 0);
		elevator.config_kF(0, RobotMap.PIDF, 20);
		elevator.config_kP(0, RobotMap.PIDP, 20);
		elevator.config_kI(0, RobotMap.PIDI, 20);
		elevator.config_kD(0, RobotMap.PIDD, 20);
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
		return elevator.getSensorCollection().getQuadraturePosition();
	}

	public void resetEncoder()
	{
		elevator.getSensorCollection().setQuadraturePosition(0, 20);
	}
	
	public void usingButtons(Joystick buttons) {
		if(buttons.getRawButton(RobotMap.elevatorUp)) {
			elevator.set(0.6);
		}
		else if(buttons.getRawButton(RobotMap.elevatorDown)) {
			elevator.set(-0.3);
		}
		else {
			elevator.set(0);
		}
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
		elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 20);
		if (tick > RobotMap.maxEncoderValue)
		{
			elevator.set(ControlMode.Position, RobotMap.maxEncoderValue);
		}
		else
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
		} else if (Math.abs(getEncoder()) > RobotMap.maxEncoderValue)
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
		if(getEncoder() > 0 && !RobotMap.elevatorLimitSwitch.get())
		{
			elevator.set(-0.20);
			if(RobotMap.elevatorLimitSwitch.get())
			{
				elevator.set(0);
				resetEncoder();
			}
		}
	}

	public void moveUsingPot(double value)
	{
		value += 1;
		//System.out.println("New Pot Value: " + value);
		value = (int) (value * (RobotMap.maxEncoderValue / 2.0));
		//System.out.println("New New Pot Value: " + value);
		//System.out.println("Ze Stupid Encoder Vals: " + getEncoder());

		
		setPosition(value);
	}
	
	public double getCurrent(WPI_TalonSRX motor)
	{
		return motor.getOutputCurrent();
	}
	
	public double getTotalCurrent()
	{
		return getCurrent(elevator) + getCurrent(elevatorSlave);
	}
	
	public String getElevatorCurrent()
	{
		return "Elevator Current: " + (elevator.getOutputCurrent() + elevatorSlave.getOutputCurrent());
	}
	
	
	public void buttonPosControl(int buttonUp, int buttonDown) {
		elevator.set(ControlMode.Position, RobotMap.encoderTick);
		
		elevatorIncrease = (RobotMap.opBoard.getThrottle() + 1)/2;
		
		if(RobotMap.opBoard.getRawButton(buttonDown)) {
			if(RobotMap.encoderTick<0) {
				RobotMap.encoderTick = 0;
			}
			else {
				RobotMap.encoderTick -= 110 + (110*elevatorIncrease);
			}
		}
		else if(RobotMap.opBoard.getRawButton(buttonUp)) {
			if(RobotMap.encoderTick>RobotMap.maxEncoderValue) {
				RobotMap.encoderTick = RobotMap.maxEncoderValue;
			}
			else {
				RobotMap.encoderTick += 110 + (110*elevatorIncrease);
			}
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
		if (Math.abs(getEncoder()) > RobotMap.maxEncoderValue)
			if (stick.getPOV() == 180)
				elevator.set(ControlMode.PercentOutput, RobotMap.moveDownSpeed);
			else if (RobotMap.elevatorLimitSwitch.get())
				if (stick.getPOV() == 0)
					elevator.set(ControlMode.PercentOutput, RobotMap.moveUpSpeed);
				else if (stick.getPOV() == 0)
					elevator.set(ControlMode.PercentOutput, RobotMap.moveUpSpeed);
				else if (stick.getPOV() == 180)
					elevator.set(ControlMode.PercentOutput, RobotMap.moveDownSpeed);
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
