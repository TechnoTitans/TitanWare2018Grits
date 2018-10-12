package org.usfirst.frc.team1683.robot.subsystems;

import org.usfirst.frc.team1683.robot.Robot;
import org.usfirst.frc.team1683.robot.motor.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem{
	private TalonSRX elevatorTalon;
	
public Elevator(TalonSRX elevatorTalon) {
	this.elevatorTalon = elevatorTalon;
}
	public void moveUp(double speed) {
		// if the speed is more than 0, and the top limit switch is not pressed
		if(speed > 0.0 && !Robot.limitSwitchTop.get()) {
			elevatorTalon.set(speed);
		} else if (speed < 0.0 && !Robot.limitSwitchBottom.get()) {
			elevatorTalon.set(speed);
		}
		
	}
//	public void moveDown(double speed) {
//		elevatorTalon.set(-speed);
//	}
	
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
 
}
