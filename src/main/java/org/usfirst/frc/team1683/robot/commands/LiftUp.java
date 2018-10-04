package org.usfirst.frc.team1683.robot.commands;

import org.usfirst.frc.team1683.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftUp extends Command {
	public void execute() {
		Robot.elevatorTalon.set(0.5);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
