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

		while(!RobotMap.slideBottomLimit.get()) {
			RobotMap.elevator.set(-0.3);
		}

		RobotMap.elevator.set(0);

		RobotMap.slideEncoder.reset();
		RobotMap.leftEncoder.reset();
		RobotMap.rightEncoder.reset();

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


	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		/*Drive code*/
		if(RobotMap.driveStick.getRawButton(2)) { driveState = 1; }
		else if(RobotMap.driveStick.getRawButton(1)) { driveState = 0; }
		//Changes drive state. 
		if(driveState == 0) { //0 is Tank Drive
			RobotMovementMethods.SRXtankDrive(RobotMap.driveStick.getRawAxis(1), RobotMap.driveStick.getRawAxis(5));
		}
		else if(driveState == 1) { //1 is arcade
			RobotMovementMethods.SRXarcadeDrive(RobotMap.driveStick.getRawAxis(0),RobotMap.driveStick.getRawAxis(1));
		}

		 /* Elevator code
		 * -winding it up moves the elevator up
		 * -unwinding it will drop the elevator
		 * -needs power to keep the elevator up
		 * -encoders
		 * -limit switch on the bottom
		 */
		
		if(RobotMap.opStick.getPOV() == 0){
			if (!AutoState.limitMotorOverElevator && AutoState.motorPower > AutoState.motorPowerLimit) { /*JUST IN CASE*/ } 
			else {
				RobotMap.e1.set(RobotMap.slideRaisingSpeed);
				AutoState.elevatorPower = RobotMap.slideRaisingSpeed;
				
				if (RobotMap.slideEncoder.get() == RobotMap.slideBeforeTopLimit) {
					RobotMap.e1.set(RobotMap.slideHoverSpeed);
					AutoState.elevatorPower = RobotMap.slideHoverSpeed;
				}
			}
		}
		else if(RobotMap.opStick.getPOV() == 180){
			if (!AutoState.limitMotorOverElevator && AutoState.motorPower > AutoState.motorPowerLimit) { /*JUST IN CASE*/ } else {
				RobotMap.e1.set(RobotMap.slideRaisingSpeed);
				AutoState.elevatorPower = RobotMap.slideRaisingSpeed;
				if (RobotMap.slideEncoder.get() == RobotMap.slideBeforeTopLimit) {
					RobotMap.e1.set(RobotMap.slideHoverSpeed);
					AutoState.elevatorPower = RobotMap.slideHoverSpeed;
				}
			}
		}
		else{
			RobotMap.e1.set(0);
			AutoState.elevatorPower = 0;
		}
		
		
		//Ramp Code
		if(RobotMap.opStick.getRawButton(RobotMap.RightRampDown)){
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
			RobotMap.leftFrontMotor.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(RobotMap.leftEncoder.get());
		}
		
		else if(RobotMap.driveStick.getRawButton(1) == true){
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
		}
		
		else if(RobotMap.driveStick.getRawButton(4) == true){
			RobotMap.rightMiddleMotor.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(RobotMap.rightEncoder.get());
		}
		
		else if(RobotMap.driveStick.getRawButton(5) == true){
			RobotMap.rightBackMotor.set(RobotMap.driveStick.getRawAxis(5));
			System.out.println(RobotMap.rightEncoder.get());
		}
		
		else if(RobotMap.driveStick.getRawButton(6) == true){
			RobotMap.elevator.set(RobotMap.slideRaisingSpeed);
			if(RobotMap.slideEncoder.get() == RobotMap.slideBeforeTopLimit){
				RobotMap.elevator.set(RobotMap.slideHoverSpeed);
			}
			System.out.println(RobotMap.slideEncoder.get());
		}
		
		else if(RobotMap.driveStick.getRawButton(7) == true){
			RobotMap.e1.set(RobotMap.slideLoweringSpeed);
			if(RobotMap.slideBottomLimit.get()){
				RobotMap.elevator.set(0);
			}
			System.out.println(RobotMap.slideEncoder.get());
		}
		
		else{
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
		}
	}
}
