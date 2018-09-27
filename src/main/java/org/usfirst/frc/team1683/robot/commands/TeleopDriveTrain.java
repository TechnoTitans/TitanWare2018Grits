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
		public void execute() {
		leftJoystick.getX(Hand.kLeft);
		rightJoystick.getY(Hand.kRight);
		leftJoystick.getZ();
		rightJoystick.getZ();
		Robot.drive.set(0.5,0.5);
		}
		Button button1 = new JoystickButton(leftJoystick, 1),
		button2 = new JoystickButton(leftJoystick, 2),
		button3 = new JoystickButton(rightJoystick, 3),
		button4 = new JoystickButton(rightJoystick, 4),
		button5 = new JoystickButton(rightJoystick, 5),
		button6 = new JoystickButton(leftJoystick, 6);
		
		public void OI() {
		button4.whileHeld(new Raise());
		button5.whileHeld(new Lower());
		button6.whileHeld(new Tilt());
		}
	
		
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
