package org.usfirst.frc.team2643.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Elevator {
	
	WPI_TalonSRX masterElevator;
	WPI_TalonSRX slaveElevator;
	
	//private double elevatorIncrease;
	public Elevator(WPI_TalonSRX elevator1, WPI_TalonSRX elevator2) {
		masterElevator = elevator1;
		slaveElevator = elevator2;
		
		slaveElevator.follow(masterElevator);
		
		currentLimit();
		
		masterElevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 20);
		
		defaultPIDLSMotor();
	}
	
	public void currentLimit() {
		masterElevator.configPeakCurrentDuration(150, 0);
		masterElevator.configPeakCurrentLimit(40, 0);
		masterElevator.configContinuousCurrentLimit(35, 0);
		masterElevator.enableCurrentLimit(true);
	}
	
	public void dropElevator()
	{
		if(!RobotMap.elevatorLimitSwitch.get()) {
			masterElevator.set(0);
			resetEncoder();
		}
		else{
			
			masterElevator.set(-0.4);
		}
		
	}

	public void resetEncoder() {
		masterElevator.getSensorCollection().setQuadraturePosition(0, 20);
	}
	
	public void defaultPIDLSMotor()
	{
		masterElevator.setSensorPhase(true);
		masterElevator.selectProfileSlot(RobotMap.defaultPID, 0);
		masterElevator.config_kF(0, RobotMap.PIDF, 20);
		masterElevator.config_kP(0, RobotMap.PIDP, 20);
		masterElevator.config_kI(0, RobotMap.PIDI, 20);
		masterElevator.config_kD(0, RobotMap.PIDD, 20);
		masterElevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 20);
	}
	
//	public void buttonPosControl(int buttonUp, int buttonDown) {
//		masterElevator.set(ControlMode.Position, RobotMap.encoderTick);
//		
//		elevatorIncrease = (RobotMap.opBoard.getThrottle() + 1)/2;
//		
//		if(RobotMap.opBoard.getRawButton(buttonDown)) {
//			if(RobotMap.encoderTick<0) {
//				RobotMap.encoderTick = 0;
//			}
//			else {
//				RobotMap.encoderTick -= 46 + (46*elevatorIncrease);
//			}
//		}
//		else if(RobotMap.opBoard.getRawButton(buttonUp)) {
//			if(RobotMap.encoderTick>RobotMap.maxEncoderValue) {
//				RobotMap.encoderTick = RobotMap.maxEncoderValue;
//			}
//			else {
//				RobotMap.encoderTick += 46 + (46*elevatorIncrease);
//			}
//		}
//	}
	
	public void percentOutputControl() {
		if(RobotMap.opBoard.getRawButton(RobotMap.elevatorUp)) {
			RobotMap.elevator1.set(0.7);
		}
		else if(RobotMap.opBoard.getRawButton(RobotMap.elevatorDown)) {
			RobotMap.elevator1.set(-0.3);
		}
		else {
			RobotMap.elevator1.set(0);
		}
	}
	
}
