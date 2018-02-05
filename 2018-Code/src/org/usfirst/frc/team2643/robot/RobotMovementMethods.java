package org.usfirst.frc.team2643.robot;

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
	
	public static int getMaxEncoder()
	{
		return Math.max(getRightEncoder(), getLeftEncoder());
	}


	/**
	 * Prepares the robot for a turn
	 */
	public static void setUpTurn(int ticks)
	{
		AutoState.robotState = AutoState.TURNING;
		//stop all drive motors and reset everything
		stopAllMotors();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();

		AutoState.leftEncoderGoal = -ticks;
		AutoState.rightEncoderGoal = ticks;
	}


	public static boolean executeTurn()
	{
		boolean isFinished = false;
		
		//if it hasn't reached its goal yet
		if(!checkIfReachedGoal(getRightEncoder(), AutoState.rightEncoderGoal))
		{
			//set the motor in the correct direction
			RobotMovementMethods.setRightMotors(
					Utils.getSign(AutoState.rightEncoderGoal)*RobotMap.cruisingSpeed);
		}
		else
		{
			isFinished = true;
		}
		//if it hasn't reached its goal yet
		if(!checkIfReachedGoal(getLeftEncoder(), AutoState.leftEncoderGoal))
		{
			//set the motor in the correct direction
			RobotMovementMethods.setLeftMotors(
					Utils.getSign(AutoState.leftEncoderGoal)
					*  RobotMap.cruisingSpeed);
		}
		else
		{
			isFinished = true;
		}
		
		return isFinished;
	}

	public static void finishTurn()
	{
		AutoState.robotState = AutoState.NOTHING;
		//stop all drive motors and reset everything
		stopAllMotors();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();

		AutoState.leftEncoderGoal = 0;
		AutoState.rightEncoderGoal = 0;
		
	}

	
	/**
	 * Prepares the robot for a move
	 */
	public static void setUpMove(int ticks)
	{
		AutoState.robotState = AutoState.MOVING;
		//stop all drive motors and reset everything
		stopAllMotors();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();
		
		AutoState.encoderGoal = ticks;
	}
	

	public static boolean executeMove()
	{
		boolean isFinished = false;
		
		//if it hasn't reached its goal yet
		if(!(checkIfReachedGoal(getRightEncoder(), AutoState.encoderGoal) || checkIfReachedGoal(getLeftEncoder(), AutoState.encoderGoal)))
		{
			//set the motor in the correct direction
			RobotMovementMethods.setRightMotors(
					Utils.getSign(AutoState.encoderGoal)*RobotMap.cruisingSpeed);
		}
		else
		{
			isFinished = true;
		}
		
		return isFinished;
	}

	public static void finishMove()
	{
		AutoState.robotState = AutoState.NOTHING;
		//stop all drive motors and reset everything
		stopAllMotors();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();

		AutoState.leftEncoderGoal = 0;
		AutoState.rightEncoderGoal = 0;
	}
	
	/**
	 *  Sets up release arms
	 */
	public static void setUpReleaseArms() {
		AutoState.robotState = AutoState.MOVING;
		//stop all drive motors and reset everything
		stopAllMotors();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();

		AutoState.movingForwardToReleaseArms = true;
	}

	/**
	 *Releases the arms in the beginning of the match 
	 * @return Returns if it is finished yet
	 */
	public static boolean executeReleaseArms(){
		//whether the method is complete
		boolean isFinished = false;
		
		//This means that it moves forward to shake the arm
		if(AutoState.movingForwardToReleaseArms)
		{
			//if it has not already
			if(!checkIfReachedGoal(getAverageEncoder(), AutoState.armsForwardEncoderGoal))
			{
				setAllMotors(RobotMap.cruisingSpeed);
			}
			else
			{
				AutoState.movingForwardToReleaseArms = false;
				AutoState.movingBackwardToReleaseArms = true;
				RobotMap.leftEncoder.reset();
				RobotMap.rightEncoder.reset();
				stopAllMotors();
			}
		}
		else if(AutoState.movingBackwardToReleaseArms)
		{
			if(!checkIfReachedGoal(getAverageEncoder(), -AutoState.armsBackwardEncoderGoal))
			{
				setAllMotors(-RobotMap.cruisingSpeed);
			}
			else
			{
				AutoState.movingBackwardToReleaseArms = false;
				stopAllMotors();
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
	public static void finishReleaseArms()
	{
		AutoState.robotState = AutoState.NOTHING;
		//stop all drive motors and reset everything
		stopAllMotors();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();

		AutoState.movingForwardToReleaseArms = false;
		AutoState.movingBackwardToReleaseArms = false;
		AutoState.movingForwardToReleaseArms = false;
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



	public void liftToSwitchHeight() //TODO
	{
		
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
			stopAllMotors();
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
		if (AutoState.limitMotorOverElevator &&
				AutoState.elevatorPower > AutoState.elevatorPowerLimit) {
			speed = AutoState.motorPowerLimit;
		}
		
		RobotMap.l1.set(-speed);
		RobotMap.l2.set(-speed);
		RobotMap.l3.set(-speed);
		AutoState.motorPower = speed/2;
	}
	/**
	 * Sets all motors on the right side of the robot to the given value
	 * @param The speed to set the motors to
	 */
	public static void setRightMotors(double speed) {
		if (AutoState.limitMotorOverElevator &&
				AutoState.elevatorPower > AutoState.elevatorPowerLimit) {
			speed = AutoState.motorPowerLimit;
		}
		RobotMap.r4.set(speed);
		RobotMap.r5.set(speed);
		RobotMap.r6.set(speed);
		AutoState.motorPower = speed/2;
	}

	/**
	 * Sets the robot to a certain speed
	 * @param speed The speed to set the motor to. Make sure it is not too fast or you will consume too much voltage
	 */
	public static void setAllMotors(double speed) { //Set all of the motors to the given value. 
		setLeftMotors(speed);
		setRightMotors(speed);
		AutoState.motorPower = speed;
	}
	/**
	 * Stops the robot
	 */
	public static void stopAllMotors()
	{
		setAllMotors(0);
	}	

}