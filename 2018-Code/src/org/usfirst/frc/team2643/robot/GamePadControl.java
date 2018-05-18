package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class GamePadControl extends Robot{
	WPI_TalonSRX elevator;
	Joystick opStick;
	public GamePadControl(WPI_TalonSRX liftMotor, Joystick stick){
		elevator = liftMotor;
		opStick = stick;
		RobotMap.elevator2.follow(elevator);
	}
	double elevatorIncrease;
	
	public void gamepadElevator() {
		elevator.set(ControlMode.Position, RobotMap.encoderTick);
		boolean buttonUp = opStick.getRawButton(1);
		boolean buttonDown = opStick.getRawButton(2);
		elevatorIncrease = opStick.getRawAxis(1);
		
		if(buttonDown) {
			if(RobotMap.encoderTick<0) {
				RobotMap.encoderTick = 0;
			}
			else {
				RobotMap.encoderTick -= 46 + (46*elevatorIncrease);
			}
		}
		else if(buttonUp) {
			if(RobotMap.encoderTick>RobotMap.maxEncoderValue) {
				RobotMap.encoderTick = RobotMap.maxEncoderValue;
			}
			else {
				RobotMap.encoderTick += 46 + (46*elevatorIncrease);
			}
		}
	}
	
	public void gamepadIntake() {
		intake.setSpeedLeft(opStick.getRawAxis(2));
		intake.setSpeedRight(opStick.getRawAxis(3));
	}
	
	public void gamepadIncline() {
		if(opStick.getRawButton(3)) {
			angleIntake.angleIntake(0.5);
		}
		else if(opStick.getRawButton(4)) {
			angleIntake.angleIntake(-.3);
		}
		else {
			angleIntake.angleIntake(0);
		}
		
	}
}
