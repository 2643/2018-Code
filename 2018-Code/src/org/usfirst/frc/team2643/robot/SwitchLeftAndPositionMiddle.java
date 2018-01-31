package org.usfirst.frc.team2643.robot;

public class SwitchLeftAndPositionMiddle {
	public static int autoProgramState = 0;

	/**
	 * Might change this to just going forward and dropping the cube onto the switch
	 * 		Just need to make sure that the robot is not placed in the path of the cube pile
	 */
	public static void runPeriodic(){ 

		switch(autoProgramState){
		
				//the robot will release the arms by going forward and then backwards
				case 0:
					if(RobotMap.DEBUG)
					{
						System.out.println("SwitchLeftAndPositionMiddle Case 0: Robot will attempt to release arms");
					}

					if(!AutoState.armsReleasing)
					{
						RobotMovementMethods.setUpReleaseArms();
						AutoState.armsReleasing = true;
					}
					else
					{
						boolean isFinished = RobotMovementMethods.executeReleaseArms();
						if(isFinished)
						{
							RobotMovementMethods.finishReleaseArms();
							autoProgramState = 1;
							AutoState.armsReleasing = false;

						}
					}
					break;
		
				//the robot will move halfway to the switch
				case 1:
					if(RobotMap.DEBUG)
					{
						System.out.println("SwitchLeftAndPositionMiddle Case 1: Robot moving halfway to switch");
					}
			
					int encoderGoal = EnvironmentVariables.ticksToBeforeSwitch/2;
					if(RobotMap.leftEncoder.get() < encoderGoal
						&& RobotMap.rightEncoder.get() < encoderGoal)
					{
						RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
					}
					else
					{
						autoProgramState = 2;
					}
					break;
			
				//the robot will turn 90 degrees left
				case 2:
					if(RobotMap.DEBUG){
						System.out.println("SwitchLeftAndPositionMiddle Case 2: Robot turning left 90 degrees");
					}
			
					if(!AutoState.turning)
					{
						RobotMovementMethods.setUpTurn(-EnvironmentVariables.ticksTo90);
						AutoState.turning = true;
					}
					else
					{
						boolean isFinished = RobotMovementMethods.executeTurn();
						if(isFinished)
						{
							RobotMovementMethods.finishTurn();
							autoProgramState = 3;
							AutoState.turning = false;
						}
					}
					break;
			
			
				//the robot will move until it almost reaches the left end of the switch
				case 3:
					if(RobotMap.DEBUG)
					{
						System.out.println("SwitchLeftAndPositionMiddle Case 3: Robot will move until it is almost in line with the left end of the switch.");
					}

					int encoderGoal2 = (EnvironmentVariables.ticksWidthOfSwitch)/2;
					if(RobotMap.leftEncoder.get() < encoderGoal2 
						&& RobotMap.rightEncoder.get() < encoderGoal2)
					{
						RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
					}
					else
					{
						autoProgramState = 2;
					}
					break;
			
			
				//the robot will turn 90 degrees right
				case 4:
					if(RobotMap.DEBUG){
						System.out.println("SwitchLeftAndPositionMiddle Case 4: Robot will turn 90 degrees right");
					}

					if(!AutoState.turning)
					{
						RobotMovementMethods.setUpTurn(EnvironmentVariables.ticksTo90);
						AutoState.turning = true;
					}
					else
					{
						boolean isFinished = RobotMovementMethods.executeTurn();
						if(isFinished)
						{
							RobotMovementMethods.finishTurn();
							autoProgramState = 5;
							AutoState.turning = false;
						}
					}
					break;
				
				//Robot will move forward until it reaches the switch
				case 5:
					if(RobotMap.DEBUG)
					{
						System.out.println("SwitchLeftAndPositionMiddle Case 5: Robot will move forward until it reaches the switch");
					}	
				
					int encoderGoal3 = EnvironmentVariables.ticksToBeforeSwitch/2;
					if(RobotMap.leftEncoder.get() < encoderGoal3
							&& RobotMap.rightEncoder.get() < encoderGoal3)
					{	
						RobotMovementMethods.setAll(RobotMap.cruisingSpeed);
					}	
					else
					{
						autoProgramState = 6;
					}
					break;
			
			
				//the robot will drop the cube
				case 6:
					if(RobotMap.DEBUG){	
						System.out.println("SwitchLeftAndPositionMiddle Case 6: Robot will drop the cube");
					}
					autoProgramState = 7;
					//TODO
					break;
			
				//the program is done
				case 7:
					if(RobotMap.DEBUG){
						System.out.println("SwitchLeftAndPositionMiddle Case 7: Program is done.");
					}
					break;
		}
	}
}
