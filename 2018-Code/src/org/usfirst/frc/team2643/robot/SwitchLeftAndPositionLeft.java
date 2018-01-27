package org.usfirst.frc.team2643.robot;

public class SwitchLeftAndPositionLeft {
	
	/**
	 * This is the auto routine that runs when our robot is on the left and our switch is on the left. 
	 * Case 1: the robot will move forward until right next to the switch
	 * Case 2: the robot will rotate ninety degrees right to face the switch
	 * Case 3: the robot will drop the cube on the switch
	 */
	public static void runPeriodic(){
		
		switch(RobotMap.autoProgramState){
		
			//the robot will move forward until right next to the switch
			case 1:
				if(RobotMap.debug == true){
					System.out.println("First Case for SwitchLeftAndPositionLeft");
					System.out.println("Robot will move forward until at the switch");
				}
				
				//the robot will move forward until it reaches the switch
				if(Robot.leftEncoder.get() < RobotMap.ticksToSwitch 
						&& Robot.rightEncoder.get() < RobotMap.ticksToSwitch)
				{
					Robot.setAll(RobotMap.autoSpeed);
				}
				else
				{
					RobotMap.autoProgramState = 2;
				}
				break;
				
			//the robot will turn ninety degrees right to face the switch
			case 2:
				if(RobotMap.debug == true){
					System.out.println("Second Case for SwitchLeftAndPositionLeft");
					System.out.println("Robot will turn ninety degrees right to face the switch");
				}
				
				//the robot will turn ninety degrees right to face the switch
				if(!RobotMap.finishedTurning)
				{
					RobotMovementMethods.turnRight();
				}
				else
				{
					RobotMap.autoProgramState = 3;
				}
				break;
			
			//the robot will drop the cube on the switch
			case 3: 
				//TODO drop the cube onto the switch
				break;
		}
	}
}
