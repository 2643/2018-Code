package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;

public class RobotMap {

	//boolean that tells if the motion to released the intake arms has been done
	public static boolean armsReleased = false;
	
	//for the autonomous state machine
	//DO NOT TOUCH THIS
	static int state = 0;
	
	//distance from the starting position to the auto line in encoder ticks
	static int autoLineDistance = 0; //TODO
	
	//the speed for the robot during autonomous 
	public static double autoSpeed = 0; //TODO
	
	//encoder ticks it takes for the robot to turn 90 degrees
	public static int ticksTo90 = 0; //TODO
	 
	//makes it so that it only prints statements when debug is true
	public static boolean debug = false;
	
	//the distance in encoder ticks from the starting position to the field switch
	public static int ticksToSwitch = 0; //TODO
	
	//encoder ticks for the switch elevator height from bottom limit switch
	public static int ticksToSwitchHeight = 0; //TODO
	
	//encoder ticks for slightly past the switch
	public static int ticksToPassSwitch = 0; //TODO
	
	//encoder ticks for the length of the field switch
	public static int ticksForLengthOfSwitch = 0; //TODO
	
	//the state integer for the switch statement in turnRight()
	public static int turnRightState = 1;
	
	//the state integer for the swtich statement in turnLeft()
	public static int turnLeftState = 1;
	
	//the variable that indicates when the robot has finished turning
	public static boolean finishedTurning = false;
	
	//the state integer for all of the auto programs
	public static int autoProgramState = 1;
	
	//Drive motors
	public static WPI_TalonSRX t1 = new WPI_TalonSRX(1);
	public static WPI_TalonSRX t2 = new WPI_TalonSRX(2);
	public static WPI_TalonSRX t3 = new WPI_TalonSRX(3);
	public static WPI_TalonSRX t4 = new WPI_TalonSRX(4);
	public static WPI_TalonSRX t5 = new WPI_TalonSRX(5);
	public static WPI_TalonSRX t6 = new WPI_TalonSRX(6);
	
	//Motor to move elevator up
	//weight of the elevator lets it drop
	public static WPI_TalonSRX s1 = new WPI_TalonSRX(7);
	
	
	//slid limit for elevator
	public static DigitalInput slideLimit = new DigitalInput(1);
	
	//joystick
	public static Joystick stick = new Joystick(0);
	
	//Encoders
	public static Encoder leftEncoder = new Encoder(0,1);
	public static Encoder rightEncoder = new Encoder(1,0);
	public static Encoder slideEncoder = new Encoder(1,1);
	
}
