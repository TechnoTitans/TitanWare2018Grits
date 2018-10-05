package org.usfirst.frc.team1683.robot.commands;

import org.usfirst.frc.team1683.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class Release extends TimedCommand {
	public Release() {
		super(1);
		requires(Robot.grabber);
	}
	
	public void execute() {
		Robot.grabber.moveOut(0.5);
	}

	public void end() {
		Robot.grabber.set(0);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}
