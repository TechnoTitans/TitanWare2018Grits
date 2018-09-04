package org.usfirst.frc.team1683.robot.motor;

import org.usfirst.frc.team1683.robot.sensors.Encoder;

public interface Motor {

	public void set(double speed);

	public double getPercentSpeed();
	
	public double getSpeed();

	public void stop();

	public void brake();

	public void coast();

	public boolean hasEncoder();

	public Encoder getEncoder();

	// public void setBrakeMode(boolean enable);

	public int getChannel();

	public boolean isReversed();
}
