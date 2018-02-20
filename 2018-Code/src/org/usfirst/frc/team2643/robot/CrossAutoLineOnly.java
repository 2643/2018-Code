package org.usfirst.frc.team2643.robot; 

import edu.wpi.first.wpilibj.Timer;

public class CrossAutoLineOnly {
	
	public static Timer timer = new Timer();
	public static boolean resetTimer = false;
	
	public static void runPeriodic(){
		
		switch(AutoState.state)
		{
		case 0:
		{
			if(resetTimer == false){
				timer.reset();
				timer.start();
				resetTimer = true;
			}else if(timer.get() < 1){
				Robot.drive.setAllSpeed(0.5);
			}else{
				Robot.drive.stopAllSpeed();
				AutoState.state = 1;
			}
			break;
		}
		case 1:
		{
			break;
		}
		}
	}
}
