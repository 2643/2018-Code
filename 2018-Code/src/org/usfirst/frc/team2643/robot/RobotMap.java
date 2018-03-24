package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

public class RobotMap
{
	/* ------------------- REMEMBER TO CHANGE!!!!!!!!!!!! ------------------------ */
	//test mode
	static boolean inTestStage = !true; 
	/* --------------------------------------------------------------------------- */
	
	/*
	 * These are the motors, encoders, joysticks and limit switch
	 */
	// Drive motors
	public static final WPI_TalonSRX leftDrive1 = new WPI_TalonSRX(14); // Front Left
	public static final WPI_TalonSRX leftDrive2 = new WPI_TalonSRX(15); // Back left
	public static final WPI_TalonSRX rightDrive1 = new WPI_TalonSRX(1); // Front Right
	public static final WPI_TalonSRX rightDrive2 = new WPI_TalonSRX(16); // Right Back
	public static final WPI_TalonSRX leftDrive3 = new WPI_TalonSRX(13); //Middle Left
	public static final WPI_TalonSRX rightDrive3 = new WPI_TalonSRX(12); //Middle Right
	
	// Motor to move elevator up
	public static final WPI_TalonSRX elevator1 = new WPI_TalonSRX(5);
	public static final DigitalInput elevatorLimitSwitch = new DigitalInput(9);
	
	//Intake motors
	public static final WPI_TalonSRX leftIntake = new WPI_TalonSRX(9);
	public static final WPI_TalonSRX rightIntake = new WPI_TalonSRX(6);
	
	//Intake Bag/Angle Motor
	//public static final WPI_TalonSRX inclineMotor = new WPI_TalonSRX(4);

	// Acceptable encoder error
	public static final int ACCEPTABLE_ENCODER_ERROR = 100;

	// slide limits for elevator
	public static final int slideBeforeTopLimit = 0; // TODO test this out at some point

	// joystick
	public static final Joystick driveStick = new Joystick(0);
	public static final Joystick opBoard = new Joystick(1);
	public static final Joystick opStick = new Joystick(2);

	// the buttons for the robot
	public static int rampReleaseButton = 11;
	public static int rampUpButton = 10;
	public static int bothIntakeButton = 8;
	public static int bothOuttakeButton = 6;
	public static int intakeLeftButton = 5;
	public static int intakeRightButton = 4;
	public static int safetyButton = 1;
	public static int outtakeLeftButton = 3;
	public static int outtakeRightButton = 2;
	public static int extraCenterMiddleButton = 7; // TODO extra button number
	public static int rampDownButton= 12;
	public static int extraRightButton = 9; // TODO extra button number

	// ramps
	public static Servo rampRelease = new Servo(9);
	public static WPI_TalonSRX rampWinch = new WPI_TalonSRX(13); // TODO enter ID
	
	//Autonomous state value
	public static int autoState = 0;
	
	// Timers
	public static Timer time = new Timer();

	// ***the speeds for the robot***

	// please never use this the battery will drain muy rapido
	public static final double fullSpeed = 1;

	// Set all motors to cruising speed! Aye aye cap'n!
	public static final double cruisingSpeed = 0.3;

	// A reduced speed for careful stuff
	public static final double reducedSpeed = 0.3;

	// makes it so that it only prints statements when debug is true
	public static final boolean DEBUG = false;

	// speed for lowering the slide
	public static final double slideLoweringSpeed = -0.3; // TODO get speed

	// speed for raising the slide
	public static final double slideRaisingSpeed = 0.3; // TODO get speed

	// speed for mainting the position of the slide
	public static final double slideHoverSpeed = 0; // TODO get speed

	// speed for output cube
	public static final double outputCubeSpeed = 1;
	// speed for input cube
	public static final double inputCubeSpeed = -0.7;

	// Elevator variables
	static int ticksPerFoot = 3530;
	static int ticksPerInch = ticksPerFoot / 12;

	// Drive encoder rotation
	static int ticksPerRotationleft = 714;
	static int ticksPerRotationRight = 0;
	
	// feet to ticks method and variables
	static double radius = 0.25;// (feet) 3 inches
	static double circumferenceFeet = 2 * Math.PI * radius;
	static double ticksPerRotation = 255;

	public static int feetToTicks(double feet)
	{
		double rotations = feet / circumferenceFeet;
		return (int) (rotations * ticksPerRotation);
	}
}
