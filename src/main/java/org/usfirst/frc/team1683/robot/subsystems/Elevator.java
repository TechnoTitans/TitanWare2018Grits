package org.usfirst.frc.team1683.robot.subsystems;

import org.usfirst.frc.team1683.robot.Robot;
import org.usfirst.frc.team1683.robot.motor.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem{
	private TalonSRX elevatorTalon;

	private boolean override = false;
	
public Elevator(TalonSRX elevatorTalon) {
	this.elevatorTalon = elevatorTalon;
}
	public void moveUp(double speed) {
		// if the speed is more than 0, and the top limit switch is not pressed
		if((speed > 0.01 && !Robot.limitSwitchTop.get()) || override) {
			elevatorTalon.set(speed);
		} else if (speed < -0.01 && !Robot.limitSwitchBottom.get()) {
			elevatorTalon.set(speed);
		} else {
			elevatorTalon.set(0);
		}
		
	}
//	public void moveDown(double speed) {
//		elevatorTalon.set(-speed);
//	}
	
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	public void toggleLimitSwitchOverride() {
		override = !override;
		SmartDashboard.putBoolean("Override Limit Switch", override);
	}
 
}
