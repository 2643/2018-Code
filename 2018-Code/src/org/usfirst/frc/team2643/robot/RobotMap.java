package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;

public class RobotMap {
	/*
	 * These are the motors, encoders, joysticks and limit switch
	 */
	//Current Numbers
	static double current0, current1, current2, current5, current6, current8, current9, current10, current13, current14, current15;
	//Temperature Numbers
	static double temp0, temp1, temp2, temp3, temp4, temp5, temp6;
	//Temperature and Current Numbers names are to be equal to which port 
			
	//Drive Motors
	static WPI_TalonSRX rightBackDrive = new WPI_TalonSRX(16);
	static WPI_TalonSRX rightMiddleDrive = new WPI_TalonSRX(1);
	static WPI_TalonSRX rightFrontDrive = new WPI_TalonSRX(2);
	static WPI_TalonSRX leftBackDrive = new WPI_TalonSRX(15);
	static WPI_TalonSRX leftMiddleDrive = new WPI_TalonSRX(14);
	static WPI_TalonSRX leftFrontDrive = new WPI_TalonSRX(13);
	
	//Motor to move elevator up
	public static final WPI_TalonSRX elevator = new WPI_TalonSRX(4);
	
	static WPI_TalonSRX leftIntake = new WPI_TalonSRX(9);
	static WPI_TalonSRX rightIntake = new WPI_TalonSRX(5);
	
	//slide limits for elevator
	public static final int slideBeforeTopLimit = 0; //TODO
	public static final DigitalInput slideBottomLimit = new DigitalInput(1);
	
	//joystick
	public static final Joystick driveStick = new Joystick(0);
	public static final Joystick opStick = new Joystick(1);
	
	//Encoders
	public static final Encoder leftEncoder = new Encoder(0,1);
	public static final Encoder rightEncoder = new Encoder(1,0);
	public static final Encoder slideEncoder = new Encoder(1,1);

	//Solenoids
	public static Solenoid RightFrontSolenoid = new Solenoid(1);
	public static Solenoid RightBackSolenoid = new Solenoid(2);
	public static Solenoid LeftFrontSolenoid = new Solenoid(3);
	public static Solenoid LeftBackSolenoid = new Solenoid(4);
	
	//the buttons for the robot
	public static int RightRampDown = 0; //TODO
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
	
	//speed for lowering the slide
	public static final double slideLoweringSpeed = -0.3; //TODO
	
	//speed for raising the slide
	public static final double slideRaisingSpeed = 0.3; //TODO
	
	//speed for mainting the position of the slide
	public static final double slideHoverSpeed = 0; //TODO
}
