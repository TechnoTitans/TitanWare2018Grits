package org.usfirst.frc.team1683.robot.commands;

import org.usfirst.frc.team1683.robot.Robot;
import org.usfirst.frc.team1683.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class Grab extends Command {
	public Grab() {
//		super(1);
		requires(Robot.grabber);
	}


	public void execute() {
		Robot.grabber.moveIn(0.5);
	}
	
	public void end() {
		Robot.grabber.set(0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
