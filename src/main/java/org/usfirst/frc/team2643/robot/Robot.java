package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.CameraServer;
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
public class Robot extends IterativeRobot
{

	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();


	public static Drive drive;
	public static Elevator elevator;
	public static Intake intake;
	
	static boolean elevatorToggle = true;
	
	
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		
		int amps = 35;
		int timeoutMs = 10;
		RobotMap.rightBackMotor.configContinuousCurrentLimit(amps, timeoutMs);
		RobotMap.rightBackMotor.configPeakCurrentLimit(amps, timeoutMs);
		RobotMap.rightBackMotor.enableCurrentLimit(true);
		
		RobotMap.rightFrontMotor.configContinuousCurrentLimit(amps, timeoutMs);
		RobotMap.rightFrontMotor.configPeakCurrentLimit(amps, timeoutMs);
		RobotMap.rightFrontMotor.enableCurrentLimit(true);
		
		RobotMap.leftBackMotor.configContinuousCurrentLimit(amps, timeoutMs);
		RobotMap.leftBackMotor.configPeakCurrentLimit(amps, timeoutMs);
		RobotMap.leftBackMotor.enableCurrentLimit(true);
		
		RobotMap.leftFrontMotor.configContinuousCurrentLimit(amps, timeoutMs);
		RobotMap.leftFrontMotor.configPeakCurrentLimit(amps, timeoutMs);
		RobotMap.leftFrontMotor.enableCurrentLimit(true);

		drive = new Drive(RobotMap.leftFrontMotor, RobotMap.leftBackMotor, /*RobotMap.leftDrive3,*/ RobotMap.rightFrontMotor, RobotMap.rightBackMotor);
		elevator = new Elevator(RobotMap.elevator1, RobotMap.elevator2, 0);
		intake = new Intake(RobotMap.leftIntake, RobotMap.rightIntake);
		
		//gyro.calibrate();
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
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
	{	
		
	
	}
		
	

	@Override
	public void teleopInit()
	{
		
		//elevator.dropElevator();
		//elevator.resetEncoder();
		//elevator.defaultPIDLSMotor();
		//elevator.resetEncoder();
		elevator.currentLimit();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		/*Drive Code*/
		drive.setLeftSpeed(-RobotMap.leftDriveStick.getRawAxis(1));
		drive.setRightSpeed(-RobotMap.rightDriveStick.getRawAxis(1));
		
		/*Elevator Code*/
		if(RobotMap.opBoard.getRawButton(RobotMap.elevatorUp)) {
			RobotMap.elevator1.set(0.6);
			RobotMap.elevator2.set(0.6);
		}
		else if(RobotMap.opBoard.getRawButton(RobotMap.elevatorDown)) {
			RobotMap.elevator1.set(-0.3);
			RobotMap.elevator2.set(-0.3);
		}
		else {
			RobotMap.elevator1.set(0);
			RobotMap.elevator2.set(0);
		}
		
		/*Intake Code*/
		if(RobotMap.opBoard.getRawButton(RobotMap.fastIntakeButton))
		{
			RobotMap.leftIntake.set(-1);
			RobotMap.rightIntake.set(1);
		}
		else if(RobotMap.opBoard.getRawButton(RobotMap.fastOuttakeButton))
		{
			RobotMap.leftIntake.set(1);
			RobotMap.rightIntake.set(-1);
		}
		else if(RobotMap.opBoard.getRawButton(RobotMap.slowIntakeButton)) {
			RobotMap.leftIntake.set(-0.5);
			RobotMap.rightIntake.set(0.5);
		}
		else if(RobotMap.opBoard.getRawButton(RobotMap.slowOuttakeButton)) {
			RobotMap.leftIntake.set(0.5);
			RobotMap.rightIntake.set(-0.5);
		}
		else
		{
			RobotMap.leftIntake.set(0);
			RobotMap.rightIntake.set(0);
		}

		if(RobotMap.opBoard.getRawButton(RobotMap.winchUp))
			RobotMap.inclineMotor.set(0.5);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic()
	{
		
	
	}
}
