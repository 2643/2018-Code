package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	// The V is to prevent it from conflicting with a class name
	final String crossAutoLineOnlyOption = "CrossAutoLineOnly";
	final String positionLeftOption = "PositionLeft";
	final String positionMiddleOption = "PositionMiddle";
	final String positionRightOption = "PositionRight";

	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

	int driveState = 0;

	// double batteryVoltage = DriverStation.getInstance().getBatteryVoltage();

	public static AutoDrive drive;
	public static AutoElevator elevator;
	public static GyroScope gyro;
	public static Intake intake;
	public static Ramp ramp;
	public static IntakeAngle angleIntake;
	public static AutoRoutines autoRoutines;
	
	public boolean elevatorStat = false;
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		chooser.addDefault(crossAutoLineOnlyOption, crossAutoLineOnlyOption);
		chooser.addObject(positionLeftOption, positionLeftOption);
		chooser.addObject(positionMiddleOption, positionMiddleOption);
		chooser.addObject(positionRightOption, positionRightOption);

		SmartDashboard.putData("Auto choices", chooser);

		gyro = new GyroScope();
		drive = new AutoDrive(RobotMap.leftDrive1, RobotMap.leftDrive2, RobotMap.leftDrive3, RobotMap.rightDrive1, RobotMap.rightDrive2, RobotMap.rightDrive3, gyro);
		elevator = new AutoElevator(RobotMap.elevator1);
		intake = new Intake(RobotMap.leftIntake, RobotMap.rightIntake);
		ramp = new Ramp(RobotMap.rampRelease, RobotMap.rampWinch);
		angleIntake = new IntakeAngle(RobotMap.inclineMotor);
		autoRoutines = new AutoRoutines();

		System.out.println("ElevatorEncoder, LeftDriveVoltage, LeftDriveCurrent, RightDriveVoltage, RightDriveCurrent");
		
		ramp.keepRampUp();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString line to get the
	 * auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the SendableChooser
	 * make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit()
	{
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		elevator.resetEncoder();
		drive.resetAllEncoder();
		RobotMap.time.start();
		
		String gameData = null;
		while (gameData == null)
		{
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}

		if (gameData.charAt(0) == 'L' && !autoSelected.equals(crossAutoLineOnlyOption))
		{
			autoSelected = "SwitchLeftAnd" + autoSelected;
		} else if (gameData.charAt(0) == 'R' && !autoSelected.equals(crossAutoLineOnlyOption))
		{
			autoSelected = "SwitchRightAnd" + autoSelected;
		}
		System.out.println("Auto selected: " + autoSelected);
		AutoState.state = 0;
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
	{
		
		System.out.println(RobotMap.rightDrive1.getSensorCollection().getQuadraturePosition());
		switch (autoSelected)
		{
		case "CrossAutoLineOnly":
			//CrossAutoLineOnly.runPeriodic();
			autoRoutines.crossAutoLine();
			System.out.println("Cross AutoLine");
			break;
		case "SwitchLeftAndPositionLeft":
			//SwitchLeftAndPositionLeft.runPeriodic();
			autoRoutines.botLeftSwitchLeft();
			System.out.println("SLPL");
			break;
		case "SwitchLeftAndPositionMiddle":
			SwitchLeftAndPositionMiddle.runPeriodic();
			System.out.println("SLPM");
			break;
		case "SwitchLeftAndPositionRight":
			//SwitchLeftAndPositionRight.runPeriodic();
			System.out.println("SLPR");
			autoRoutines.botRightSwitchLeft();
			break;
		case "SwitchRightAndPositionLeft":
			//SwitchRightAndPositionLeft.runPeriodic();
			autoRoutines.botLeftSwitchRight();
			System.out.println("SRPL");
			break;
		case "SwitchRightAndPositionMiddle":
			SwitchRightAndPositionMiddle.runPeriodic();
			System.out.println("SRPM");
			break;
		case "SwitchRightAndPositionRight":
			//SwitchRightAndPositionRight.runPeriodic();
			autoRoutines.botRightSwitchRight();
			System.out.println("SRPR");
			break;
		}
//		switch (autoSelected)
//		{
//		case "CrossAutoLineOnly":
//			System.out.println("Cross");
//			break;
//		case "SwitchLeftAndPositionLeft":
//			//SwitchLeftAndPositionLeft.runPeriodic();
//			//autoRoutines.botLeftSwitchLeft();
//			System.out.println("SwitchLeftBotLeft");
//			break;
//		case "SwitchLeftAndPositionMiddle":
//			SwitchLeftAndPositionMiddle.runPeriodic();
//			break;
//		case "SwitchLeftAndPositionRight":
//			//SwitchLeftAndPositionRight.runPeriodic();
//			//autoRoutines.botRightSwitchLeft();
//			System.out.println("SwitchLeftBotRight");
//			break;
//		case "SwitchRightAndPositionLeft":
//			//SwitchRightAndPositionLeft.runPeriodic();
//			//autoRoutines.botLeftSwitchRight();
//			System.out.println("SwithRightBotLeft");
//			break;
//		case "SwitchRightAndPositionMiddle":
//			//SwitchRightAndPositionMiddle.runPeriodic();
//			break;
//		case "SwitchRightAndPositionRight":
//			//SwitchRightAndPositionRight.runPeriodic();
//			System.out.println("switchRightBotRight");
//			break;
//		}
	}

	@Override
	public void teleopInit()
	{
		//elevator.dropElevator();
		drive.resetAllEncoder();
		elevator.resetEncoder();
		elevator.defaultPIDLSMotor();
		gyro.reset();
		elevator.currentLimit();
		//ramp.keepRampUp();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{		
		/** Drive code */

		
		if (driveState == 0) // 0 is Tank Drive
		{ 
			drive.SRXtankDrive(-RobotMap.driveStick.getRawAxis(1), -RobotMap.driveStick.getRawAxis(5));
		} else if (driveState == 1)
		{
			drive.SRXarcadeDrive(RobotMap.driveStick.getRawAxis(0), RobotMap.driveStick.getRawAxis(1));
		}

		if (RobotMap.driveStick.getRawButton(7))
		{
			driveState = 0;
		} else if (RobotMap.driveStick.getRawButton(8))
		{
			driveState = 1;
		}
		
		if(RobotMap.opBoard.getRawButton(9)) {
			angleIntake.angleIntake(-0.5);
		}
		else if(RobotMap.opBoard.getRawButton(10)) {
			angleIntake.angleIntake(0.5);
		}
		else {
			angleIntake.angleIntake(0);
		}
		
		if(RobotMap.opBoard.getRawButton(11)) {
			if(elevatorStat == true) {
				elevatorStat = false;
				System.out.println("false");
			}
			else {
				elevatorStat = true;
				System.out.println("true");
			}
		}
		
		if(elevatorStat) {
			if(RobotMap.opBoard.getRawButton(2)) {
				RobotMap.elevator1.set(1);
			}
			else if(RobotMap.opBoard.getRawButton(3)) {
				RobotMap.elevator1.set(-1);
			}
			else {
				RobotMap.elevator1.set(0);
			}
		}
		else {
			elevator.moveUsingPot(RobotMap.opBoard.getThrottle());
		}
			
		// Ramp.deployRamp(RobotMap.driveStick.getRawButton(4),
		// RobotMap.opStick.getRawButton(4), RobotMap.opStick.getRawButton(6));
		// System.out.println(Elevator.getEncoderValues());
		// System.out.println(drive.getRightEncoder() + " <-- Right Encoder Values Left
		// Encoder Values --> " + drive.getLeftEncoder());
		
		//Intake.intake(RobotMap.opStick.getRawAxis(2), RobotMap.opStick.getRawAxis(3));
		
		intake.intake(RobotMap.opBoard);
		//System.out.println("Limit Switch: " + RobotMap.elevatorLimitSwitch.get());
		
		//ramp.releaseRamp(RobotMap.opBoard);
		/*if(RobotMap.driveStick.getRawButton(1)) {
			drive.resetAllEncoder();
		}*/
		if(RobotMap.opBoard.getRawButton(7))
		{
			elevator.dropElevator();
			elevator.resetEncoder();
		}
		System.out.println("Left Encoder: " + drive.getLeftEncoder() + "    Right Encoder: " + drive.getRightEncoder() + "    Elevator: " + elevator.getEncoder());
		System.out.println(elevator.getElevatorCurrent());
		
	}

	@Override
	public void disabledInit()
	{
		AutoState.state = 0;
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic()
	{
		System.out.println("DO NOT RUN AT FULL SPEED IF YOU DON'T WANT TO BREAK THE ROBOT");
		if (RobotMap.driveStick.getRawButton(0) == true)
		{
			RobotMap.leftDrive1.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(drive.getLeftEncoder());
		}

		else if (RobotMap.driveStick.getRawButton(1) == true)
		{
			RobotMap.leftDrive2.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(drive.getLeftEncoder());
		}

		else if (RobotMap.driveStick.getRawButton(3) == true)
		{
			RobotMap.rightDrive1.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(drive.getRightEncoder());
		}

		else if (RobotMap.driveStick.getRawButton(4) == true)
		{
			RobotMap.rightDrive2.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(drive.getRightEncoder());
		}

		else if (RobotMap.driveStick.getRawButton(6) == true)
		{
			RobotMap.elevator1.set(RobotMap.slideRaisingSpeed);
			if (RobotMap.elevator1.getSensorCollection().getQuadraturePosition() == RobotMap.slideBeforeTopLimit)
			{
				RobotMap.elevator1.set(RobotMap.slideHoverSpeed);
			}
			System.out.println(RobotMap.elevator1.getSensorCollection().getQuadraturePosition());
		}

		else if (RobotMap.driveStick.getRawButton(7) == true)
		{
			RobotMap.elevator1.set(RobotMap.slideLoweringSpeed);
			if (RobotMap.elevator1.getSensorCollection().getQuadraturePosition() == 0)
			{
				RobotMap.elevator1.set(0);
			}
			System.out.println(RobotMap.elevator1.getSensorCollection().getQuadraturePosition());
		}

		else
		{
			drive.resetAllEncoder();
			RobotMap.elevator1.getSensorCollection().setQuadraturePosition(0, 10);
			drive.stopAllSpeed();
		}
	}
}