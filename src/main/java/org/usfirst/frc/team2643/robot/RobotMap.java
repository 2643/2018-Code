package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class RobotMap
{
	/* ------------------- REMEMBER TO CHANGE!!!!!!!!!!!! ------------------------ */
	//test mode
	static boolean inTestStage = false; 
	/* --------------------------------------------------------------------------- */
	
	/*
	 * These are the motors, encoders, joysticks and limit switch
	 */
	//public static final DigitalInput elevatorLimitSwitch = new DigitalInput(0);

	//Drive motors
	public static final WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(14);   //TODO change this port
	public static final WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(15);   //TODO change this port
	public static final WPI_TalonSRX rightFrontMotor= new WPI_TalonSRX(16);  //TODO change this port
	public static final WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(1);  //TODO change this port

	//Elevator motors
	public static final WPI_TalonSRX elevator1 = new WPI_TalonSRX(5);
	public static final WPI_TalonSRX elevator2 = new WPI_TalonSRX(30);

	//Intake motors
	public static final WPI_TalonSRX leftIntake = new WPI_TalonSRX(9);
	public static final WPI_TalonSRX rightIntake = new WPI_TalonSRX(6);
	
	//Intake Bag/Angle Motor
	public static final WPI_TalonSRX inclineMotor = new WPI_TalonSRX(4);

	//Acceptable encoder error
	public static final int ACCEPTABLE_ENCODER_ERROR = 100;	
	//slide limits for elevator
	public static final int slideBeforeTopLimit = 0; //TODO test this out at some point
	
	
	//joystick
	public static final Joystick leftDriveStick = new Joystick(0);
	public static final Joystick rightDriveStick = new Joystick(1); 
	public static final Joystick opBoard = new Joystick(2);
	
	//buttons
	public static int elevatorUp = 1;
	public static int elevatorDown = 2;
	
	public static int winchUp = 6;
	public static int winchDown = 5
	; 

	public static int fastIntakeButton = 3;
	public static int fastOuttakeButton = 4;
	public static int slowIntakeButton = 0;
	public static int slowOuttakeButton = 0;
	
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
	public static final boolean DEBUG = true;

	// speed for lowering the slide
	public static final double slideLoweringSpeed = -0.15; // TODO get speed

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

	static final int lsEncodersToFeet = 0; //TODO
	static final int lsEncodersToInches = 0; //TODO
	
	static final int defaultPID = 0;
	static final double PIDF = 0.5;
	static final double PIDP = 0.5;
	static final double PIDI = 0;
	static final double PIDD = 0;
	
	static final int maxEncoderValue = 60000; //TODO //From 12750
	static final int moveUpSpeed = 0;
	static final int moveDownSpeed = 0;
	
	static double encoderTick = 0;
	
	public static int feetToTicks(double feet)
	{
		double rotations = feet / circumferenceFeet;
		return (int) (rotations * ticksPerRotation);
	}
}
