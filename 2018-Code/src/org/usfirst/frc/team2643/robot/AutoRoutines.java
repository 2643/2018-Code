package org.usfirst.frc.team2643.robot;

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
			stopAtSwitch = 8,
			finishedAuto = 9,
			dropIntake = 10;
			
	private final int //Auto Constants under some random encoder ticks (457)
			encoderTicksToSwitch = 3208, //168 Inches (4073)
			ninetyDegreeTurn = 38, 
			encoderTicksToMidField = 4358, //5400
			encoderTicksFromSideToSide = 3915, //211 inches (5115)
			encoderTicksFromMidFieldToSwitch = 401; //32 inches (775)

	/*private final int //Auto Constants Under 360 conditions
			encoderTicksToSwitch = 3208,
			gyroTicksToSwitch = 38,
			encoderTicksToMidField = 4354,
			encoderTicksFromSideToSide = 3915,
			encoderTicksFromMidFieldToSwitch = 401;
			*/
	private double
			outTakeSpeed = -1,
			motorSpeed = 0.5;
	
	private boolean otherSide = false;
	
	/*
	 * Notes:
	 * 
	 */
	
	
	private static Timer timer = new Timer();
	private Timer failTimer = new Timer();
	
	public void crossAutoLine() {
		switch(RobotMap.autoState) {
		case startMove:
		{
			timer.start();
			drive.setLeftSpeed(0.56);
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
			if(timer.get()>1.7) {
				drive.setAllSpeed(0);
				System.out.println("Finished Cross Auto Line");
				break;
			}
			break;
		}
		}
	}
	
	
	public void testCrossAuto() {
		switch(RobotMap.autoState) {
		case startMove:
		{
			timer.start();
			drive.setLeftSpeed(0.68);
			drive.setRightSpeed(0.5);
			intake.setSpeedLeft(0.28);
			System.out.println("Finished Start");
			RobotMap.autoState = dropIntake;
		break;
		}
		case dropIntake:
		{
			if(timer.get()>0.4) {
				intake.setSpeedLeft(0);
			}
			if(timer.get()>2) {
				drive.setAllSpeed(0);
				timer.stop();
				RobotMap.autoState = endMove;
			}
		}
		case endMove:
		{
			intake.setSpeed(-0.8);
			break;
		}
		}
	}

	public void botLeftSwitchLeft() {
		switch(RobotMap.autoState) {
			case startMove:
			{	
				System.out.println("started move");
				timer.start();
				failTimer.start();
				Robot.drive.setLeftSpeed(motorSpeed+0.1);
				Robot.drive.setRightSpeed(motorSpeed);
				//Robot.angleIntake.angleIntake(motorSpeed);
				Robot.intake.setSpeedLeft(motorSpeed);
				RobotMap.autoState = endMove;
			break;
			}
			case endMove:
			{
				System.out.println("Testing End move" + "\t" + Robot.drive.getRightEncoder());
				if(Math.abs(Robot.drive.getLeftEncoder())>encoderTicksToSwitch||Math.abs(Robot.drive.getRightEncoder())>encoderTicksToSwitch) {
					Robot.drive.setAllSpeed(0);
					RobotMap.autoState = startTurn;
				}
				if(timer.get()>0.5) {
					RobotMap.inclineMotor.set(0);
					Robot.intake.setSpeed(0);
					timer.stop();
				}
				if(failTimer.get()>5) {
					RobotMap.autoState = releaseCube;
				}
			break;
			}
			case startTurn:
			{
				System.out.println("Started Turn");
				Robot.drive.setLeftSpeed(motorSpeed+0.1);
				Robot.drive.setRightSpeed(-motorSpeed);
				failTimer.start();
				RobotMap.autoState = endTurn;
			break;
			}
			case endTurn:
			{
				System.out.println("Testing End Turn" + "\t" + Math.abs(Robot.gyro.getAngle()));
				if(Math.abs(Robot.gyro.getAngle())>ninetyDegreeTurn) {
					Robot.drive.setAllSpeed(0);
					RobotMap.autoState = startToSwitch;
				}
				if(failTimer.get()>5) {
					RobotMap.autoState = releaseCube;
				}
			break;
			}
			case startToSwitch:
			{
				System.out.println("Started to switch");
				Robot.drive.resetAllEncoder();
				Robot.drive.setLeftSpeed(motorSpeed+0.1);
				Robot.drive.setRightSpeed(motorSpeed);
				RobotMap.autoState = endToSwitch;
			break;
			}
			case endToSwitch:
			{
				System.out.println("Testing end To Switch" + "\t"  + Robot.drive.getRightEncoder());
				if(Math.abs(Robot.drive.getLeftEncoder()) > 500||Math.abs(Robot.drive.getRightEncoder()) > 500) {
					Robot.drive.setAllSpeed(0);
					Robot.drive.resetAllEncoder();
					timer.start();
					RobotMap.autoState = releaseCube;
				}
				if(failTimer.get()>5) {
					RobotMap.autoState = releaseCube;
				}
			break;
			}
			case releaseCube:
			{
				System.out.println("Releasing cube");
				Robot.intake.setSpeedLeft(outTakeSpeed);
				Robot.intake.setSpeedRight(outTakeSpeed);
				RobotMap.autoState = stopRelease;
			break;
			}
			case stopRelease:
			{
				System.out.println("Testing stopRelease");
				if(timer.get()>0.3) {
					Robot.intake.setSpeed(0);
				}
			break;
			}
		}
	}
	
	//----------------------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------------------
	
	public void botRightSwitchRight() {
		switch(RobotMap.autoState) {
			case startMove:
			{	
				System.out.println("started move");
				timer.start();
				Robot.drive.setLeftSpeed(motorSpeed);
				Robot.drive.setRightSpeed(motorSpeed);
				Robot.intake.setSpeedLeft(0.5);
				//Robot.angleIntake.angleIntake(0.8);
				RobotMap.autoState = endMove;
			break;
			}
			case endMove:
			{
				System.out.println("Testing End move" + "\t" + Robot.drive.getLeftEncoder() + "\t" + Robot.drive.getRightEncoder());
				if(Math.abs(Robot.drive.getLeftEncoder())>encoderTicksToSwitch||Math.abs(Robot.drive.getRightEncoder())>encoderTicksToSwitch) {
					Robot.drive.setAllSpeed(0);
					RobotMap.autoState = startTurn;
				}
				if(timer.get()>0.5) {
					RobotMap.inclineMotor.set(0);
					timer.stop();
					timer.reset();
				}
			break;
			}
			case startTurn:
			{
				System.out.println("Started Turn");
				Robot.drive.setLeftSpeed(-motorSpeed);
				Robot.drive.setRightSpeed(motorSpeed);
				RobotMap.autoState = endTurn;
			break;
			}
			case endTurn:
			{
				System.out.println("Testing End Turn" + "\t" + Math.abs(Robot.gyro.getAngle()));
				if(Math.abs(Robot.gyro.getAngle())>ninetyDegreeTurn) {
					Robot.drive.setAllSpeed(0);
					RobotMap.autoState = startToSwitch;
				}
			break;
			}
			case startToSwitch:
			{
				System.out.println("Started to switch");
				Robot.drive.resetAllEncoder();
				Robot.drive.setLeftSpeed(motorSpeed);
				Robot.drive.setRightSpeed(motorSpeed);
				RobotMap.autoState = endToSwitch;
			break;
			}
			case endToSwitch:
			{
				System.out.println("Testing end To Switch" + "\t" + Robot.drive.getLeftEncoder() + "\t" + Robot.drive.getRightEncoder());
				if(Math.abs(Robot.drive.getLeftEncoder()) > 500||Math.abs(Robot.drive.getRightEncoder()) > 500) {
					Robot.drive.setAllSpeed(0);
					RobotMap.autoState = releaseCube;
				}
			break;
			}
			case releaseCube:
			{
				System.out.println("Releasing cube");
				Robot.intake.setSpeedLeft(outTakeSpeed);
				Robot.intake.setSpeedRight(outTakeSpeed);
				timer.start();
				RobotMap.autoState = stopRelease;
			break;
			}
			case stopRelease:
			{
				System.out.println("Testing stopRelease");
				if(timer.get()>0.3) {
					Robot.intake.setSpeed(0);
				}
			break;
			}
		}
	}
	
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	
	
	public void botLeftSwitchRight() {
		switch(RobotMap.autoState) {
			case startMove:
			{	
				System.out.println("started move");
				timer.start();
				Robot.drive.setLeftSpeed(motorSpeed);
				Robot.drive.setRightSpeed(motorSpeed);
				//Robot.angleIntake.angleIntake(0.8);
				if(otherSide) {
					RobotMap.autoState = stopAtSwitch;
				}
				else {
					RobotMap.autoState = endMove;
				}
			break;
			}
			case endMove:
			{
				System.out.println("Testing End move" + "\t" + Robot.drive.getLeftEncoder() + "\t" + Robot.drive.getRightEncoder());
				if(Math.abs(Robot.drive.getLeftEncoder())>encoderTicksToMidField||Math.abs(Robot.drive.getRightEncoder())>encoderTicksToMidField) {
					Robot.drive.setAllSpeed(0);
					RobotMap.autoState = startTurn;
				}
				if(timer.get()>0.5) {
					RobotMap.inclineMotor.set(0);
					timer.stop();
				}
			break;
			}
			case startTurn:
			{
				System.out.println("Started Turn");
				Robot.drive.setLeftSpeed(motorSpeed);
				Robot.drive.setRightSpeed(-motorSpeed);
				Robot.gyro.reset();
				RobotMap.autoState = endTurn;
			break;
			}
			case endTurn:
			{
				System.out.println("Testing End Turn" + "\t" + Math.abs(Robot.gyro.getAngle()));
				if(Math.abs(Robot.gyro.getAngle())>ninetyDegreeTurn) {
					Robot.drive.setAllSpeed(0);
					if(otherSide) {
						Robot.drive.resetAllEncoder();
						Robot.drive.setLeftSpeed(motorSpeed);
						Robot.drive.setRightSpeed(motorSpeed);
						RobotMap.autoState = stopAtSwitch;
					}
					else {
						RobotMap.autoState = startToSwitch;
					}
				}
			break;
			}
			case startToSwitch:
			{
				System.out.println("Started to switch");
				Robot.drive.resetAllEncoder();
				Robot.drive.setLeftSpeed(motorSpeed);
				Robot.drive.setRightSpeed(motorSpeed);
				RobotMap.autoState = endToSwitch;
			break;
			}
			case endToSwitch:
			{
				System.out.println("Testing end To Switch" + "\t" + Robot.drive.getLeftEncoder() + "\t" + Robot.drive.getRightEncoder());
				if(Math.abs(Robot.drive.getLeftEncoder()) > encoderTicksFromSideToSide||Math.abs(Robot.drive.getRightEncoder()) > encoderTicksFromSideToSide) {
					Robot.drive.setAllSpeed(0);
					Robot.drive.resetAllEncoder();
					otherSide = true;
					RobotMap.autoState = startTurn;
				}
			break;
			}
			case stopAtSwitch:
			{
				System.out.println("Going to switch" + "\t" + Robot.drive.getAverageEncoder());
				if(Math.abs(Robot.drive.getLeftEncoder()) > encoderTicksFromMidFieldToSwitch || Math.abs(Robot.drive.getRightEncoder()) > encoderTicksFromMidFieldToSwitch) {
					Robot.drive.stopAllSpeed();
					RobotMap.autoState = releaseCube;
				}
			break;
			}
			case releaseCube:
			{
				System.out.println("Releasing cube");
				Robot.intake.setSpeedLeft(outTakeSpeed);
				Robot.intake.setSpeedRight(outTakeSpeed);
				RobotMap.autoState = stopRelease;
			break;
			}
			case stopRelease:
			{
				System.out.println("Testing stopRelease");
				if(timer.get()>0.3) {
					Robot.intake.setSpeed(0);
					RobotMap.autoState = finishedAuto;
				}
			break;
			}
			case finishedAuto:
			{
				System.out.println("Finished Auto!!");
			}
			
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------
	
	public void botRightSwitchLeft() {
		switch(RobotMap.autoState) {
			case startMove:
			{	
				System.out.println("started move");
				timer.start();
				Robot.drive.setLeftSpeed(motorSpeed);
				Robot.drive.setRightSpeed(motorSpeed);
				//Robot.angleIntake.angleIntake(0.5);
				Robot.intake.setSpeedLeft(motorSpeed);
				if(otherSide) {
					RobotMap.autoState = stopAtSwitch;
				}
				else {
					RobotMap.autoState = endMove;
				}
			break;
			}
			case endMove:
			{
				System.out.println("Testing End move" + "\t" + Robot.drive.getLeftEncoder() + "\t" + Robot.drive.getRightEncoder());
				if(Math.abs(Robot.drive.getLeftEncoder())>encoderTicksToMidField||Math.abs(Robot.drive.getRightEncoder())>encoderTicksToMidField) {
					Robot.drive.setAllSpeed(0);
					RobotMap.autoState = startTurn;
				}
				if(timer.get()>0.5) {
					RobotMap.inclineMotor.set(0);
					timer.stop();
				}
			break;
			}
			case startTurn:
			{
				System.out.println("Started Turn");
				Robot.drive.setLeftSpeed(-motorSpeed);
				Robot.drive.setRightSpeed(motorSpeed);
				Robot.gyro.reset();
				RobotMap.autoState = endTurn;
			break;
			}
			case endTurn:
			{
				System.out.println("Testing End Turn" + "\t" + Math.abs(Robot.gyro.getAngle()));
				if(Math.abs(Robot.gyro.getAngle())>ninetyDegreeTurn) {
					Robot.drive.setAllSpeed(0);
					if(otherSide) {
						Robot.drive.resetAllEncoder();
						Robot.drive.setLeftSpeed(motorSpeed);
						Robot.drive.setRightSpeed(motorSpeed);
						RobotMap.autoState = stopAtSwitch;
					}
					else {
						RobotMap.autoState = startToSwitch;
					}
				}
			break;
			}
			case startToSwitch:
			{
				System.out.println("Started to switch");
				Robot.drive.resetAllEncoder();
				Robot.drive.setLeftSpeed(motorSpeed);
				Robot.drive.setRightSpeed(motorSpeed);
				RobotMap.autoState = endToSwitch;
			break;
			}
			case endToSwitch:
			{
				System.out.println("Testing end To Switch" + "\t" + Robot.drive.getLeftEncoder() + "\t" + Robot.drive.getRightEncoder());
				if(Math.abs(Robot.drive.getLeftEncoder()) > encoderTicksFromSideToSide||Math.abs(Robot.drive.getRightEncoder()) > encoderTicksFromSideToSide) {
					Robot.drive.setAllSpeed(0);
					Robot.drive.resetAllEncoder();
					otherSide = true;
					RobotMap.autoState = startTurn;
				}
			break;
			}
			case stopAtSwitch:
			{
				System.out.println("Going to switch" + "\t" + Robot.drive.getAverageEncoder());
				if(Math.abs(Robot.drive.getLeftEncoder()) > encoderTicksFromMidFieldToSwitch || Math.abs(Robot.drive.getRightEncoder()) > encoderTicksFromMidFieldToSwitch) {
					Robot.drive.stopAllSpeed();
					RobotMap.autoState = releaseCube;
				}
			break;
			}
			case releaseCube:
			{
				System.out.println("Releasing cube");
				Robot.intake.setSpeedLeft(outTakeSpeed);
				Robot.intake.setSpeedRight(outTakeSpeed);
				RobotMap.autoState = stopRelease;
			break;
			}
			case stopRelease:
			{
				System.out.println("Testing stopRelease");
				if(timer.get()>0.3) {
					Robot.intake.setSpeed(0);
					RobotMap.autoState = finishedAuto;
				}
			break;
			}
			case finishedAuto:
			{
				System.out.println("Finished Auto!!");
			}
			
		}
	}
	
	public void crossAutoLineCorrect() {
		switch(RobotMap.autoState) {
		case startMove:
		{
			timer.start();
			drive.setLeftSpeed(0.56);
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
			if(timer.get()>1.7) {
				drive.setAllSpeed(0);
				System.out.println("Finished Cross Auto Line");
				
				break;
			}
			break;
		}
		}
	}
	
}
