package org.usfirst.frc.team2643.robot;

//TalonSRX class import
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	final String OnlyCrossAutoLine = "CrossAutoLineOnly";
	final String PositionLeft = "PositionLeft";
	final String PositionMiddle = "PositionMiddle";
	final String PositionRight = "PositionRight";
	
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	
	//Six Drive Motors
	static WPI_TalonSRX t1 = new WPI_TalonSRX(1);
	static WPI_TalonSRX t2 = new WPI_TalonSRX(2);
	static WPI_TalonSRX t3 = new WPI_TalonSRX(3);
	static WPI_TalonSRX t4 = new WPI_TalonSRX(4);
	static WPI_TalonSRX t5 = new WPI_TalonSRX(5);
	static WPI_TalonSRX t6 = new WPI_TalonSRX(6);
	
	//Motor to move elevator up
	//weight of the elevator lets it drop
	static WPI_TalonSRX s1 = new WPI_TalonSRX(7);
	
	static DigitalInput slideLimit = new DigitalInput(1);
	
	static Joystick stick = new Joystick(0);
	
	int driveState = 0;
	
	static Encoder leftEncoder = new Encoder(0,1);
	static Encoder rightEncoder = new Encoder(1,0);
	static Encoder slideEncoder = new Encoder(1,1);
	
	
	double batteryVoltage = DriverStation.getInstance().getBatteryVoltage();
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault(OnlyCrossAutoLine, OnlyCrossAutoLine);
		
		chooser.addObject(PositionLeft, PositionLeft);
		chooser.addObject(PositionMiddle, PositionMiddle);
		chooser.addObject(PositionRight, PositionRight);
		
		SmartDashboard.putData("Auto choices", chooser);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		
		while(!slideLimit.get()) {
			s1.set(-.8);
		}
		
		s1.set(0);
		
		slideEncoder.reset();
		leftEncoder.reset();
		rightEncoder.reset();
		
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L' && !autoSelected.equals(OnlyCrossAutoLine))
		{
			autoSelected = "SwitchLeftAnd"  + autoSelected;
		} 
		else if(gameData.charAt(0) == 'R' && !autoSelected.equals(OnlyCrossAutoLine))
		{
			autoSelected = "SwitchRightAnd" + autoSelected;
		}
		
		System.out.println("Auto selected: " + autoSelected);
		RobotMap.armsReleased = false;	
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		if(RobotMap.armsReleased == false)
		{
			//TODO move forward and then move backwards to release arms
			RobotMap.armsReleased = true;
		}
		else
		{
			switch(RobotMap.state) {
				case 0: 
					CrossAutoLineOnly.runPeriodic();
					break;
				case 1:
					SwitchLeftAndPositionLeft.runPeriodic();
					break;
				case 2:
					SwitchLeftAndPositionMiddle.runPeriodic();
					break;
				case 3:
					SwitchLeftAndPositionRight.runPeriodic();
					break;
				case 4:
					SwitchRightAndPositionLeft.runPeriodic();
					break;
				case 5: 
					SwitchRightAndPositionMiddle.runPeriodic();
					break;
				case 6: 
					SwitchRightAndPositionRight.runPeriodic();
					break;
			}
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		if(stick.getRawButton(2)) { driveState = 1; }
		else if(stick.getRawButton(1)) { driveState = 0; }
		//Changes drive state. 
		if(driveState == 0) { //0 is Tank Drive
			SRXtankDrive(stick.getRawAxis(1), stick.getRawAxis(5));
		}
		else if(driveState == 1) { //1 is arcade
			SRXarcadeDrive(stick.getRawAxis(0),stick.getRawAxis(1));
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	
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
			setAll(0);
		}
	}
	
	public static void SRXtankDrive(double x, double y) { //Very basic tank drive.
		setLeftMotors(-x);
		setRightMotors(y);
	}
	public static void setLeftMotors(double x) { //Set all of the motors on the left side to the given value.
		t1.set(x);
		t2.set(x);
		t3.set(x);
	}
	public static void setRightMotors(double x) { //Set all of the motors on the right side to the given value.
		t4.set(x);
		t5.set(x);
		t6.set(x);
	}
	public static void setAll(double x) { //Set all of the motors to the given value. 
		t1.set(x);
		t2.set(x);
		t3.set(x);
		t4.set(x);
		t5.set(x);
		t6.set(x);
	}
	
}
