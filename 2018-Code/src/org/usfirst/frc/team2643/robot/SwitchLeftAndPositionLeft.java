package org.usfirst.frc.team2643.robot;

import edu.wpi.first.wpilibj.Timer;

public class SwitchLeftAndPositionLeft {


	//state variable for switch statement 
	public static int autoProgramState = 0;
	

	/**
	 * This is the auto routine that runs when our robot is on the left and our switch is on the left. 
	 * Case 1: the robot will move forward until right next to the switch
	 * Case 2: the robot will rotate ninety degrees right to face the switch
	 * Case 3: the robot will drop the cube on the switch
	 */
	public static void runPeriodic(){
 
		System.out.println("Left Encoder: " + Robot.drive.getLeftEncoder() + "    Right Encoder: "
				+ Robot.drive.getRightEncoder() + "    Elevator: " + Robot.elevator.getEncoder());
				
		
		switch(autoProgramState){
		
			case 0:
			{
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionMiddle Case 0: Robot will release arms");
				}

				if(!AutoState.inttaking)
				{
					Robot.intake.setUpIntake(0.5, RobotMap.inputCubeSpeed);
					AutoState.inttaking = true;
				}
				else
				{
					if(Robot.intake.executeIntake()) 
					{
						Robot.intake.finishIntake();
						AutoState.inttaking = false; 
						autoProgramState++; 
					}
				}
				break;
			}
			case 1:
			{
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 1: Robot go forward until next to the switch");
				}

				
				int encoderGoal = EnvironmentVariables.ticksToMiddleOfSwitch;
				if(!AutoState.moving)
				{
					Robot.drive.setUpMove(encoderGoal);
					AutoState.moving = true;
				}
				else if(Robot.drive.executeMove())
				{
					Robot.drive.finishMove();
					AutoState.moving = false;
					autoProgramState++;
				}
				break;
			}
			case 2:
			{
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 2: Turn right to face the switch");
				}

				
				if(!AutoState.turning)
				{
					//Robot.drive.setUpTurn(EnvironmentVariables.ticksTo90);
					Robot.drive.setUpGyroTurn(EnvironmentVariables.degreesTogyro(90));
					AutoState.turning = true;
				}	
				else
				{ 
					if(Robot.drive.executeGyroTurn())
					{
						Robot.drive.finishGyroTurn();
						AutoState.turning = false;
						autoProgramState++;
					}
				}
				break;
			}
			case 3: 
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 3: Robot will drop the cube onto the switch.");
				}
				if(!AutoState.elevating)
				{
					Robot.elevator.setUpElevate(500);
					AutoState.elevating = true;
				}	
				else
				{ 
					if(Robot.elevator.executeElevate())
					{
						Robot.elevator.finishElevate();
						AutoState.elevating = false;
						autoProgramState++;
					}
				}
				break;
			
			case 4:
			{	
				if(RobotMap.DEBUG){
					System.out.println("SwitchLeftAndPositionLeft Case 4: Outtake the cube");
				}
				
				if(!AutoState.inttaking)
				{
					Robot.intake.setUpIntake(1, RobotMap.outputCubeSpeed);
					AutoState.inttaking = true;
				}
				else
				{
					if(Robot.intake.executeIntake()) 
					{
						Robot.intake.finishIntake();
						AutoState.inttaking = false; 
						autoProgramState++; 
					}
				}
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
