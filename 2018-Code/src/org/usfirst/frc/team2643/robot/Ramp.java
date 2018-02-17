package org.usfirst.frc.team2643.robot;

public class Ramp
{
	private final boolean on = true;
	private final boolean off = false;
	private boolean releasedRamps = false;
	private boolean extendLeftPistons = false;
	private boolean extendRightPistons = false;
	
	/**
	 * Retracts pistons for deploying ramps
	 */
	public void retractRamps()
	{
		RobotMap.rampReleaseSolenoid.set(off);
		releasedRamps = false;
	}
	
	/**
	 * drops the ramps
	 */
	public void deployRamps()
	{
		RobotMap.rampReleaseSolenoid.set(on);
		releasedRamps = true;
	}
	
	/**
	 * returns if the ramps are dropped
	 * @return
	 */
	public boolean isRampsReleased()
	{
		return releasedRamps;
	}
	
	/**
	 * deploys left ramp pistons
	 */
	public void deployLeftPistons()
	{
		RobotMap.leftRampSolenoid.set(on);
		extendLeftPistons = true;
	}
	
	/**
	 * deploys right ramp pistons
	 */
	public void deplyRightPistons()
	{
		RobotMap.rightRampSolenoid.set(on);
		extendRightPistons = true;
	}
	
	/**
	 * retracts left ramp pistons
	 */
	public void retractLeftPistons()
	{
		RobotMap.leftRampSolenoid.set(off);
		extendLeftPistons = false;
	}
	
	/**
	 * retracts right ramp pistons
	 */
	public void retractRightPistons()
	{
		RobotMap.rightRampSolenoid.set(off);
		extendRightPistons = false;
	}
	
	/**
	 * returns if the left ramp pistons are deployed
	 * @return
	 */
	public boolean isLeftPistonsExtended()
	{
		return extendLeftPistons;
	}
	
	/**
	 * returns if the right ramp pistons are deployed
	 * @return
	 */
	public boolean isRightPistonsExtended() 
	{
		return extendRightPistons;
	}
}
