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
	//The V is to prevent it from conflicting with a class name
	final String CrossAutoLineOnly_v = "CrossAutoLineOnly";
	final String PositionLeft = "PositionLeft";
	final String PositionMiddle = "PositionMiddle";
	final String PositionRight = "PositionRight";
	
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	
	
	
	
	double batteryVoltage = DriverStation.getInstance().getBatteryVoltage();
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault(CrossAutoLineOnly_v, CrossAutoLineOnly_v);
		
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
		
		while(!RobotMap.slideLimit.get()) {
			RobotMap.s1.set(-0.8);
		}
		
		RobotMap.s1.set(0);
		
		RobotMap.slideEncoder.reset();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();
		
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L' && !autoSelected.equals(CrossAutoLineOnly_v))
		{
			autoSelected = "SwitchLeftAnd"  + autoSelected;
		} 
		else if(gameData.charAt(0) == 'R' && !autoSelected.equals(CrossAutoLineOnly_v))
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
			RobotMovementMethods.releaseArm();
		}
		else
		{
			switch(autoSelected) {
				case "CrossAutoLineOnly": 
					CrossAutoLineOnly.runPeriodic();
					break;
				case "SwitchLeftAndPositionLeft":
					SwitchLeftAndPositionLeft.runPeriodic();
					break;
				case "SwitchLeftAndPositionMiddle":
					SwitchLeftAndPositionMiddle.runPeriodic();
					break;
				case "SwitchLeftAndPositionRight":
					SwitchLeftAndPositionRight.runPeriodic();
					break;
				case "SwitchRightAndPositionLeft":
					SwitchRightAndPositionLeft.runPeriodic();
					break;
				case "SwitchRightAndPositionMiddle": 
					SwitchRightAndPositionMiddle.runPeriodic();
					break;
				case "SwitchRightAndPositionRight": 
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
		if(RobotMap.stick.getRawButton(2)) { driveState = 1; }
		else if(RobotMap.stick.getRawButton(1)) { driveState = 0; }
		//Changes drive state. 
		if(driveState == 0) { //0 is Tank Drive
			RobotMovementMethods.SRXtankDrive(RobotMap.stick.getRawAxis(1), RobotMap.stick.getRawAxis(5));
		}
		else if(driveState == 1) { //1 is arcade
			RobotMovementMethods.SRXarcadeDrive(RobotMap.stick.getRawAxis(0),RobotMap.stick.getRawAxis(1));
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	
	

	
}
