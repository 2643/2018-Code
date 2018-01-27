package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class RobotMovementMethods {

	
	public static int getLeftEncoder()
	{
		return RobotMap.leftEncoder.get();
	}
	
	public static int getRightEncoder()
	{
		return RobotMap.rightEncoder.get();
	}
	
	public static int getAverageEncoder()
	{
		return((getRightEncoder() + getLeftEncoder())/2);
	}

	
	/**
	 * Makes a 90 degree right turn
	 */
	public static void rightTurn()
	{
		if(!AutoState.turnInitialized)
		{
			setUpTurn();
		}
	}
	
	/**
	 * Prepares the robot for a turn
	 */
	public static void setUpTurn(int ticks)
	{
		AutoState.robotState = AutoState.TURNING;
		//stop all drive motors and reset everything
		stopAll();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();

		AutoState.leftEncoderGoal = -ticks;
		AutoState.rightEncoderGoal = ticks;
	}


	public static void executeTurn()
	{
		//if it hasnt reached its goal yet
		if(!checkIfReachedGoal(getRightEncoder(), AutoState.rightEncoderGoal))
		{
			//set the motor in the correct direction
			RobotMovementMethods.setRightMotors(
					Utils.getSign(AutoState.rightEncoderGoal)*RobotMap.cruisingSpeed);
		}
		else
		{
			finishTurn();
		}
		//if it hasnt reached its goal yet
		if(!checkIfReachedGoal(getLeftEncoder(), AutoState.leftEncoderGoal))
		{
			//set the motor in the correct direction
			RobotMovementMethods.setLeftMotors(
					Utils.getSign(AutoState.leftEncoderGoal)*RobotMap.cruisingSpeed);
		}
		else
		{
			finishTurn();
		}
	}

	public static void finishTurn()
	{
		AutoState.robotState = AutoState.NOTHING;
		//stop all drive motors and reset everything
		stopAll();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();

		AutoState.leftEncoderGoal = 0;
		AutoState.rightEncoderGoal = 0;
	}

	/**
	 * Checks if the current encoder value has reached its goal
	 * @param currentEncoder the current value of the encoder
	 * @param encoderGoal the goal to be reached
	 * @return true if the goal has been reached, false if the goal has not
	 */
	private static boolean checkIfReachedGoal(int currentEncoder, int encoderGoal)
	{
		if(encoderGoal == 0)
		{
			return true;
		}
		else if(encoderGoal < 0)
		{
			if(currentEncoder < encoderGoal)
			{
				return true;
			}
		}
		else if(encoderGoal > 0)
		{
			if(currentEncoder > encoderGoal)
			{
				return true;
			}
		}
		return false;
	}

	

	public void liftToSwitchHeight() {

	}

	//TODO This is broken please fix it
	public static void SRXarcadeDrive(double x, double y) {
		if(x<-0.05 || x>0.05) { //If the given axis is pushed to the left or right, then set them to the value of that axis. 0.05 is the given dead zone and can be increased or decreased. Currently the deadzone is 5%
			setRightMotors(x);
			setLeftMotors(x);
		}
		else if(y>0.05||y<0.05) { //If the given axis is pushed up or
			setRightMotors(y);
			setLeftMotors(-y);
		}
		else { //If no joystick activity, set all motors to 0.
			stopAll();
		}
	}

	/**
	 * Basic tank drive
	 * @param x
	 * @param y
	 */
	public static void SRXtankDrive(double x, double y) { //Very basic tank drive.
		setLeftMotors(x);
		setRightMotors(y);
	}
	/**
	 * Sets all motors on the left side of the robot to the given value
	 * @param speed The speed to set the motors to
	 */
	public static void setLeftMotors(double speed) { 
		RobotMap.t1.set(-speed);
		RobotMap.t2.set(-speed);
		RobotMap.t3.set(-speed);
	}
	/**
	 * Sets all motors on the right side of the robot to the given value
	 * @param The speed to set the motors to
	 */
	public static void setRightMotors(double speed) {
		RobotMap.t4.set(speed);
		RobotMap.t5.set(speed);
		RobotMap.t6.set(speed);
	}

	/**
	 * Sets the robot to a certain speed
	 * @param speed The speed to set the motor to. Make sure it is not too fast or you will consume too much voltage
	 */
	public static void setAll(double speed) { //Set all of the motors to the given value. 
		setLeftMotors(speed);
		setRightMotors(speed);
	}
	/**
	 * Stops the robot
	 */
	public static void stopAll()
	{
		setAll(0);
	}
	
	public static void setUpReleaseArms() {
		AutoState.robotState = AutoState.MOVING;
		//stop all drive motors and reset everything
		stopAll();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();
		
		AutoState.movingForwardToReleaseArm = true;
	}
	
	/**
	 *Releases the arms in the beginning of the match 
	 */
	public static void releaseArms(){
		if(AutoState.movingForwardToReleaseArm)
		{
			if(!checkIfReachedGoal(getAverageEncoder(), AutoState.armEncoderGoal))
			{
				setAll(RobotMap.cruisingSpeed);
			}
		}
	}

}