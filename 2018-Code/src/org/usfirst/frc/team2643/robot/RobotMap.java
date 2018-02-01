package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;

public class RobotMap {
	/*
	 * These are the motors, encoders, joysticks and limit switch
	 */
	//Drive motors
	public static Spark lFrontMotor = new Spark(5);
	public static Spark lBackMotor =  new Spark(2);
	public static Spark rFrontMotor = new Spark(6);
	public static Spark rBackMotor = new Spark(1);
	
	
	//joystick
	public static final Joystick driveStick = new Joystick(0);
	public static final Joystick opStick = new Joystick(1);
	
	//Encoders
	public static final Encoder leftEncoder = new Encoder(1, 2);
	public static final Encoder rightEncoder = new Encoder(3, 4);

	//the buttons for the robot
	public static int RightRampDown = 0; //TODO put in the button numbers for these
	public static int RightRampUp = 0; //TODO
	public static int LeftRampDown = 0; //TODO
	public static int LeftRampUp = 0; //TODO
	
	
	//the speeds for the robot
	
	//please never use this the battery will drain muy rapido
	public static final double fullSpeed = 1;
	
	//Set all motors to cruising speed! Aye aye cap'n!
	public static final double cruisingSpeed = 0.6; //TODO
	
	//A reduced speed for careful stuff 
	public static final double reducedSpeed = 0.2;  //TODO
	
	//makes it so that it only prints statements when debug is true
	public static final boolean DEBUG = true;
}
