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
			dropIntake = 10,
			stopDrop = 14,
			raiseElevator = 11,
			stopElevator = 12,
			endCase = 13,
			moveBack = 15,
			stopBack = 16;
			
	private final int //Auto Constants under some random encoder ticks (457)
			encoderTicksToSwitch = 3208, //168 Inches (4073)
			ninetyDegreeTurn = 185, 
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
			outTakeSpeed = -0.8,
			leftMotorSpeed = 0.56,
			rightMotorSpeed = 0.5,
			leftTurningSpeed = 0.45,
			rightTurningSpeed = 0.45,
			dropIntakeSpeed = 0.25,
			holdElevatorSpeed = 0.15,
			raiseElevatorSpeed = 0.5;
	
	private boolean otherSide = false;
	
	/*
	 * Notes:
	 * 
	 */
	
	
	public Timer timer = new Timer();
	private Timer failTimer = new Timer();
	
	public void crossAutoLine() {
		switch(RobotMap.autoState) {
		case startMove:
		{
			timer.start();
			drive.setLeftSpeed(leftMotorSpeed);
			drive.setRightSpeed(rightMotorSpeed);
			System.out.println("Finished Start");
			RobotMap.autoState = endMove;
		break;
		}
		case endMove:
		{
			if(Timer.getMatchTime()>15) {
				break;
			}
			if(timer.get()>1.2) {
				drive.setAllSpeed(0);
				System.out.println("Finished Cross Auto Line");
				break;
			}
			break;
		}
		}
	}
	
	public void doSwitch(boolean isRight) {
		switch(RobotMap.autoState) {
		case startMove:
		{
			System.out.println("startedMove");
			timer.start();
			drive.setLeftSpeed(leftMotorSpeed);
			drive.setRightSpeed(rightMotorSpeed);
			intake.setSpeedLeft(dropIntakeSpeed);
			System.out.println("Finished Start");
			RobotMap.autoState = endMove;
		break;
		}
		case endMove:
		{
			System.out.println("Testing End move");
			if(timer.get()>1.5) {
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
			}
			else {
				System.out.println("Started Turn");
				Robot.drive.setLeftSpeed(leftTurningSpeed);
				Robot.drive.setRightSpeed(-rightTurningSpeed);
				RobotMap.autoState = endTurn;
			}
			
		break;
		}
		case endTurn:
		{
			System.out.println("Testing End Turn" + "\t" + Math.abs(Robot.gyro.getAngle()));
			if(Math.abs(Robot.gyro.getAngle())>ninetyDegreeTurn) {
				Robot.drive.setAllSpeed(0);
				RobotMap.autoState = raiseElevator;
			break;
			}
		break;
		}
		case raiseElevator:
		{
			RobotMap.elevator1.set(raiseElevatorSpeed);
			timer.start();
			RobotMap.autoState = stopElevator;
		break;
		}
		case stopElevator:
		{
			if(timer.get() > 3 || elevator.getEncoder() > 5000) {
				RobotMap.elevator1.set(holdElevatorSpeed);
				RobotMap.autoState = startToSwitch;
			break;
			}
		break;
		}
		case startToSwitch:
		{
			System.out.println("Started to switch");
			drive.setLeftSpeed(leftMotorSpeed * 0.5);
			drive.setRightSpeed(rightMotorSpeed * 0.5);
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
				RobotMap.elevator1.set(0);
				//RobotMap.autoState = moveBack;
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
			if(timer.get()>1) {
				drive.setAllSpeed(0);
				timer.stop();
				System.out.println("finished auto!!");
			break;
			}
			if(Timer.getMatchTime()>15) {
				break;
			}
		break;
		}
	}
	}
	
	public void doScale(boolean isRight) {
		
		switch(RobotMap.autoState) {
		
		case startMove:
		{
			System.out.println("Starting Move");
			timer.start();
			drive.setLeftSpeed(0.51);
			drive.setRightSpeed(0.46);
			RobotMap.autoState = endMove;
		break;
		}
		case endMove:
		{
			System.out.println("In endMove");
			if(timer.get()>3.8) {
				drive.setAllSpeed(0);
				timer.stop();
				RobotMap.autoState = startTurn;
				break;
			}
		break;
		}
		case startTurn:
		{
			if(isRight) {
				System.out.println("Starting Turn");
				drive.setLeftSpeed(0.45);	
				drive.setRightSpeed(-0.45);
				gyro.reset();
				RobotMap.autoState = endTurn;
			break;
			}
			else {
				System.out.println("Starting Turn");
				drive.setLeftSpeed(-0.45);	
				drive.setRightSpeed(0.45);
				gyro.reset();
				RobotMap.autoState = endTurn;
			break;
			}
		}
		case endTurn:
		{
			System.out.println("In endTurn");
			if(Math.abs(gyro.getAngle())>ninetyDegreeTurn) {
				drive.setAllSpeed(0);
				RobotMap.autoState = raiseElevator;
			break;
			}
		break;
		}
		case raiseElevator:
		{
			System.out.println("Started raiseElevator");
			RobotMap.elevator1.set(0.7);
			timer.start();
			RobotMap.autoState = stopElevator;
		break;
		}
		case stopElevator:
		{
			System.out.println("In stopElevator");
			if(timer.get()> 5 || elevator.getEncoder()>10000) {
				RobotMap.elevator1.set(0.12);
				RobotMap.autoState = dropIntake;
			break;
			}
		break;
		}
		case dropIntake:
		{
			System.out.println("Start dropIntake");
			intake.setSpeedLeft(-0.24);
			timer.start();
			RobotMap.autoState = stopDrop;
		break;
		}
		case stopDrop:
		{	
			System.out.println("In stopDrop");
			if(timer.get()>0.35) {
				intake.setSpeedLeft(0);
				System.out.println("Stopped drop");
				timer.reset();
				RobotMap.autoState = releaseCube;
			break;			
			}
		break;
		}
		case releaseCube:
		{
			intake.setSpeed(-0.7);
			timer.reset();
			System.out.println("Starting Release Cube");
			RobotMap.autoState = stopRelease;
		break;
		}
		case stopRelease:
		{
			System.out.println("In stopRelease");
			if(timer.get()>1) {
				intake.setSpeed(0);
				RobotMap.elevator1.set(0);
				RobotMap.autoState = moveBack;
			break;
			}
		break;
		}
		case moveBack:
		{
			drive.setLeftSpeed(-0.12);
			drive.setRightSpeed(-0.12);
			timer.start();
			RobotMap.autoState = stopBack;
		break;
		}
		case stopBack:
		{
			if(timer.get()>0.5) {
				drive.setAllSpeed(0);
				RobotMap.autoState = endCase;
			break;
			}
		break;
		}
		case endCase:
		{
			System.out.println("Finished Auto!!");
			timer.stop();
			if(Timer.getMatchTime()>15) {
				break;
			}
			break;
		}
		}
	}
	
	public void middleCube() {
		switch(RobotMap.autoState) {
		case startMove:
		{
			timer.start();
			drive.setLeftSpeed(leftMotorSpeed);
			drive.setRightSpeed(rightMotorSpeed);
			RobotMap.elevator1.set(0.15);
			intake.setSpeedLeft(0.25);
			System.out.println("Finished Start");
			RobotMap.autoState = endMove;
		break;
		}
		case endMove:
		{
			if(Timer.getMatchTime()>15) {
				break;
			}
			if(timer.get()>0.8) {
				drive.setAllSpeed(0);
				intake.setSpeed(0);
				RobotMap.elevator1.set(0.13);
				System.out.println("Finished Cross Auto Line");
				RobotMap.autoState = releaseCube;
				break;
			}
			break;
		}
		case releaseCube:
		{
			intake.setSpeed(-0.6);
			System.out.println("Started ReleaseCube");
			timer.start();
			RobotMap.autoState = stopRelease;
			break;
		}
		case stopRelease:
		{	
			if(timer.get()>0.5) {
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
	}
	
	
	/*public void testCrossAuto() {
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
	}*/

	/*public void botLeftSwitchLeft() {
		switch(RobotMap.autoState) {
			case startMove:
			{	
				System.out.println("started move");
				timer.start();
				failTimer.start();
				drive.setLeftSpeed(0.68);
				drive.setRightSpeed(0.5);
				//Robot.angleIntake.angleIntake(motorSpeed);
				Robot.intake.setSpeedLeft(motorSpeed);
				RobotMap.autoState = endMove;
			break;
			}
			case endMove:
			{
				System.out.println("Testing End move" + "\t" + Robot.drive.getRightEncoder());
				if(timer.get()>1.6) {
					Robot.drive.setAllSpeed(0);
					RobotMap.autoState = startTurn;
				break;
				}
			break;
			}
			case startTurn:
			{
				System.out.println("Started Turn");
				Robot.drive.setLeftSpeed(0.45);
				Robot.drive.setRightSpeed(-0.45);
				failTimer.start();
				RobotMap.autoState = endTurn;
			break;
			}
			case endTurn:
			{
				System.out.println("Testing End Turn" + "\t" + Math.abs(Robot.gyro.getAngle()));
				if(Math.abs(Robot.gyro.getAngle())>ninetyDegreeTurn) {
					Robot.drive.setAllSpeed(0);
					RobotMap.autoState = raiseElevator;
				break;
				}
//				if(failTimer.get()>5) {
//					failTimer.reset();
//					RobotMap.autoState = releaseCube;
//				break;
//				}
			break;
			}
			case raiseElevator:
			{
				RobotMap.elevator1.set(0.5);
				timer.start();
				RobotMap.autoState = stopElevator;
			break;
			}
			case stopElevator:
			{
				if(timer.get()>3 || elevator.getEncoder() > 5000) {
					RobotMap.elevator1.set(0.15);
					RobotMap.autoState = startToSwitch;
				break;
				}
			break;
			}
			case startToSwitch:
			{
				System.out.println("Started to switch");
				Robot.drive.setLeftSpeed(0.38);
				Robot.drive.setRightSpeed(0.33);
				timer.start();
				RobotMap.autoState = endToSwitch;
			break;
			}
			case endToSwitch:
			{
				System.out.println("Testing end To Switch");
				if(timer.get() > 0.8) {
					Robot.drive.setAllSpeed(0);
					timer.start();
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
				if(timer.get()>1) {
					Robot.intake.setSpeed(0);
				}
			break;
			}
		}
	}
	*/
	//----------------------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------------------
	
//	public void botRightSwitchRight() {
//		switch(RobotMap.autoState) {
//		case startMove:
//		{
//			System.out.println("startedMove");
//			timer.start();
//			drive.setLeftSpeed(0.552);
//			drive.setRightSpeed(0.515);
//			intake.setSpeedLeft(0.25);
//			System.out.println("Finished Start");
//			RobotMap.autoState = endMove;
//		break;
//		}
//		case endMove:
//		{
//			System.out.println("Testing End move");
//			if(timer.get()>1.5) {
//				Robot.drive.setAllSpeed(0);
//				RobotMap.autoState = startTurn;
//			break;
//			}
//		break;
//		}
//		case startTurn:
//		{
//			System.out.println("Started Turn");
//			Robot.drive.setLeftSpeed(-0.45);
//			Robot.drive.setRightSpeed(0.45);
//			RobotMap.autoState = endTurn;
//		break;
//		}
//		case endTurn:
//		{
//			System.out.println("Testing End Turn" + "\t" + Math.abs(Robot.gyro.getAngle()));
//			if(Math.abs(Robot.gyro.getAngle())>ninetyDegreeTurn) {
//				Robot.drive.setAllSpeed(0);
//				RobotMap.autoState = raiseElevator;
//			break;
//			}
//		break;
//		}
//		case raiseElevator:
//		{
//			RobotMap.elevator1.set(0.5);
//			timer.start();
//			RobotMap.autoState = stopElevator;
//		break;
//		}
//		case stopElevator:
//		{
//			if(timer.get() > 3 || elevator.getEncoder() > 5000) {
//				RobotMap.elevator1.set(0.15);
//				RobotMap.autoState = startToSwitch;
//			break;
//			}
//		break;
//		}
//		case startToSwitch:
//		{
//			System.out.println("Started to switch");
//			Robot.drive.setLeftSpeed(0.28);
//			Robot.drive.setRightSpeed(0.23);
//			timer.start();
//			RobotMap.autoState = endToSwitch;
//		break;
//		}
//		case endToSwitch:
//		{
//			System.out.println("Testing end To Switch");
//			if(timer.get() > 0.8) {
//				Robot.drive.setAllSpeed(0);
//				timer.reset();
//				RobotMap.autoState = releaseCube;
//			break;
//			}
//		break;
//		}
//		case releaseCube:
//		{
//			System.out.println("Releasing cube");
//			Robot.intake.setSpeedLeft(-0.6);
//			Robot.intake.setSpeedRight(-0.6);
//			RobotMap.autoState = stopRelease;
//		break;
//		}
//		case stopRelease:
//		{
//			System.out.println("Testing stopRelease");
//			if(timer.get()>1) {
//				Robot.intake.setSpeed(0);
//				RobotMap.elevator1.set(0);
//				//RobotMap.autoState = moveBack;
//			break;
//			}
//		break;
//		}
//		case moveBack:
//		{
//			drive.setLeftSpeed(-0.35);
//			drive.setRightSpeed(-0.35);
//			timer.start();
//			RobotMap.autoState = stopBack;
//		break;
//		}
//		case stopBack:
//		{
//			if(timer.get()>1.5) {
//				drive.setAllSpeed(0);
//				timer.stop();
//				System.out.println("finished auto!!");
//			break;
//			}
//		break;
//		}
//	}
//	}
	
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	
	
/*	public void botLeftSwitchRight() {
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
//				if(timer.get()>0.5) {
//					RobotMap.inclineMotor.set(0);
//					timer.stop();
//				}
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
	}*/
	
	//------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------
	
	/*public void botRightSwitchLeft() {
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
	
	public void correctCubeSwitch() {
		switch(RobotMap.autoState) {
		case startMove:
		{
			timer.start();
			drive.setLeftSpeed(0.56);
			drive.setRightSpeed(0.5);
			RobotMap.elevator1.set(0.15);
			System.out.println("Finished Start");
			RobotMap.autoState = endMove;
		break;
		}
		case endMove:
		{
			if(Timer.getMatchTime()>15) {
				break;
			}
			if(timer.get()>1.6) {
				drive.setAllSpeed(0);
				RobotMap.elevator1.set(0);
				intake.setSpeedLeft(0.3);
				failTimer.start();
				System.out.println("Finished Cross Auto Line");
				RobotMap.autoState = releaseCube;
				break;
			}
			break;
		}
		case releaseCube:
		{
			if(failTimer.get()>1) {
				intake.setSpeedLeft(-0.6);
				intake.setSpeedRight(0.6);
				failTimer.reset();
				RobotMap.autoState = stopRelease;
				break;
			}
			break;
		}
		case stopRelease:
		{	
			if(failTimer.get()>0.5) {
				intake.setSpeed(0);
				timer.stop();
				failTimer.stop();
				System.out.println("Dropped Cube");
				break;
			}
		break;
		}
		}
	}
	
	public void botRightScaleRight() {
		switch(RobotMap.autoState) {
		case startMove:
		{
			System.out.println("Starting Move");
			timer.start();
			drive.setLeftSpeed(0.51);
			drive.setRightSpeed(0.46);
			RobotMap.autoState = endMove;
		break;
		}
		case endMove:
		{
			System.out.println("In endMove");
			if(timer.get()>3.8) {
				drive.setAllSpeed(0);
				timer.stop();
				RobotMap.autoState = startTurn;
				break;
			}
		break;
		}
		case startTurn:
		{
			System.out.println("Starting Turn");
			drive.setLeftSpeed(-0.45);
			drive.setRightSpeed(0.45);
			gyro.reset();
			RobotMap.autoState = endTurn;
		break;
		}
		case endTurn:
		{
			System.out.println("In endTurn");
			if(Math.abs(gyro.getAngle())>ninetyDegreeTurn/2.0) {
				drive.setAllSpeed(0);
				RobotMap.autoState = raiseElevator;
			break;
			}
		break;
		}
		case raiseElevator:
		{
			System.out.println("Started raiseElevator");
			RobotMap.elevator1.set(0.7);
			timer.start();
			RobotMap.autoState = stopElevator;
		break;
		}
		case stopElevator:
		{
			System.out.println("In stopElevator");
			if(timer.get()> 5.2 || elevator.getEncoder()>10000) {
				RobotMap.elevator1.set(0.1);
				RobotMap.autoState = dropIntake;
			break;
			}
		break;
		}
		case dropIntake:
		{
			System.out.println("Start dropIntake");
			intake.setSpeedLeft(0.24);
			timer.start();
			RobotMap.autoState = stopDrop;
		break;
		}
		case stopDrop:
		{	
			System.out.println("In stopDrop");
			if(timer.get()>0.35) {
				intake.setSpeedLeft(0);
				System.out.println("Stopped drop");
				timer.reset();
				RobotMap.autoState = releaseCube;
			break;			
			}
		break;
		}
		case releaseCube:
		{
			intake.setSpeedLeft(-0.55);
			intake.setSpeedRight(-0.55);
			timer.reset();
			System.out.println("Starting Release Cube");
			RobotMap.autoState = stopRelease;
		break;
		}
		case stopRelease:
		{
			System.out.println("In stopRelease");
			if(timer.get()>1) {
				intake.setSpeed(0);
				RobotMap.elevator1.set(0);
				RobotMap.autoState = moveBack;
			break;
			}
		break;
		}
		case moveBack:
		{
			drive.setLeftSpeed(-0.1);
			drive.setRightSpeed(-0.1);
			timer.start();
			RobotMap.autoState = stopBack;
		break;
		}
		case stopBack:
		{
			if(timer.get()>0.3) {
				drive.setAllSpeed(0);
				RobotMap.autoState = endCase;
			break;
			}
		break;
		}
		case endCase:
		{
			System.out.println("Finished Auto!!");
			timer.stop();
			break;
		}
		}
	}
	
	public void botLeftScaleLeft() {
		switch(RobotMap.autoState) {
		case startMove:
		{
			System.out.println("Starting Move");
			timer.start();
			drive.setLeftSpeed(0.51);
			drive.setRightSpeed(0.46);
			RobotMap.autoState = endMove;
		break;
		}
		case endMove:
		{
			System.out.println("In endMove");
			if(timer.get()>3.8) {
				drive.setAllSpeed(0);
				timer.stop();
				RobotMap.autoState = startTurn;
				break;
			}
		break;
		}
		case startTurn:
		{
			System.out.println("Starting Turn");
			drive.setLeftSpeed(-0.45);				//TODO Flip Polarity!!
			drive.setRightSpeed(0.45);
			gyro.reset();
			RobotMap.autoState = endTurn;
		break;
		}
		case endTurn:
		{
			System.out.println("In endTurn");
			if(Math.abs(gyro.getAngle())>ninetyDegreeTurn) {
				drive.setAllSpeed(0);
				RobotMap.autoState = raiseElevator;
			break;
			}
		break;
		}
		case raiseElevator:
		{
			System.out.println("Started raiseElevator");
			RobotMap.elevator1.set(0.7);
			timer.start();
			RobotMap.autoState = stopElevator;
		break;
		}
		case stopElevator:
		{
			System.out.println("In stopElevator");
			if(timer.get()> 5.2 || elevator.getEncoder()>10000) {
				RobotMap.elevator1.set(0.1);
				RobotMap.autoState = dropIntake;
			break;
			}
		break;
		}
		case dropIntake:
		{
			System.out.println("Start dropIntake");
			intake.setSpeedLeft(-0.24);
			timer.start();
			RobotMap.autoState = stopDrop;
		break;
		}
		case stopDrop:
		{	
			System.out.println("In stopDrop");
			if(timer.get()>0.35) {
				intake.setSpeedLeft(0);
				System.out.println("Stopped drop");
				timer.reset();
				RobotMap.autoState = releaseCube;
			break;			
			}
		break;
		}
		case releaseCube:
		{
			intake.setSpeedLeft(0.55);
			intake.setSpeedRight(-0.55);
			timer.reset();
			System.out.println("Starting Release Cube");
			RobotMap.autoState = stopRelease;
		break;
		}
		case stopRelease:
		{
			System.out.println("In stopRelease");
			if(timer.get()>1) {
				intake.setSpeed(0);
				RobotMap.elevator1.set(0);
				RobotMap.autoState = moveBack;
			break;
			}
		break;
		}
		case moveBack:
		{
			drive.setLeftSpeed(-0.15);
			drive.setRightSpeed(-0.15);
			timer.start();
			RobotMap.autoState = stopBack;
		break;
		}
		case stopBack:
		{
			if(timer.get()>0.3) {
				drive.setAllSpeed(0);
				RobotMap.autoState = endCase;
			break;
			}
		break;
		}
		case endCase:
		{
			System.out.println("Finished Auto!!");
			timer.stop();
			break;
		}
		}
	}
	*/
}
