package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot {

	Joystick opStick = new Joystick(0);
	
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

	@Override
	public void robotInit() {
		new Thread(() -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		}).start();
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
		leftDrive1.set(-driveStick.getRawAxis(1));
		rightDrive1.set(driveStick.getRawAxis(5));
		rightDrive2.set(driveStick.getRawAxis(5));
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
		}		 
		*/
	}

	@Override
	public void testPeriodic() {
	}
}
