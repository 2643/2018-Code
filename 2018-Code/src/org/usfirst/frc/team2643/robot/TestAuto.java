package org.usfirst.frc.team2643.robot;

public class TestAuto {
	public void moveAuto() {
		Drive drive = new Drive (null, null, null, null);
		int programState = 0;
	
		switch(programState) {
		case 0:
		{
		if(RobotMap.time.get() < 0.5) {
			RobotMap.leftIntake.set(-0.5);
			}
		programState++;
		break;
		}
		case 1:
		{
			while(drive.getLeftEncoder()<EnvironmentVariables.autoLineDistance) {
				System.out.println(drive.getLeftEncoder());
				
			}
		}
		
		}
	}
}
