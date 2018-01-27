package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class RobotMovementMethods {



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
		if(!checkIfReachedGoal(RobotMap.rightEncoder.get(), AutoState.rightEncoderGoal))
		{
			//set the motor in the correct direction
			RobotMovementMethods.setRightMotors(
					Utils.getSign(AutoState.rightEncoderGoal)*RobotMap.cruisingSpeed);
		}
		else
		{
			robotMap.set
		}
		//if it hasnt reached its goal yet
		if(!checkIfReachedGoal(RobotMap.righEncoder.get(), AutoState.rightEncoderGoal))
		{
			//set the motor in the correct direction
			RobotMovementMethods.setRightMotors(
					Utils.getSign(AutoState.rightEncoderGoal)*RobotMap.cruisingSpeed);
		}
	}

	public static void 

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

	//TODO TEST THIS SEPERATELY BEFORE TESTING AUTO CODE!!!
	/**
	 * turnRight()
	 * 		makes the robot turn 90 degrees
	 */ 
	public static void turnRight(){

		switch(RobotMap.turnRightState){
		case 1:
			//if debug is true, print debug statements
			if(RobotMap.DEBUG)
				System.out.println("First Case for turnRight() method");


			//set all of the drive motors to zero
			stopAll();

			//reset the encoders before turning


			//but it has not turned yet
			RobotMap.finishedTurning = false;
			RobotMap.turnRightState = 2;
			break;
		case 2:

			//if the debug statements are set to true, print the debug statements
			if(RobotMap.DEBUG)
				System.out.println("Second case for turnRight() method");

			//if the robot has not turned 90 degrees,
			if(Math.abs(RobotMap.leftEncoder.get()) < RobotMap.ticksTo90 && Math.abs(RobotMap.rightEncoder.get()) < RobotMap.ticksTo90){

				//set the left and right motors to opposite values so that it rotates
				setLeftMotors(0.4);
				setRightMotors(-0.4);

			}else{

				//once it is finished turning, set all the drive motors to zero
				stopAll();

				//reset the encoders after turning
				RobotMap.leftEncoder.reset();
				RobotMap.rightEncoder.reset();

				RobotMap.turnRightState = 1;
				//the robot has finished turning and stoppedBeforeTurning needs to be reset 
				RobotMap.finishedTurning = true;
			}
			break;					
		}
	}

	//TODO TEST THIS SEPERATELY BEFORE TESTING AUTO CODE!!!
	/**
	 * turnLeft() makes the robot turn left 90 degrees
	 */
	public static void turnLeft(){

		switch(RobotMap.turnLeftState){
		case 1: 

			//if debug is set to true. then print the debug statements
			if(RobotMap.DEBUG == true)
				System.out.println("First Case for turnLeft() method");

			//set all of the drive motors to zero
			stopAll();

			//reset the encoders before turning
			RobotMap.leftEncoder.reset();
			RobotMap.rightEncoder.reset();

			//but it has not finished turning
			RobotMap.finishedTurning = false;

			RobotMap.turnLeftState = 2;

			break;

		case 2:

			//if debug is true, print the debug statements
			if(RobotMap.DEBUG == true)
				System.out.println("Second Case for turnLeft() method");

			//if the robot has not turned ninety degrees...
			if(Math.abs(RobotMap.leftEncoder.get()) < RobotMap.ticksTo90 && Math.abs(-RobotMap.rightEncoder.get()) < RobotMap.ticksTo90){

				//set the drive motors to opposite values to rotate the robot
				setLeftMotors(-0.4);
				setRightMotors(0.4);
			}
			else //else the robot has turned ninety degrees
			{
				//set the motors to zero after turning
				stopAll();

				//reset the encoders after turning
				RobotMap.leftEncoder.reset();
				RobotMap.rightEncoder.reset();

				RobotMap.turnLeftState = 1;

				//the robot has finished turning
				RobotMap.finishedTurning = true;
			}
			break;
		}
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

	/**
	 *Releases the arms in the beginning of the match 
	 */
	public static void releaseArms(){
		//help TODO
	}

}