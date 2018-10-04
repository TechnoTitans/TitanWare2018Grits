package org.usfirst.frc.team1683.robot.commands;

import org.usfirst.frc.team1683.robot.Robot;
import org.usfirst.frc.team1683.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.Command;

public class Grab extends Command {
	public void execute() {
		Robot.grabber.moveIn(0.5);
	}
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
