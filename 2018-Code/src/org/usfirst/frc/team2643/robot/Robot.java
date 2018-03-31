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
	final String positionMiddleLeftOption = "PositionMiddleLeft";
	final String positionMiddleRightOption = "PositionMiddleRight";
	final String positionRightOption = "PositionRight";

	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

	int driveState = 0;

	public static Drive drive;
	public static Elevator elevator;
	public static GyroScope gyro;
	public static Intake intake;
	public static IntakeAngle angleIntake;
	public static AutoRoutines autoRoutines;
	
	final int doLeftScale = 0,
			doRightScale = 1,
			doLeftSwitch = 2,
			doRightSwitch = 3,
			doMiddleSwitch = 4,
			crossAuto = 5,
			doOppositeMiddleRPos = 6,
			doOppositeMiddleLPos = 7;
	
	int autoSelection;
	
	int elevatorState = 1,
			nextState;
	
	final int usingPot = 1,
			transition = 2,
			usingButtons = 3;
	
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		chooser.addDefault(crossAutoLineOnlyOption, crossAutoLineOnlyOption);
		chooser.addObject(positionLeftOption, positionLeftOption);
		chooser.addObject(positionMiddleLeftOption, positionMiddleLeftOption);
		chooser.addObject(positionMiddleRightOption, positionMiddleRightOption);
		chooser.addObject(positionRightOption, positionRightOption);

		SmartDashboard.putData("Auto choices", chooser);

		gyro = new GyroScope();
		drive = new Drive(RobotMap.leftDrive1, RobotMap.leftDrive2, /*RobotMap.leftDrive3,*/ RobotMap.rightDrive1, RobotMap.rightDrive2);
		elevator = new Elevator(RobotMap.elevator1, RobotMap.elevator2, 0);
		intake = new Intake(RobotMap.leftIntake, RobotMap.rightIntake);
		angleIntake = new IntakeAngle(RobotMap.inclineMotor);
		autoRoutines = new AutoRoutines();
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
		System.out.println("Auto selected: " + autoSelected);
		elevator.resetEncoder();
		RobotMap.time.start();
		
		String gameData = null;
		while (gameData == null)
		{
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}		
		
		/*if(gameData.charAt(1) == 'L' && autoSelected.equals(positionLeftOption) && !autoSelected.equals(crossAutoLineOnlyOption)) {
			autoSelection = doLeftScale;
		}*/
		if(gameData.charAt(0) == 'L' && autoSelected.equals(positionLeftOption) && !autoSelected.equals(crossAutoLineOnlyOption)) {
			autoSelection = doLeftSwitch;
		}
		/*else if(gameData.charAt(1) == 'R' && autoSelected.equals(positionRightOption) && !autoSelected.equals(crossAutoLineOnlyOption)) {
			autoSelection = doRightScale;
		}*/
		else if(gameData.charAt(0) == 'R' && autoSelected.equals(positionRightOption) && !autoSelected.equals(crossAutoLineOnlyOption)) {
			autoSelection = doRightSwitch;
		}
		else if(autoSelected.equals(positionMiddleLeftOption)) {
			if(gameData.charAt(0) == 'L') {
				autoSelection = doMiddleSwitch;
			}
			else {
				autoSelection = crossAuto;
			}
		}
		else if(autoSelected.equals(positionMiddleRightOption)) {
			if(gameData.charAt(0) == 'R') {
				autoSelection = doMiddleSwitch;
			}
			else {
				autoSelection = crossAuto;
			}
		}
		else {
			autoSelection = crossAuto;
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
	{	
		if(!isOperatorControl()) {
			System.out.println(autoRoutines.timer.get());  //TODO Remove this print when it becomes unnessecary.
		switch(autoSelection) {
		/*case doLeftScale:
			{
				autoRoutines.doSwitch(false);
				System.out.println("BotLeftScaleLeft");
			break;
			}
		case doRightScale:
			{
				autoRoutines.doScale(true);
				System.out.println("BotRightScaleRight");
			break;
			}*/
		case doLeftSwitch:
			{
				autoRoutines.doSwitch(false);
				System.out.println("BotLeftSwitchLeft");
			break;
			}
		case doRightSwitch:
			{
				autoRoutines.doSwitch(true);
				System.out.println("BotRightSwitchRight");
			break;
			}
		case doMiddleSwitch:
			{
				autoRoutines.middleCube();
				System.out.println("Middle Do Switch");
			break;
			}
		case doOppositeMiddleLPos:
			{
				autoRoutines.doOppositeMiddle(false);
				System.out.println("Middle Opposite Switch Left");
			break;
			}
		case doOppositeMiddleRPos:
			{
				autoRoutines.doOppositeMiddle(true);
				System.out.println("Middle Opposite Switch Right");
			break;
			}
		case crossAuto:
			{
				autoRoutines.crossAutoLine();
				System.out.println("CrossAuto");
			break;
			}
		default:
			{
				autoRoutines.crossAutoLine();
				System.out.println("CrossAuto");
				break;
			}
		}
	}
		
}	

	@Override
	public void teleopInit()
	{
		//elevator.dropElevator();
		//elevator.resetEncoder();
		//elevator.defaultPIDLSMotor();
		//elevator.resetEncoder();
		gyro.reset();
		elevator.currentLimit();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{		
		/** Drive code */

		drive.SRXtankDrive(-RobotMap.driveStick.getRawAxis(1), -RobotMap.driveStick.getRawAxis(5));
		
		intake.intake(RobotMap.opBoard);
		
		if(RobotMap.opBoard.getRawButton(10))
		{
			elevator.dropElevator();
		}
		else
		{
			elevator.moveUsingPot(RobotMap.opBoard.getThrottle());
		}
		
		angleIntake.angleUsingButtons(RobotMap.winchUp, RobotMap.winchDown);
		
		
		
		if(RobotMap.DEBUG)
		{
			System.out.println(elevator.getEncoder() + "\t" + elevator.getElevatorCurrent());
			System.out.println("E Limit Switch: " + RobotMap.elevatorLimitSwitch.get());
		}
		//elevator.usingButtons(RobotMap.opBoard);
		//l System.out.println(elevator.getEncoder());
		
		//System.out.println(RobotMap.opBoard.getThrottle());
//		switch(elevatorState) {
//			case usingPot:
//			{
//				elevator.moveUsingPot(RobotMap.opBoard.getThrottle());
//				if(RobotMap.opBoard.getRawButton(1) && RobotMap.opBoard.getRawButton(9)) {
//					nextState = usingButtons;
//					elevatorState = transition;
//					System.out.println("Changed to Buttons");
//				}
//			}
//			case usingButtons:
//			{
//				elevator.usingButtons(RobotMap.opBoard);
//				if(RobotMap.opBoard.getRawButton(1) && RobotMap.opBoard.getRawButton(9)) {
//					nextState = usingPot;
//					elevatorState = transition;
//					System.out.println("Changed to Pot");
//				}
//			}
//			case transition:
//			{
//				RobotMap.elevator1.set(0);
//				elevatorState = nextState;
//			}
//		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic()
	{
		/*System.out.println("DO NOT RUN AT FULL SPEED IF YOU DON'T WANT TO BREAK THE ROBOT");
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
		}*/
	}
}