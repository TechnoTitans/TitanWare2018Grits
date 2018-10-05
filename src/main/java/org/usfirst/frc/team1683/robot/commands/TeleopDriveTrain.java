package org.usfirst.frc.team1683.robot.commands;

import org.usfirst.frc.team1683.robot.HWR;
import org.usfirst.frc.team1683.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopDriveTrain extends Command {
	private Joystick leftJoystick = new Joystick(HWR.LEFT_JOYSTICK);
	private Joystick rightJoystick = new Joystick(HWR.RIGHT_JOYSTICK);
	private Joystick auxJoystick = new Joystick(HWR.AUX_JOYSTICK);
	public void execute() {
			Robot.drive.set(-leftJoystick.getY(), -rightJoystick.getY());
			Robot.elevator.moveUp(-auxJoystick.getY());
	}

	Button button1 = new JoystickButton(leftJoystick,1),
		   button2 = new JoystickButton(leftJoystick,2),
		   button3 = new JoystickButton(rightJoystick, 3),
	       button4 = new JoystickButton(auxJoystick, 4),
	       button5 = new JoystickButton(auxJoystick, 5),
	       button6 = new JoystickButton(leftJoystick,6);
	public TeleopDriveTrain() {
		button4.whenPressed(new Grab());
		button5.whenPressed(new Release());
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
