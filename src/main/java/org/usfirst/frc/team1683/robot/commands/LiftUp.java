package org.usfirst.frc.team1683.robot.commands;

import org.usfirst.frc.team1683.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class LiftUp extends TimedCommand {
	public LiftUp(boolean isSwitch) {
		super(1.7);
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
		return isTimedOut();
	}
}
