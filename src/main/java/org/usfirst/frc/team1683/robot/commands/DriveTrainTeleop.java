package org.usfirst.frc.team1683.robot.commands;

import org.usfirst.frc.team1683.robot.HWR;
import org.usfirst.frc.team1683.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainTeleop extends Command {
	private Joystick left = new Joystick(HWR.LEFT_JOYSTICK);
	double getRawAxis() {
		return 0;
	}
	protected boolean isFinished1() {
		// TODO Auto-generated method stub
		return false;
	}
	public void execute() {
		Robot.drive.set(0.5);
		double leftjoy = left.getY();
	}
	private Joystick right = new Joystick(HWR.RIGHT_JOYSTICK); 
	public int getAxisCount() {
		return 0;
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}