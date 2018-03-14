package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoRoutines {
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
			finishedAuto = 9;
			
	private final int //Auto Constants
			encoderTicksToSwitch = 3208,
			gyroTicksToSwitch = 38,
			encoderTicksToMidField = 4354,
			encoderTicksFromSideToSide = 3915,
			encoderTicksFromMidFieldToSwitch = 401;
	
	private boolean otherSide = false;
	
	/*
	 * Notes:
	 * 
	 */
	
	
	private Timer timer = new Timer();

	public void botLeftSwitchLeft() {
		switch(RobotMap.autoState) {
			case startMove:
			{	
				System.out.println("started move");
				timer.start();
				Robot.drive.setLeftSpeed(0.6);
				Robot.drive.setRightSpeed(-0.6);
				Robot.angleIntake.angleIntake(0.8);
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
				}
			break;
			}
			case startTurn:
			{
				System.out.println("Started Turn");
				Robot.drive.setLeftSpeed(0.6);
				Robot.drive.setRightSpeed(0.6);
				RobotMap.autoState = endTurn;
			break;
			}
			case endTurn:
			{
				System.out.println("Testing End Turn" + "\t" + Math.abs(Robot.gyro.getAngle()));
				if(Math.abs(Robot.gyro.getAngle())>gyroTicksToSwitch) {
					Robot.drive.setAllSpeed(0);
					RobotMap.autoState = startToSwitch;
				}
			break;
			}
			case startToSwitch:
			{
				System.out.println("Started to switch");
				Robot.drive.resetAllEncoder();
				Robot.drive.setLeftSpeed(0.6);
				Robot.drive.setRightSpeed(-0.6);
				RobotMap.autoState = endToSwitch;
			break;
			}
			case endToSwitch:
			{
				System.out.println("Testing end To Switch" + "\t" + Robot.drive.getLeftEncoder() + "\t" + Robot.drive.getRightEncoder());
				if(Math.abs(Robot.drive.getLeftEncoder()) > 250||Math.abs(Robot.drive.getRightEncoder()) > 250) {
					Robot.drive.setAllSpeed(0);
					Robot.drive.resetAllEncoder();
					timer.start();
					RobotMap.autoState = releaseCube;
				}
			break;
			}
			case releaseCube:
			{
				System.out.println("Releasing cube");
				Robot.intake.setSpeedLeft(-1);
				Robot.intake.setSpeedRight(1);
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
				Robot.drive.setLeftSpeed(0.6);
				Robot.drive.setRightSpeed(-0.6);
				Robot.angleIntake.angleIntake(0.8);
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
				Robot.drive.setLeftSpeed(-0.6);
				Robot.drive.setRightSpeed(-0.6);
				RobotMap.autoState = endTurn;
			break;
			}
			case endTurn:
			{
				System.out.println("Testing End Turn" + "\t" + Math.abs(Robot.gyro.getAngle()));
				if(Math.abs(Robot.gyro.getAngle())>38) {
					Robot.drive.setAllSpeed(0);
					RobotMap.autoState = startToSwitch;
				}
			break;
			}
			case startToSwitch:
			{
				System.out.println("Started to switch");
				Robot.drive.resetAllEncoder();
				Robot.drive.setLeftSpeed(0.6);
				Robot.drive.setRightSpeed(-0.6);
				RobotMap.autoState = endToSwitch;
			break;
			}
			case endToSwitch:
			{
				System.out.println("Testing end To Switch" + "\t" + Robot.drive.getLeftEncoder() + "\t" + Robot.drive.getRightEncoder());
				if(Math.abs(Robot.drive.getLeftEncoder()) > 250||Math.abs(Robot.drive.getRightEncoder()) > 250) {
					Robot.drive.setAllSpeed(0);
					timer.start();
					RobotMap.autoState = releaseCube;
				}
			break;
			}
			case releaseCube:
			{
				System.out.println("Releasing cube");
				Robot.intake.setSpeedLeft(-1);
				Robot.intake.setSpeedRight(1);
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
				Robot.drive.setLeftSpeed(0.6);
				Robot.drive.setRightSpeed(-0.6);
				Robot.angleIntake.angleIntake(0.8);
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
				Robot.drive.setLeftSpeed(0.6);
				Robot.drive.setRightSpeed(0.6);
				Robot.gyro.reset();
				RobotMap.autoState = endTurn;
			break;
			}
			case endTurn:
			{
				System.out.println("Testing End Turn" + "\t" + Math.abs(Robot.gyro.getAngle()));
				if(Math.abs(Robot.gyro.getAngle())>gyroTicksToSwitch) {
					Robot.drive.setAllSpeed(0);
					if(otherSide) {
						Robot.drive.resetAllEncoder();
						Robot.drive.setLeftSpeed(0.5);
						Robot.drive.setRightSpeed(-0.5);
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
				Robot.drive.setLeftSpeed(0.6);
				Robot.drive.setRightSpeed(-0.6);
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
				Robot.intake.setSpeedLeft(-1);
				Robot.intake.setSpeedRight(1);
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
				Robot.drive.setLeftSpeed(0.6);
				Robot.drive.setRightSpeed(-0.6);
				Robot.angleIntake.angleIntake(0.8);
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
				Robot.drive.setLeftSpeed(-0.6);
				Robot.drive.setRightSpeed(-0.6);
				Robot.gyro.reset();
				RobotMap.autoState = endTurn;
			break;
			}
			case endTurn:
			{
				System.out.println("Testing End Turn" + "\t" + Math.abs(Robot.gyro.getAngle()));
				if(Math.abs(Robot.gyro.getAngle())>gyroTicksToSwitch) {
					Robot.drive.setAllSpeed(0);
					if(otherSide) {
						Robot.drive.resetAllEncoder();
						Robot.drive.setLeftSpeed(0.5);
						Robot.drive.setRightSpeed(-0.5);
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
				Robot.drive.setLeftSpeed(0.6);
				Robot.drive.setRightSpeed(-0.6);
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
				Robot.intake.setSpeedLeft(-1);
				Robot.intake.setSpeedRight(1);
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
	
}
