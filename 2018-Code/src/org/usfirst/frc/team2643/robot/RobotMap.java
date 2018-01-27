package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;

public class RobotMap {
	/*
	 * These are the motors, encoders, joysticks and limit switch
	 */
	//Drive motors
	public static WPI_TalonSRX t1 = new WPI_TalonSRX(1);
	public static WPI_TalonSRX t2 = new WPI_TalonSRX(2);
	public static WPI_TalonSRX t3 = new WPI_TalonSRX(3);
	public static WPI_TalonSRX t4 = new WPI_TalonSRX(4);
	public static WPI_TalonSRX t5 = new WPI_TalonSRX(5);
	public static WPI_TalonSRX t6 = new WPI_TalonSRX(6);
	
	//Motor to move elevator up
	public static WPI_TalonSRX s1 = new WPI_TalonSRX(7);
	
	
	//slid limit for elevator
	public static DigitalInput slideTopLimit = new DigitalInput(2);
	public static DigitalInput slideBottomLimit = new DigitalInput(1);
	
	//joystick
	public static Joystick driveStick = new Joystick(0);
	public static Joystick opStick = new Joystick(1);
	
	//Encoders
	public static Encoder leftEncoder = new Encoder(0,1);
	public static Encoder rightEncoder = new Encoder(1,0);
	public static Encoder slideEncoder = new Encoder(1,1);

	
	/*
	 * These are variables used in the autonomous code
	 */
	
	//the speeds for the robot
	
	//please never use this the battery will drain muy rapido
	public static double fullSpeed = 1;
	//Set all motors to cruising speed! Aye aye cap'n!
	public static double cruisingSpeed = 0.6;
	//A reduced speed for 
	public static double reducedSpeed = 0.2;
	
	//makes it so that it only prints statements when debug is true
	public static final boolean DEBUG = true;
	
	//speed for lowering the slide
	public static double slideLoweringSpeed = -0.3; //TODO
	
	//speed for raising the slide
	public static double slideRaisingSpeed = 0.3; //TODO
	
	//speed for mainting the position of the slide
	public static double slideHoverSpeed = 0; //TODO
}
