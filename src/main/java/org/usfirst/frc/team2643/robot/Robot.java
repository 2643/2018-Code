package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.cscore.UsbCamera;

public class Robot extends IterativeRobot {
	
	Joystick driveStick = new Joystick(0);
	Joystick operatorBoard = new Joystick(1);
	
	public static final WPI_TalonSRX leftDrive1 = new WPI_TalonSRX(14); // Front Left
	public static final WPI_TalonSRX leftDrive2 = new WPI_TalonSRX(15); // Back left
	public static final WPI_TalonSRX rightDrive1 = new WPI_TalonSRX(16); // Front Right
	public static final WPI_TalonSRX rightDrive2 = new WPI_TalonSRX(1); // Back Right
	
	public static final DigitalInput photoSensor = new DigitalInput(0);
	public static final DigitalInput photoSensor1 = new DigitalInput(2);

	@Override
	public void robotInit() {
		new Thread(() -> CameraServer.getInstance().startAutomaticCapture()).start();
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopPeriodic() {
		leftDrive1.set(-driveStick.getRawAxis(1));
		leftDrive2.set(-driveStick.getRawAxis(1));
		rightDrive1.set(driveStick.getRawAxis(5));
		rightDrive2.set(driveStick.getRawAxis(5));

		
	}

	@Override
	public void testPeriodic() {
	}
}
