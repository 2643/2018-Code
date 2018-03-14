package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoRoutines {
	private final int
			startMove = 0,
			endMove = 1,
			startTurn = 2,
			endTurn = 3, 
			startToSwitch = 4,
			endToSwitch = 5,
			releaseCube = 6,
			stopRelease = 7,
			moveOut = 8,
			stopMoveOut = 9;
	
	/*
	 * Notes:
	 * Ask about angle gearbox gearing
	 */
	
	private Timer timer = new Timer();

	public void leftSwitchLeft() {
		switch(RobotMap.autoState) {
			case startMove:
			{
				timer.start();
				Robot.drive.setLeftSpeed(0.6);
				Robot.drive.setRightSpeed(-0.6);
				Robot.angleIntake.angleIntake(0.8);
			}
		}
	}
}
