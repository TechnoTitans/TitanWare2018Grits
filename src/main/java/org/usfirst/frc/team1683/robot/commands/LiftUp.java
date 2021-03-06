package org.usfirst.frc.team1683.robot.commands;

import org.usfirst.frc.team1683.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftUp extends Command {
	private boolean isSwitch;
	public LiftUp(boolean isSwitch) {
		// super(1.7);
		this.isSwitch = isSwitch;
		requires(Robot.elevator);
	}

	public void execute() {
		Robot.elevator.moveUp(0.9);
	}

	public void end() {
		Robot.elevator.moveUp(0);
	}

	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		SmartDashboard.putNumber("Time", timeSinceInitialized());
		if (Robot.limitSwitchTop.get()) return true;
		return isSwitch && timeSinceInitialized() > 1.7;
	}
}
