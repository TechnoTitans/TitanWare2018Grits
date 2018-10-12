package org.usfirst.frc.team1683.robot.commands;

import org.usfirst.frc.team1683.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class Shake extends TimedCommand {
	
	public Shake() {
		super(0.3);
		requires(Robot.grabber);
		
	}
	public void execute() {
		// todo implement out in command group thing not even in this file lmao
		// this use to be
//		Robot.grabbeLeft.set(-0.5);
//		Robot.grabbeRight.set(-.5);
	}

	public void end() {
		Robot.grabber.set(0);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}


}
