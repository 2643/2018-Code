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
	//the speed for the robot during autonomous 
	public static double autoSpeed = 0; //TODO
	
	//makes it so that it only prints statements when debug is true
	public static boolean debug = false;
	
	//boolean that tells if the motion to released the intake arms has been done
	public static boolean armsReleased = false;
	
	//the state integer for all of the auto programs
	public static int autoProgramState = 1;
	
	//the state integer for the switch statement in turnRight()
	public static int turnRightState = 1;
	
	//the state integer for the swtich statement in turnLeft()
	public static int turnLeftState = 1;
	
	//distance from the starting position to the auto line in encoder ticks
	static int autoLineDistance = 0; //TODO
	
	//encoder ticks it takes for the robot to turn 90 degrees
	public static int ticksTo90 = 0; //TODO
	
	//the distance in encoder ticks from the starting position to the field switch
	public static int ticksToSwitch = 0; //TODO
	
	//encoder ticks for the switch elevator height from bottom limit switch
	public static int ticksToSwitchHeight = 0; //TODO
	
	//encoder ticks for slightly past the switch
	public static int ticksToPassSwitch = 0; //TODO
	
	//encoder ticks for the length of the field switch
	public static int ticksForLengthOfSwitch = 0; //TODO
	
	//the variable that indicates when the robot has finished turning
	public static boolean finishedTurning = false;
	
	
	//speed for lowering the slide
	public static double slideLoweringSpeed = -0.3; //TODO
	
	//speed for raising the slide
	public static double slideRaisingSpeed = 0.3; //TODO
	
	//speed for mainting the position of the slide
	public static double slideHoverSpeed = 0; //TODO
}
