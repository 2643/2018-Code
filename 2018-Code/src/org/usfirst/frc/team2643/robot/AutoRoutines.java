package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;

public class AutoRoutines extends Robot {
	private final int //Drive States
			startMove = 0,
			endMove = 1,
			startTurn = 2,
			endTurn = 3, 
			startToSwitch = 4,
			endToSwitch = 5,
			releaseCube = 6,
			stopRelease = 7,
			//stopAtSwitch = 8,
			//finishedAuto = 9,
			dropIntake = 10,
			stopDrop = 14,
			raiseElevator = 11,
			stopElevator = 12,
			endCase = 13,
			moveBack = 15,
			stopBack = 16,
			secondTurn = 17,
			gyroStartMove = 18,
			state19 = 19,
			state20 = 20,
			state21 = 21;
			
private final int //Auto Constants under some random encoder ticks (457)
//			encoderTicksToSwitch = 3208, //168 Inches (4073)
			ninetyDegreeTurn = 95;
//			encoderTicksToMidField = 4358, //5400
//			encoderTicksFromSideToSide = 3915, //211 inches (5115)
//			encoderTicksFromMidFieldToSwitch = 401; //32 inches (775)

	/*private final int //Auto Constants Under 360 conditions
			encoderTicksToSwitch = 3208,
			gyroTicksToSwitch = 38,
			encoderTicksToMidField = 4354,
			encoderTicksFromSideToSide = 3915,
			encoderTicksFromMidFieldToSwitch = 401;
			*/
	private double
			outTakeSpeed = -0.8,
			leftMotorSpeed = 0.56,
			rightMotorSpeed = 0.5,
			leftTurningSpeed = 0.55,
			rightTurningSpeed = 0.55,
			dropIntakeSpeed = 0.25,
			holdElevatorSpeed = 0.15,
			raiseElevatorSpeed = 0.5,
			movementIncrease = 0;
	
	private boolean turnTwo = false,
			doneTurning = false;
	//private boolean otherSide = false;
	
	/*
	 * Notes:
	 * 
	 */
	
	public Timer timer = new Timer();
	
	//private GyroScope gyro = new GyroScope();
	//private BuiltInAccelerometer ac = new BuiltInAccelerometer();
//	private Boolean once = false;
	
//	private double kp = 0.18;
//	private double kd = 2 * 0.18;
//	private double ki = 0.00125;
//	
//	private double pVal = 0;
//	private double pTotal = 0;
//	private double pAc = 0;
	
	public void crossAutoLine() {
		switch(RobotMap.autoState) {
		case startMove:
		{
			timer.start();
			drive.setLeftSpeed(0.5);
			drive.setRightSpeed(0.5);
			System.out.println("Finished Start");
			RobotMap.autoState = endMove;
		break;
		}
		case endMove:
		{
			if(Timer.getMatchTime()>15) {
				break;
			}
			if(timer.get()>0.9) {
				drive.setAllSpeed(0);
				System.out.println("Finished Cross Auto Line");
				break;
			}
			break;
		}
		}
	}
	
	
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	public void doSwitch(boolean isRight) {
		switch(RobotMap.autoState) {
		case startMove:
		{	//Start move case will have the robot to start moving forward toward its goal.
			System.out.println("startedMove");
			timer.start();
			drive.setLeftSpeed(0.5);
			drive.setRightSpeed(0.5);
			//intake.setSpeedLeft(0.25);
			System.out.println("Finished Start");
			RobotMap.autoState = endMove;
		break;
		}
		case endMove:
		{
			System.out.println("Testing End move");
			if(timer.get()>1.3) {
				Robot.drive.setAllSpeed(0);
				RobotMap.autoState = startTurn;
			break;
			}
		break;
		}
		case startTurn:
		{	
			if(isRight) {
				System.out.println("Started Turn");
				Robot.drive.setLeftSpeed(-leftTurningSpeed);
				Robot.drive.setRightSpeed(rightTurningSpeed);
				RobotMap.autoState = endTurn;
			break;
			}
			else {
				System.out.println("Started Turn");
				Robot.drive.setLeftSpeed(leftTurningSpeed);
				Robot.drive.setRightSpeed(-rightTurningSpeed);
				RobotMap.autoState = endTurn;
			break;
			}
		}
		case endTurn:
		{
			System.out.println("Testing End Turn" + "\t" + Math.abs(Robot.gyro.getAngle()));
			if(Math.abs(Robot.gyro.getAngle())>ninetyDegreeTurn) {
				Robot.drive.setAllSpeed(0);
				RobotMap.autoState = startToSwitch;
			break;
			}
		break;
		}
		
		case startToSwitch:
		{
			System.out.println("Started to switch");
			drive.setLeftSpeed(0.4);
			drive.setRightSpeed(0.4);
			timer.start();
			RobotMap.autoState = endToSwitch;
		break;
		}
		case endToSwitch:
		{
			System.out.println("Testing end To Switch");
			if(timer.get() > 0.8) {
				drive.setAllSpeed(0);
				timer.reset();
				RobotMap.autoState = releaseCube;
			break;
			}
		break;
		}
		case releaseCube:
		{
			System.out.println("Releasing cube");
			intake.setSpeed(outTakeSpeed);
			RobotMap.autoState = stopRelease;
		break;
		}
		case stopRelease:
		{
			System.out.println("Testing stopRelease");
			if(timer.get()>1) {
				intake.setSpeed(0);
				RobotMap.autoState = moveBack;
			break;
			}
		break;
		}
		case moveBack:
		{
			drive.setLeftSpeed(-leftMotorSpeed * 0.5);
			drive.setRightSpeed(-rightMotorSpeed * 0.5);
			timer.start();
			RobotMap.autoState = stopBack;
		break;
		}
		case stopBack:
		{
			if(timer.get()>1.2) {
				drive.setAllSpeed(0);
				timer.stop();				System.out.println("finished auto!!");
			break;
			}
			if(Timer.getMatchTime()>15) {
				break;
			}
		break;
		}
	}
	}
	
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public void middleCube() {
		switch(RobotMap.autoState) {
		case startMove:
		{
			timer.start();
			drive.setLeftSpeed(0.4);
			drive.setRightSpeed(0.4);
			//RobotMap.elevator1.set(ControlMode.Position, 800);
			//intake.setSpeedLeft(0.38);
			System.out.println("Finished Start");
			RobotMap.autoState = endMove;
		break;
		}
		case endMove:
		{
			if(Timer.getMatchTime()>15) {
				break;
			}
			if(timer.get()>2.5) {
				drive.setAllSpeed(0);
				intake.setSpeed(0);
				//RobotMap.elevator1.set(0.13);
				System.out.println("Finished Cross Auto Line");
				RobotMap.autoState = releaseCube;
				break;
			}
			break;
		}
		case releaseCube:
		{
			intake.setSpeed(-0.8);
			System.out.println("Started ReleaseCube");
			timer.start();
			RobotMap.autoState = stopRelease;
			break;
		}
		case stopRelease:
		{	
			if(timer.get()>2.5) {
				intake.setSpeed(0);
				timer.stop();
				System.out.println("Dropped Cube");
				RobotMap.autoState = endCase;
				break;
			}
		break;
		}
		case endCase:
		{
			intake.setSpeed(0);
			//RobotMap.elevator1.set(0);
			drive.setAllSpeed(0);
			System.out.println("Finished auto!!");
			if(Timer.getMatchTime()>15) {
				break;
			}
		break;
		}
		}
	}//End of method
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void doOppositeMiddle() {	//TODO Flip Polarities!!
		switch(RobotMap.autoState) {
			case startMove:
			{
				timer.start();
				drive.setLeftSpeed(0.4);
				drive.setRightSpeed(0.4);
				System.out.println("Finished Start");
				RobotMap.autoState = endMove;
			break;
			}
			/*case gyroStartMove:
			{
				timer.start();
				gyro.reset();
				System.out.println("Finished Gyro Start");
				while(Timer.getMatchTime() <= 0.6+movementIncrease)
				{
					double angle = gyro.getAngle();
					
					if(( (int) ac.getX() * 10 ) != pAc)
					{
						if(!once)
						{
							gyro.reset();
							once = !once;
							angle = gyro.getAngle();
							System.out.println("gyro reset: " + angle);
						}
						
						double motorVal = (angle * kp) + (kd * (angle - pVal)) + (ki * pTotal);
						drive.setLeftSpeed(-motorVal);
						drive.setRightSpeed(0.4);
						pVal = angle;
						pTotal += angle;
					}
					else
					{
						pAc = ac.getX() * 10;
						drive.setAllSpeed(0.4);
					}
					Timer.delay(0.004);
				}
				RobotMap.autoState = endMove;
				break;
			}
			*/
			case endMove:
			{
				if(Timer.getMatchTime()>15) {
					break;
				}
					if(timer.get()>0.6+movementIncrease) {
						drive.setAllSpeed(0);
						gyro.reset();
						System.out.println("Stopped Move");
						if(turnTwo) {
							if(doneTurning) {
								RobotMap.autoState = releaseCube;
							}else
							{
								RobotMap.autoState = secondTurn;
								break;
							}
						}
						else {
							RobotMap.autoState = startTurn;
							break;
						}
				}
				break;
			}
			
			
			case startTurn:
			{
					drive.setLeftSpeed(-0.5);
					drive.setRightSpeed(0.5);
					gyro.reset();
					intake.setSpeedLeft(0.25);
					timer.start();
					System.out.println("Started First Turn");
					RobotMap.autoState = endTurn;
				break;
			}
			
			
			case endTurn:
			{
				if(timer.get()>0.4) {
					intake.setSpeed(0);
				}
				
				if(Math.abs(gyro.getAngle())>ninetyDegreeTurn) {
					drive.stopAllSpeed();
					turnTwo = true;
					timer.reset();
					if(!doneTurning) {
						movementIncrease = 1.45;
					}
					else {
						movementIncrease = 0.7;
					}
					System.out.println("Ended Turn");
					RobotMap.autoState = startMove;
					break;
				}
				break;
			}
			
			
			case secondTurn:
			{
					drive.setLeftSpeed(0.5);
					drive.setRightSpeed(-0.5);
					gyro.reset();
					doneTurning = true;
					//RobotMap.elevator1.set(ControlMode.Position, 2650);
					System.out.println("Started Second Turn");
					RobotMap.autoState = endTurn;
				break;
			}
			
			
			case releaseCube:
			{
				intake.setSpeed(-0.8);
				System.out.println("Started ReleaseCube");
				timer.start();
				RobotMap.autoState = stopRelease;
				break;
			}
			
			
			case stopRelease:
			{	
				if(timer.get()>2) {
					intake.setSpeed(0);
					timer.stop();
					System.out.println("Dropped Cube");
					RobotMap.autoState = endCase;
					break;
				}
			break;
			}
			
			
			case endCase:
			{
				intake.setSpeed(0);
				RobotMap.elevator1.set(0);
				drive.setAllSpeed(0);
				System.out.println("Finished auto!!");
				if(Timer.getMatchTime()>15) {
					break;
				}
			break;
			}
			}
		}//End of method
	}