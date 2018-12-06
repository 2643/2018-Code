package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
<<<<<<< HEAD
=======
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Solenoid;
>>>>>>> master
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
<<<<<<< HEAD
=======

	public static Drive drive;
<<<<<<< HEAD
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
	
	static boolean elevatorToggle = true;
	
	public static Encoder leftEncoder = new Encoder(2,3);
	public static Encoder rightEncoder = new Encoder(0,1);
	
=======
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f

>>>>>>> master
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
<<<<<<< HEAD
		
		int amps = 35;
		int timeoutMs = 10;
		RobotMap.rightBackMotor.configContinuousCurrentLimit(amps, timeoutMs);
		RobotMap.rightBackMotor.configPeakCurrentLimit(amps, timeoutMs);
		RobotMap.rightBackMotor.enableCurrentLimit(true);
		
		RobotMap.rightMiddleMotor.configContinuousCurrentLimit(amps, timeoutMs);
		RobotMap.rightMiddleMotor.configPeakCurrentLimit(amps, timeoutMs);
		RobotMap.rightMiddleMotor.enableCurrentLimit(true);
		
		RobotMap.rightFrontMotor.configContinuousCurrentLimit(amps, timeoutMs);
		RobotMap.rightFrontMotor.configPeakCurrentLimit(amps, timeoutMs);
		RobotMap.rightFrontMotor.enableCurrentLimit(true);
		
		RobotMap.leftBackMotor.configContinuousCurrentLimit(amps, timeoutMs);
		RobotMap.leftBackMotor.configPeakCurrentLimit(amps, timeoutMs);
		RobotMap.leftBackMotor.enableCurrentLimit(true);
		
		RobotMap.leftMiddleMotor.configContinuousCurrentLimit(amps, timeoutMs);
		RobotMap.leftMiddleMotor.configPeakCurrentLimit(amps, timeoutMs);
		RobotMap.leftMiddleMotor.enableCurrentLimit(true);
		
		RobotMap.leftFrontMotor.configContinuousCurrentLimit(amps, timeoutMs);
		RobotMap.leftFrontMotor.configPeakCurrentLimit(amps, timeoutMs);
		RobotMap.leftFrontMotor.enableCurrentLimit(true);

=======

<<<<<<< HEAD
		gyro = new GyroScope();
		drive = new Drive(RobotMap.leftDrive1, RobotMap.leftDrive2, /*RobotMap.leftDrive3,*/ RobotMap.rightDrive1, RobotMap.rightDrive2);
		elevator = new Elevator(RobotMap.elevator1, RobotMap.elevator2, 0);
		intake = new Intake(RobotMap.leftIntake, RobotMap.rightIntake);
		angleIntake = new IntakeAngle(RobotMap.inclineMotor);
		autoRoutines = new AutoRoutines();
		
		//gyro.calibrate();
=======
		drive = new Drive(
				RobotMap.leftDrive1,
				RobotMap.leftDrive2,
				RobotMap.rightDrive1,
				RobotMap.rightDrive2);
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
>>>>>>> master
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
<<<<<<< HEAD
		elevator.resetEncoder();
		RobotMap.time.start();
		
		String gameData = null;
		while (gameData == null)
=======

<<<<<<< HEAD
		while(!RobotMap.slideBottomLimit.get()) {
			RobotMap.elevator.set(-0.3);
		}

		RobotMap.elevator.set(0);

		RobotMap.slideEncoder.reset();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();
=======
		RobotMap.elevator1.getSensorCollection().setQuadraturePosition(0, 10);
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f

		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L' && !autoSelected.equals(crossAutoLineOnlyOption))
		{
			autoSelected = "SwitchLeftAnd"  + autoSelected;
		} 
		else if(gameData.charAt(0) == 'R' && !autoSelected.equals(crossAutoLineOnlyOption))
>>>>>>> master
		{
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}		
		
		/*if(gameData.charAt(1) == 'L' && autoSelected.equals(positionLeftOption) && !autoSelected.equals(crossAutoLineOnlyOption)) {
			autoSelection = doLeftScale;
		}*/
		if(autoSelected.equals(positionLeftOption)) {
			if(gameData.charAt(0) == 'L') {
				autoSelection = doLeftSwitch;
			}
			else {
				autoSelection = crossAuto;
			}
			
		}
		/*else if(gameData.charAt(1) == 'R' && autoSelected.equals(positionRightOption) && !autoSelected.equals(crossAutoLineOnlyOption)) {
			autoSelection = doRightScale;
		}*/
		else if(autoSelected.equals(positionRightOption)) {
			if(gameData.charAt(0) == 'R') {
				autoSelection = doRightSwitch;
			}
			else {
				autoSelection = crossAuto;
			}
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
				autoSelection = doOppositeMiddleRPos;
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
				//autoRoutines.doOurSwitch();
				System.out.println("BotLeftSwitchLeft");
			break;
			}
		case doRightSwitch:
			{
				autoRoutines.doOurSwitch();
				System.out.println("BotRightSwitchRight");
			break;
			}
		case doMiddleSwitch: //right middle (use this)
			{
				autoRoutines.middleCube();
				System.out.println("Middle Do Switch");
			break;
			}
		case doOppositeMiddleLPos:
			{
				autoRoutines.doOppositeMiddle();
				System.out.println("Middle Opposite Switch Left");
			break;
			}
		case doOppositeMiddleRPos: //right middle (use this)
			{
				autoRoutines.doOppositeMiddle();
				//autoRoutines.doOppositeMiddleWithEncoder();
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
<<<<<<< HEAD
	public void teleopPeriodic()
	{	
=======
	public void teleopPeriodic() {
<<<<<<< HEAD
		/*Drive code*/
		if(RobotMap.driveStick.getRawButton(2)) { driveState = 1; }
		else if(RobotMap.driveStick.getRawButton(1)) { driveState = 0; }
		//Changes drive state. 
=======

		/** Drive code*/
		//TODO TEST!!!
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
		if(driveState == 0) { //0 is Tank Drive
			Robot.drive.setLeftSpeed(RobotMap.driveStick.getRawAxis(1));
			Robot.drive.setRightSpeed(RobotMap.driveStick.getRawAxis(5));
		}
>>>>>>> master
		
		drive.SRXtankDrive(-RobotMap.driveStick.getRawAxis(1), -RobotMap.driveStick.getRawAxis(5));
		intake.intake(RobotMap.opBoard);
		elevator.buttonPosControl(RobotMap.elevatorUp, RobotMap.elevatorDown);
		angleIntake.angleUsingButtons(RobotMap.winchUp, RobotMap.winchDown);
		
<<<<<<< HEAD
	//	autoRoutines.doOppositeMiddleWithEncoder();
=======
		//TODO TEST
		if(RobotMap.opStick.getPOV() == 0){
<<<<<<< HEAD
			if (!AutoState.limitMotorOverElevator && AutoState.motorPower > AutoState.motorPowerLimit) { /*JUST IN CASE*/ } 
			else {
				RobotMap.e1.set(RobotMap.slideRaisingSpeed);
				AutoState.elevatorPower = RobotMap.slideRaisingSpeed;
				
				if (RobotMap.slideEncoder.get() == RobotMap.slideBeforeTopLimit) {
					RobotMap.e1.set(RobotMap.slideHoverSpeed);
					AutoState.elevatorPower = RobotMap.slideHoverSpeed;
				}
=======
			if(RobotMap.elevator1.getSensorCollection().getQuadraturePosition() == RobotMap.slideBeforeTopLimit){
				RobotMap.elevator1.set(RobotMap.slideHoverSpeed);
			}else{
				RobotMap.elevator1.set(RobotMap.slideRaisingSpeed);
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
			}
		}
>>>>>>> master
		
		/** Drive code */
//		double leftDriveEncoder =RobotMap.leftDrive1.getSensorCollection().getQuadraturePosition();
//		double rightDriveEncoder =RobotMap.rightDrive1.getSensorCollection().getQuadraturePosition();
		//double leftEncoderPotentialDifference = RobotMap.rightIntake.getSensorCollection().getQuadraturePosition();
		
		
		/*if(RobotMap.opBoard.getRawButton(6)) {
			elevator.resetEncoder();
		}*/
		
		//System.out.println(leftDriveEncoder + "\t" + rightDriveEncoder);
		
		if(RobotMap.DEBUG)
		{
			System.out.println(elevator.getEncoder());
			//System.out.println("E Limit Switch: " + RobotMap.elevatorLimitSwitch.get());
		}
		/*
if(elevatorToggle) {
			
			
			if(RobotMap.opBoard.getRawButton(1) && RobotMap.opBoard.getRawButton(9)) {
				elevatorToggle = !elevatorToggle;
			}
			
		}
		else {
			if(RobotMap.opBoard.getRawButton(10))
			{
				elevator.dropElevator();
			}
			else
			{
				elevator.moveUsingPot(RobotMap.opBoard.getThrottle());
			}
			
			if(RobotMap.opBoard.getRawButton(1) && RobotMap.opBoard.getRawButton(9)) {
				elevatorToggle = !elevatorToggle;
			}
		}*/
		
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
<<<<<<< HEAD
	public void testPeriodic()
	{
		
		int leftDriveEncoder =leftEncoder.get();
		int rightDriveEncoder =rightEncoder.get();
		System.out.println(leftDriveEncoder + "\t" + rightDriveEncoder);
		
		
		autoRoutines.doOppositeMiddleWithEncoder();
		
		
//		if(RobotMap.opBoard.getRawButton(1)) {
//			gyro.reset();
//		}
//		System.out.println(gyro.getAngle());
		
		/*System.out.println("DO NOT RUN AT FULL SPEED IF YOU DON'T WANT TO BREAK THE ROBOT");
		if (RobotMap.driveStick.getRawButton(0) == true)
		{
=======
	public void testPeriodic() {
		System.out.println("DO NOT RUN AT FULL SPEED IF YOU DON'T WANT TO BREAK THE ROBOT");
		if(RobotMap.driveStick.getRawButton(0) == true){
<<<<<<< HEAD
			RobotMap.leftFrontMotor.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(RobotMap.leftEncoder.get());
=======
>>>>>>> master
			RobotMap.leftDrive1.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(drive.getLeftEncoder());
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
		}

<<<<<<< HEAD
		else if (RobotMap.driveStick.getRawButton(1) == true)
		{
=======
		else if(RobotMap.driveStick.getRawButton(1) == true){
<<<<<<< HEAD
			RobotMap.leftMiddleMotor.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(RobotMap.leftEncoder.get());
		}
		
		else if(RobotMap.driveStick.getRawButton(2) == true){
			RobotMap.leftBackMotor.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(RobotMap.leftEncoder.get());
		}
		
		else if(RobotMap.driveStick.getRawButton(3) == true){
			RobotMap.rightFrontMotor.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(RobotMap.rightEncoder.get());
=======
>>>>>>> master
			RobotMap.leftDrive2.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(drive.getLeftEncoder());
		}

		else if (RobotMap.driveStick.getRawButton(3) == true)
		{
			RobotMap.rightDrive1.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(drive.getRightEncoder());
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
		}

<<<<<<< HEAD
		else if (RobotMap.driveStick.getRawButton(4) == true)
		{
=======
		else if(RobotMap.driveStick.getRawButton(4) == true){
<<<<<<< HEAD
			RobotMap.rightMiddleMotor.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(RobotMap.rightEncoder.get());
		}
		
		else if(RobotMap.driveStick.getRawButton(5) == true){
			RobotMap.rightBackMotor.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(RobotMap.rightEncoder.get());
=======
>>>>>>> master
			RobotMap.rightDrive2.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(drive.getRightEncoder());
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
		}

<<<<<<< HEAD
		else if (RobotMap.driveStick.getRawButton(6) == true)
		{
=======

		else if(RobotMap.driveStick.getRawButton(6) == true){
<<<<<<< HEAD
			RobotMap.elevator.set(RobotMap.slideRaisingSpeed);
			if(RobotMap.slideEncoder.get() == RobotMap.slideBeforeTopLimit){
				RobotMap.elevator.set(RobotMap.slideHoverSpeed);
=======
>>>>>>> master
			RobotMap.elevator1.set(RobotMap.slideRaisingSpeed);
			if (RobotMap.elevator1.getSensorCollection().getQuadraturePosition() == RobotMap.slideBeforeTopLimit)
			{
				RobotMap.elevator1.set(RobotMap.slideHoverSpeed);
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
			}
			System.out.println(RobotMap.elevator1.getSensorCollection().getQuadraturePosition());
		}

<<<<<<< HEAD
		else if (RobotMap.driveStick.getRawButton(7) == true)
		{
=======
		else if(RobotMap.driveStick.getRawButton(7) == true){
<<<<<<< HEAD
			RobotMap.e1.set(RobotMap.slideLoweringSpeed);
			if(RobotMap.slideBottomLimit.get()){
				RobotMap.elevator.set(0);
=======
>>>>>>> master
			RobotMap.elevator1.set(RobotMap.slideLoweringSpeed);
			if (RobotMap.elevator1.getSensorCollection().getQuadraturePosition() == 0)
			{
				RobotMap.elevator1.set(0);
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
			}
			System.out.println(RobotMap.elevator1.getSensorCollection().getQuadraturePosition());
		}

<<<<<<< HEAD
		else
		{
			drive.resetAllEncoder();
			RobotMap.elevator1.getSensorCollection().setQuadraturePosition(0, 10);
			drive.stopAllSpeed();
		}*/
=======
		else{
<<<<<<< HEAD
			RobotMap.leftEncoder.reset();
			RobotMap.rightEncoder.reset();
			RobotMap.slideEncoder.reset();
			RobotMap.rightFrontMotor.set(0);
			RobotMap.rightMiddleMotor.set(0);
			RobotMap.rightBackMotor.set(0);
			RobotMap.leftFrontMotor.set(0);
			RobotMap.leftMiddleMotor.set(0);
			RobotMap.leftBackMotor.set(0);
			
			if(RobotMap.slideBottomLimit.get() == false){
				RobotMap.elevator.set(RobotMap.slideLoweringSpeed);
			}
			else if(RobotMap.slideBottomLimit.get() == true){
				RobotMap.elevator.set(0);
			}
=======
			drive.resetAllEncoder();
			RobotMap.elevator1.getSensorCollection().setQuadraturePosition(0, 10);
			drive.stopAllSpeed();
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
		}
>>>>>>> master
	}
}
