package org.usfirst.frc.team1683.robot.sensors;

public interface Encoder {

	public double getDistance();
	
	public double getSpeed();

	public void reset();
}
