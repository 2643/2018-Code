package org.usfirst.frc.team2643.robot;

//TalonSRX class import
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Solenoid;
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
	final String crossAutoLineOnlyOption = "CrossAutoLineOnly";
	final String positionLeftOption = "PositionLeft";
	final String positionMiddleOption = "PositionMiddle";
	final String positionRightOption = "PositionRight";

	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

	int driveState = 0;
<<<<<<< HEAD
=======


	double batteryVoltage = DriverStation.getInstance().getBatteryVoltage();

	public static Drive drive;
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault(crossAutoLineOnlyOption, crossAutoLineOnlyOption);

		chooser.addObject(positionLeftOption, positionLeftOption);
		chooser.addObject(positionMiddleOption, positionMiddleOption);
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

		drive = new Drive(
				RobotMap.leftDrive1,
				RobotMap.leftDrive2,
				RobotMap.rightDrive1,
				RobotMap.rightDrive2);
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
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
		drive.setToPositionMode();
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);

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
		{
			autoSelected = "SwitchRightAnd" + autoSelected;
		}

		System.out.println("Auto selected: " + autoSelected);
		AutoState.armsReleasing = false;	
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
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


	@Override
	public void teleopInit() {
		drive.setToPercentValue();
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
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
		

		/* Elevator code
		 * -winding it up moves the elevator up!!!!!
		 * -unwinding it will drop the elevator
		 * -check brake mode on talons
		 * -encoders
		 * -limit switch on the bottom
		 */
		
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
		
		else if(RobotMap.opStick.getPOV() == 180){
			if (RobotMap.elevator1.getSensorCollection().getQuadraturePosition() == 0) {
				RobotMap.elevator1.set(0);
			}else {
				RobotMap.elevator1.set(RobotMap.slideLoweringSpeed);
			}
		}
		else if(RobotMap.elevator1.getSensorCollection().getQuadraturePosition() != 0){
			RobotMap.elevator1.set(RobotMap.slideHoverSpeed);
		}
		else {
			RobotMap.elevator1.set(0);
		}


		//Ramp Code
		//TODO TEST!!! TEST!!! TEST!!!
		if(RobotMap.opStick.getRawButton(RobotMap.RightRampUp)){
			RobotMap.RightFrontSolenoid.set(true);
			RobotMap.RightBackSolenoid.set(true);
		}
		else if(RobotMap.opStick.getRawButton(RobotMap.RightRampDown)){
			RobotMap.RightFrontSolenoid.set(false);
			RobotMap.RightBackSolenoid.set(false);
		}

		if(RobotMap.opStick.getRawButton(RobotMap.LeftRampUp)){
			RobotMap.LeftFrontSolenoid.set(true);
			RobotMap.LeftBackSolenoid.set(true);
		}
		else if(RobotMap.opStick.getRawButton(RobotMap.LeftRampDown)){
			RobotMap.LeftFrontSolenoid.set(false);
			RobotMap.LeftBackSolenoid.set(false);
		}

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		System.out.println("DO NOT RUN AT FULL SPEED IF YOU DON'T WANT TO BREAK THE ROBOT");
		if(RobotMap.driveStick.getRawButton(0) == true){
<<<<<<< HEAD
			RobotMap.leftFrontMotor.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(RobotMap.leftEncoder.get());
=======
			RobotMap.leftDrive1.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(drive.getLeftEncoder());
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
		}

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
			RobotMap.leftDrive2.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(drive.getLeftEncoder());
		}


		else if(RobotMap.driveStick.getRawButton(3) == true){
			RobotMap.rightDrive1.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(drive.getRightEncoder());
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
		}

		else if(RobotMap.driveStick.getRawButton(4) == true){
<<<<<<< HEAD
			RobotMap.rightMiddleMotor.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(RobotMap.rightEncoder.get());
		}
		
		else if(RobotMap.driveStick.getRawButton(5) == true){
			RobotMap.rightBackMotor.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(RobotMap.rightEncoder.get());
=======
			RobotMap.rightDrive2.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(drive.getRightEncoder());
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
		}


		else if(RobotMap.driveStick.getRawButton(6) == true){
<<<<<<< HEAD
			RobotMap.elevator.set(RobotMap.slideRaisingSpeed);
			if(RobotMap.slideEncoder.get() == RobotMap.slideBeforeTopLimit){
				RobotMap.elevator.set(RobotMap.slideHoverSpeed);
=======
			RobotMap.elevator1.set(RobotMap.slideRaisingSpeed);
			if(RobotMap.elevator1.getSensorCollection().getQuadraturePosition() == RobotMap.slideBeforeTopLimit){
				RobotMap.elevator1.set(RobotMap.slideHoverSpeed);
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
			}
			System.out.println(RobotMap.elevator1.getSensorCollection().getQuadraturePosition());
		}

		else if(RobotMap.driveStick.getRawButton(7) == true){
<<<<<<< HEAD
			RobotMap.e1.set(RobotMap.slideLoweringSpeed);
			if(RobotMap.slideBottomLimit.get()){
				RobotMap.elevator.set(0);
=======
			RobotMap.elevator1.set(RobotMap.slideLoweringSpeed);
			if(RobotMap.elevator1.getSensorCollection().getQuadraturePosition() == 0){
				RobotMap.elevator1.set(0);
>>>>>>> 632d0a34346fb87c7c7c2949c595313bed72923f
			}
			System.out.println(RobotMap.elevator1.getSensorCollection().getQuadraturePosition());
		}

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
	}
}
