package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class Drive
{

	private final WPI_TalonSRX leftDriveMaster;
	private final WPI_TalonSRX leftDriveSlave1;
	private final WPI_TalonSRX leftDriveSlave2;
	private final WPI_TalonSRX rightDriveMaster;
	private final WPI_TalonSRX rightDriveSlave1;
	private final WPI_TalonSRX rightDriveSlave2;
	
	public Drive(WPI_TalonSRX l1, WPI_TalonSRX l2, WPI_TalonSRX r1, WPI_TalonSRX r2)
	{
		this(l1, l2, null, r1, r2, null);
	}
	
	public Drive(WPI_TalonSRX l1, WPI_TalonSRX l2,  WPI_TalonSRX l3, WPI_TalonSRX r1, WPI_TalonSRX r2, WPI_TalonSRX r3)
	{
		leftDriveMaster = l1;
		leftDriveSlave1 = l2;
		leftDriveSlave2 = l3;
		rightDriveMaster = r1;
		rightDriveSlave1 = r2;
		rightDriveSlave2 = r3;
		
		currentLimit(leftDriveMaster);
		currentLimit(rightDriveMaster);
		
		//rampRate(leftDriveMaster);
		//rampRate(rightDriveMaster);
		
		leftDriveMaster.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
		rightDriveMaster.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 0);
		leftDriveMaster.setSensorPhase(false);
		rightDriveMaster.setSensorPhase(false);
	}
	
	public void setToPositionMode()
	{
		leftDriveMaster.set(ControlMode.Position,0);
		rightDriveMaster.set(ControlMode.Position,0);
	}
	
	public void setToPercentValue()
	{
		leftDriveMaster.set(ControlMode.PercentOutput, 1);
		rightDriveMaster.set(ControlMode.PercentOutput,1);
	}

	public void currentLimit(WPI_TalonSRX motor)
	{
		motor.configContinuousCurrentLimit(32, 0);
		motor.configPeakCurrentLimit(35, 0);
		motor.configPeakCurrentDuration(80, 0);
		motor.enableCurrentLimit(true);
	}
	
	public void rampRate(WPI_TalonSRX motor) {
		motor.configOpenloopRamp(0.2, 0);
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
	
	/**
	 * Sets all motors on the right side of the robot to the given value
	 * 
	 * @param speed - A double that is the speed the motor is set to
	 * 
	 */
	public void setLeftSpeed(double speed)
	{
		leftDriveMaster.set(speed);
	}
		/**
		 * Sets speed for right side
		 * 
		 * @param speed - A double that is the speed the motor is set to
		 */
	public void setRightSpeed(double speed)
	{
		rightDriveMaster.set(-speed);
	}
	/**
	 * Makes the motors on the left side go to an encoder distance of ticks
	 *
	 * @param ticks - A number
	 */
	public void setLeftEncoder(int ticks) {
		leftDriveMaster.set(ControlMode.Position, ticks);
	}
	/**
	 * Makes the motors on the right side go to an encoder distance of ticks
	 * 
	 * @param ticks - A number
	 */
	public void setRightEncoder(int ticks) {
		rightDriveMaster.set(ControlMode.Position, ticks);
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

	/**
	 * Stops the robot
	 */
	public void stopAllSpeed()
	{
		setAllSpeed(0);
	}
	/**
	 * Uses joystick axis to do arcade drive
	 * 
	 * @param x - x axis of joystick
	 * @param y - y axis of joystick
	 */
	public void SRXarcadeDrive(double x, double y)
	{
		if (x < -0.03 || x > 0.03)
		{ // If the given axis is pushed to the left or right, then set them to the value
			// of that axis. 0.05 is the given dead zone and can be increased or decreased.
			// Currently the deadzone is 5%
			setRightSpeed(x);
			setLeftSpeed(x);
		} else if (y > 0.03 || y < 0.03)
		{ // If the given axis is pushed up or down
			setRightSpeed(-y);
			setLeftSpeed(y);
		} else
		{ // If no joystick activity, set all motors to 0.
			setAllSpeed(0);
		}
	}
	/**
	 * 
	 * @param motor
	 * @return The current output
	 */
	public double getCurrent(WPI_TalonSRX motor)
	{
		return motor.getOutputCurrent();
	}
	/**
	 * 
	 * @param side
	 * @return
	 */
	public double totalCurrentDraw(String side)
	{
		double totalCurrent = 0;
		if(side.equals("left"))
		{
			totalCurrent = getCurrent(leftDriveMaster) + getCurrent(leftDriveSlave1) + getCurrent(leftDriveSlave2);
		}
		else if(side.equals("right"))
		{
			totalCurrent = getCurrent(rightDriveMaster) + getCurrent(rightDriveSlave1) + getCurrent(rightDriveSlave2);
		}
		
		return totalCurrent;
	}
	

	/**
	 * 
	 * @param stick
	 * When calling the method, all you have to do is input a joystick. For example,
	 * SRXmodifiedDrive(RobotMap.driveStick);
	 */
	public void SRXmodifiedDrive(Joystick stick) {
		double yAxis = -stick.getRawAxis(1); 	// The 2 variables are constantly updated
		double turnModifier = stick.getRawAxis(4); // Only purpose is to make things simpler and cuts down unnecessary code
		
		System.out.println(yAxis + "\t" + turnModifier);
		
		if(yAxis>0.03 || yAxis < -0.03 && turnModifier > 0.03) { // Ensures the robot moves in correspondence with movement of right stick
			/*if(yAxis>0.8||yAxis<-0.8) { // Checks whether robot is close to max speed. If it is, it subtracts the output of the opposite motor
				setLeftSpeed(yAxis - Math.abs(turnModifier));
				setRightSpeed(yAxis);
			}*/
			System.out.println("left");
				setLeftSpeed(yAxis);
				setRightSpeed(yAxis + Math.abs(turnModifier)); //Once the driver moves the right stick in the +x direction, add speed to the right
			
		}
		
		else if(yAxis > 0.03 || yAxis < -0.03 && turnModifier < -0.03) { // Same thing as last block for opposite side
			/*if(yAxis>0.8||yAxis<-0.8) {
				setLeftSpeed(yAxis);
				setRightSpeed(yAxis - Math.abs(turnModifier));
			}*/
			System.out.println("right");
				setLeftSpeed(yAxis);
				setRightSpeed(yAxis + Math.abs(turnModifier)); 
		}
		
		else if(yAxis > 0.03 || yAxis < -0.03/* &&  turnModifier < -0.03 || turnModifier < 0.03*/) {// Ensures that robot is going forward
			setLeftSpeed(yAxis);
			setRightSpeed(yAxis);
			System.out.println("forward");
		}
		
		else if(yAxis < 0.03 || yAxis > -0.03 && turnModifier < -0.03 || turnModifier > 0.03) {
			setLeftSpeed(turnModifier);
			setRightSpeed(-turnModifier);
		}
		
		else {
			stopAllSpeed();
		}
	}
	
}
