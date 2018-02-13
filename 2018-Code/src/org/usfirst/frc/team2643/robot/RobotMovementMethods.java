package org.usfirst.frc.team2643.robot;

public class RobotMovementMethods {



	



	//
	//	/**
	//	 * Prepares the robot for a turn
	//	 */
	//	public static void setUpTurn(int ticks)
	//	{
	//		AutoState.robotState = AutoState.TURNING;
	//		//stop all drive motors and reset everything
	//		Robot.drive.stopAllSpeed();
	//		Robot.drive.resetAll();
	//		AutoState.leftEncoderGoal = -ticks;
	//		AutoState.rightEncoderGoal = ticks;
	//	}
	//
	//
	//	public static boolean executeTurn()
	//	{
	//		boolean isFinished = false;
	//
	//		//if it hasn't reached its goal yet
	//		if(!checkIfReachedGoal(Robot.drive.getRightEncoder(), AutoState.rightEncoderGoal))
	//		{
	//			//set the motor in the correct direction
	//			Robot.drive.setRightSpeed(
	//					Utils.getSign(AutoState.rightEncoderGoal)*RobotMap.cruisingSpeed);
	//		}
	//		else
	//		{
	//			isFinished = true;
	//		}
	//		//if it hasn't reached its goal yet
	//		if(!checkIfReachedGoal(Robot.drive.getLeftEncoder(), AutoState.leftEncoderGoal))
	//		{
	//			//set the motor in the correct direction
	//			Robot.drive.setLeftSpeed(
	//					Utils.getSign(AutoState.leftEncoderGoal)
	//					*  RobotMap.cruisingSpeed);
	//		}
	//		else
	//		{
	//			isFinished = true;
	//		}
	//
	//		return isFinished;
	//	}
	//
	//	public static void finishTurn()
	//	{
	//		AutoState.robotState = AutoState.NOTHING;
	//		//stop all drive motors and reset everything
	//		Robot.drive.stopAllSpeed();
	//		Robot.drive.resetAll();
	//		AutoState.leftEncoderGoal = 0;
	//		AutoState.rightEncoderGoal = 0;
	//
	//	}
	//
	//
	//	/**
	//	 * Prepares the robot for a move
	//	 */
	//	public static void setUpMove(int ticks)
	//	{
	//		AutoState.robotState = AutoState.MOVING;
	//		//stop all drive motors and reset everything
	//		Robot.drive.resetAll();
	//		Robot.drive.stopAllSpeed();
	//
	//		AutoState.encoderGoal = ticks;
	//	}
	//
	//
	//	public static boolean executeMove()
	//	{
	//		boolean isFinished = false;
	//
	//		//if it hasn't reached its goal yet
	//		if(!(checkIfReachedGoal(Robot.drive.getRightEncoder(), AutoState.encoderGoal) || checkIfReachedGoal(Robot.drive.getLeftEncoder(), AutoState.encoderGoal)))
	//		{
	//			//set the motor in the correct direction
	//			Robot.drive.setRightSpeed(
	//					Utils.getSign(AutoState.encoderGoal)*RobotMap.cruisingSpeed);
	//		}
	//		else
	//		{
	//			isFinished = true;
	//		}
	//
	//		return isFinished;
	//	}
	//
	//	public static void finishMove()
	//	{
	//		AutoState.robotState = AutoState.NOTHING;
	//		//stop all drive motors and reset everything
	//		Robot.drive.stopAllSpeed();
	//		Robot.drive.resetAll();
	//		AutoState.leftEncoderGoal = 0;
	//		AutoState.rightEncoderGoal = 0;
	//	}
	//
	//	/**
	//	 *  Sets up release arms
	//	 */
	//	public static void setUpReleaseArms() {
	//		AutoState.robotState = AutoState.MOVING;
	//		//stop all drive motors and reset everything
	//		Robot.drive.stopAllSpeed();
	//		Robot.drive.resetAll();
	//
	//		AutoState.movingForwardToReleaseArms = true;
	//	}
	//
	//	/**
	//	 *Releases the arms in the beginning of the match 
	//	 * @return Returns if it is finished yet
	//	 */
	//	public static boolean executeReleaseArms(){
	//		//whether the method is complete
	//		boolean isFinished = false;
	//
	//		//This means that it moves forward to shake the arm
	//		if(AutoState.movingForwardToReleaseArms)
	//		{
	//			//if it has not already
	//			if(!checkIfReachedGoal(Robot.drive.getAverageEncoder(), AutoState.armsForwardEncoderGoal))
	//			{
	//				Robot.drive.setAllSpeed(RobotMap.cruisingSpeed);
	//			}
	//			else
	//			{
	//				AutoState.movingForwardToReleaseArms = false;
	//				AutoState.movingBackwardToReleaseArms = true;
	//				Robot.drive.stopAllSpeed();
	//				Robot.drive.resetAll();
	//			}
	//		}
	//		else if(AutoState.movingBackwardToReleaseArms)
	//		{
	//			if(!checkIfReachedGoal(Robot.drive.getAverageEncoder(), -AutoState.armsBackwardEncoderGoal))
	//			{
	//				Robot.drive.setAllSpeed(-RobotMap.cruisingSpeed);
	//			}
	//			else
	//			{
	//				AutoState.movingBackwardToReleaseArms = false;
	//				Robot.drive.stopAllSpeed();
	//			}
	//		}
	//		else
	//		{
	//			isFinished = true;
	//		}
	//		return isFinished;
	//	}
	//	/**
	//	 * Cleans up after robot finishes releasing
	//	 */
	//	public static void finishReleaseArms()
	//	{
	//		AutoState.robotState = AutoState.NOTHING;
	//		//stop all drive motors and reset everything
	//		Robot.drive.stopAllSpeed();
	//		Robot.drive.resetAll();
	//		AutoState.movingForwardToReleaseArms = false;
	//		AutoState.movingBackwardToReleaseArms = false;
	//		AutoState.movingForwardToReleaseArms = false;
	//	}
	//
	//	/**
	//	 * Checks if the current encoder value has reached its goal
	//	 * @param currentEncoder the current value of the encoder
	//	 * @param encoderGoal the goal to be reached
	//	 * @return true if the goal has been reached, false if the goal has not
	//	 */
	//	private static boolean checkIfReachedGoal(int currentEncoder, int encoderGoal)
	//	{
	//		if(encoderGoal == 0)
	//		{
	//			return true;
	//		}
	//		else if(encoderGoal < 0)
	//		{
	//			if(currentEncoder < encoderGoal)
	//			{
	//				return true;
	//			}
	//		}
	//		else if(encoderGoal > 0)
	//		{
	//			if(currentEncoder > encoderGoal)
	//			{
	//				return true;
	//			}
	//		}
	//		return false;
	//	}
	//
	//
	//
	//
	/**
	 * Basic tank drive
	 * @param x
	 * @param y
	 */
	public static void SRXtankDrive(double x, double y) { //Very basic tank drive.
		Robot.drive.setLeftSpeed(x);
		Robot.drive.setRightSpeed(y);
	}



}