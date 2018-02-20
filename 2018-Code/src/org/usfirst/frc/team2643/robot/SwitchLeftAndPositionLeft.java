package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class SwitchLeftAndPositionLeft {


	/**
	 * This is the auto routine that runs when our robot is on the left and our switch is on the left. 
	 * Case 1: the robot will move forward until right next to the switch
	 * Case 2: the robot will rotate ninety degrees right to face the switch
	 * Case 3: the robot will drop the cube on the switch
	 */
	public static void runPeriodic(){
 
		System.out.println("Left Encoder: " + Robot.drive.getLeftEncoder() + "    Right Encoder: "
				+ Robot.drive.getRightEncoder() + "    Elevator: " + Robot.elevator.getEncoder());
				
		
		switch(AutoState.state){
		
			case 0:
			{
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionMiddle Case 0: Robot will release arms");
				}
				
				AutoState.state = Robot.intake.autoIntake(AutoState.state, RobotMap.inputCubeSpeed, 0.5);
				
				break;
			}
			case 1:
			{
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 1: Robot go forward until next to the switch");
				}
				int moveGoal = EnvironmentVariables.ticksToMiddleOfSwitch;
				AutoState.state = Robot.drive.autoMove(AutoState.state, moveGoal);
				break;
			}
			case 2:
			{
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 2: Turn right to face the switch");
				}
				
				AutoState.state = Robot.drive.autoTurn(AutoState.state, 90);
				
				break;
			}
			case 3: 
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 3: Robot will drop the cube onto the switch.");
				}
				
			//	AutoState.state = Robot.elevator.autoElevate(AutoState.state, 500);
				AutoState.state++;
				break;
			
			case 4:
			{	
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 4: Outtake the cube");
				}

				AutoState.state = Robot.intake.autoIntake(AutoState.state, RobotMap.outputCubeSpeed, 1);
				break;
			}
			case 5:
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 5: Program is done");
				}
				break;
		}
	}
}
