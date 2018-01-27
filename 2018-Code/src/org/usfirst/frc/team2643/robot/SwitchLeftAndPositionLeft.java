package org.usfirst.frc.team2643.robot;

public class SwitchLeftAndPositionLeft {
	
	
	public static int autoProgramState = 1;
	
	/**
	 * This is the auto routine that runs when our robot is on the left and our switch is on the left. 
	 * Case 1: the robot will move forward until right next to the switch
	 * Case 2: the robot will rotate ninety degrees right to face the switch
	 * Case 3: the robot will drop the cube on the switch
	 */
	public static void runPeriodic(){
		
		switch(autoProgramState){
		
			//the robot will move forward until right next to the switch
			case 1:
				if(RobotMap.DEBUG){
					System.out.println("First Case for SwitchLeftAndPositionLeft");
					System.out.println("Robot will move forward until at the switch");
				}
				
				//the robot will move forward until it reaches the switch
				if(RobotMap.leftEncoder.get() < EnvironmentVariables.ticksToSwitch 
						&& RobotMap.rightEncoder.get() < EnvironmentVariables.ticksToSwitch)
				{
					RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
				}
				else
				{
					autoProgramState = 2;
				}
				break;
				
			//the robot will turn ninety degrees right to face the switch
			case 2:
				if(RobotMap.DEBUG){
					System.out.println("Second Case for SwitchLeftAndPositionLeft");
					System.out.println("Robot will turn ninety degrees right to face the switch");
				}
				
				//the robot will turn ninety degrees right to face the switch
				if(!AutoState.turnInitialized)
				{
					RobotMovementMethods.setUpTurn(EnvironmentVariables.ticksTo90);
				}
				else
				{
					boolean isFinished = RobotMovementMethods.executeTurn();
					if(isFinished)
					{
						RobotMovementMethods.finishTurn();
						autoProgramState = 3;
					}
				}
				break;
			
			//the robot will drop the cube on the switch
			case 3: 
				//TODO drop the cube onto the switch
				break;
		}
	}
}
