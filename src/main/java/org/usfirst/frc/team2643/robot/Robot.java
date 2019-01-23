/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must
 */
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String 

	
	Joystick driveStick = new Joystick(0);
	Joystick opStick = new Joystick(1);
	
	public static final WPI_TalonSRX leftDrive1 = new WPI_TalonSRX(14); // Front Left
	public static final WPI_TalonSRX leftDrive2 = new WPI_TalonSRX(15); // Back left
	public static final WPI_TalonSRX rightDrive1 = new WPI_TalonSRX(1); // Front Right
	public static final WPI_TalonSRX rightDrive2 = new WPI_TalonSRX(16); // Back Right
	
	public static final WPI_TalonSRX elevator1 = new WPI_TalonSRX(5);
	public static final WPI_TalonSRX elevator2 = new WPI_TalonSRX(30);
	
	public static final WPI_TalonSRX leftIntake = new WPI_TalonSRX(9);
	public static final WPI_TalonSRX rightIntake = new WPI_TalonSRX(6);
	
	public static final WPI_TalonSRX angleMotor = new WPI_TalonSRX(4);
	
	public static final DigitalInput elevatorSwitch = new DigitalInput(9);

	public static final DigitalInput photoSensor = new DigitalInput(0);
	public static final DigitalInput photoSensor1 = new DigitalInput(2);
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		new Thread(() -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(320, 240);
			camera.setFPS(20);
		}).start();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		System.out.println(photoSensor.get() + " " + photoSensor1.get());
		/*
		leftDrive1.set(-driveStick.getRawAxis(1));
		leftDrive1.set(-driveStick.getRawAxis(1));
		rightDrive1.set(driveStick.getRawAxis(5));
		rightDrive2.set(driveStick.getRawAxis(5));
		*/
		/*
		if(opStick.getRawButton(1) == true) {
			leftIntake.set(-0.6);
			rightIntake.set(0.6);
		}else if(opStick.getRawButton(2) == true) {
			leftIntake.set(0.6);
			rightIntake.set(-0.6);
		}else {
			leftIntake.set(0);
			rightIntake.set(0);
		}*/
		
		/*
		elevator1.set(opStick.getRawAxis(1)/2);
		elevator2.set(opStick.getRawAxis(1)/2);
		
		leftIntake.set(opStick.getRawAxis(5)/2);
		rightIntake.set(opStick.getRawAxis(5)/2);
		
		if(Math.abs(opStick.getRawAxis(5)) < 0.0)
		{
			elevator1.set(0.2);
			elevator2.set(0.2);
		}
		*/
		/*
		if(opStick.getPOV() == 0) {
			System.out.println("UP");
			elevator1.set(0.6);
			elevator2.set(0.6);
		} else if(opStick.getPOV() == 180) {
			System.out.println("DOWN");
			elevator1.set(-0.4);
			elevator2.set(-0.4);
		} else if(opStick.getRawButton(4) == true){
			elevator1.set(0.2);
			elevator2.set(0.2);
		} else {
			elevator1.set(0);
			elevator2.set(0);
		}		 */
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
