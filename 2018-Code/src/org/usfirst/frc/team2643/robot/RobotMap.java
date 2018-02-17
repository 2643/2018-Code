package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;

public class RobotMap {
	/*
	 * These are the motors, encoders, joysticks and limit switch
	 */
	//Drive motors
	public static final WPI_TalonSRX leftDrive1 = new WPI_TalonSRX(14);   //Front Left
	public static final WPI_TalonSRX leftDrive2 = new WPI_TalonSRX(15);   //TODO change this port
	public static final WPI_TalonSRX rightDrive1 = new WPI_TalonSRX(1);  //Front Right
	public static final WPI_TalonSRX rightDrive2 = new WPI_TalonSRX(16);  //Right Back

	//Motor to move elevator up
	public static final WPI_TalonSRX elevator1 = new WPI_TalonSRX(5);    //TODO change this port
	public static final DigitalInput elevatorLimitSwitch = new DigitalInput(1);
	//public static final Elevator e1 = new Elevator();
	
	public static final WPI_TalonSRX leftIntake = new WPI_TalonSRX(6);
	public static final WPI_TalonSRX rightIntake = new WPI_TalonSRX(9);

	//Acceptable encoder error
	public static final int ACCEPTABLE_ENCODER_ERROR = 100;
	
	//slide limits for elevator
	public static final int slideBeforeTopLimit = 0; //TODO test this out at some point
	
	//joystick
	public static final Joystick driveStick = new Joystick(0);
	public static final Joystick opStick = new Joystick(1);
	
	//Solenoids
	//public static Solenoid RightFrontSolenoid = new Solenoid(1);          //TODO change this port
	
	//the buttons for the robot
	public static int RightRampDown = 0; //TODO get the button number
	public static int RightRampUp = 0; //TODO get the button number
	public static int LeftRampDown = 0; //TODO get the button number
	public static int LeftRampUp = 0; //TODO get the button number
	
	 
	//the speeds for the robot
	
	//please never use this the battery will drain muy rapido
	public static final double fullSpeed = 1;
	
	//Set all motors to cruising speed! Aye aye cap'n!
	public static final double cruisingSpeed = 0.6;
	
	//A reduced speed for careful stuff 
	public static final double reducedSpeed = 0.3;
	
	//makes it so that it only prints statements when debug is true
	public static final boolean DEBUG = true;
	
	//speed for lowering the slide
	public static final double slideLoweringSpeed = -0.3; //TODO get speed
	
	//speed for raising the slide
	public static final double slideRaisingSpeed = 0.3; //TODO get speed
	
	//speed for mainting the position of the slide
	public static final double slideHoverSpeed = 0; //TODO get speed
	
	//Elevator variables
	static int ticksPerFoot = 3613;
	static int ticksPerInch = 3613/4;
	
	//feet to ticks method and variables
	static double radius = 0.25;//(feet) 3 inches 
	static double circumferenceFeet = 2*Math.PI*radius;
	static double ticksPerRotation = 255;
	public static int feetToTicks(double feet)
	{
		double rotations = feet/circumferenceFeet;
		return (int)(rotations*ticksPerRotation);
	}
}
