package org.usfirst.frc.team1683.robot.commands;

import org.usfirst.frc.team1683.robot.HWR;
import org.usfirst.frc.team1683.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopDriveTrain extends Command {
	private Joystick leftJoystick = new Joystick(HWR.LEFT_JOYSTICK);
	private Joystick rightJoystick = new Joystick(HWR.RIGHT_JOYSTICK);
		public void execute() {
		leftJoystick.getX(Hand.kLeft);
		if {
			Robot.drive.set(X/100 * 0.5);
		}
		rightJoystick.getY(Hand.kRight);
		if {
//			Robot.drive.set(Y/100 * 0.5 );
		}
		leftJoystick.getZ();
		rightJoystick.getZ();
		Robot.drive.set(0.5,0.5);
//		Double check on if it is necessary to repeat every left or right Joystick
//		figure out how to input joystick into commands out instead of just finding it, which is get.
		leftJoystick.getThrottle();
		rightJoystick.getThrottle();
//		What is a twist?
		leftJoystick.getTwist();
		rightJoystick.getTwist();
		leftJoystick.getDirectionDegrees();
		rightJoystick.getDirectionDegrees();
		leftJoystick.getDirectionRadians();
		rightJoystick.getDirectionRadians();
		leftJoystick.getMagnitude();
		rightJoystick.getMagnitude();
		
		Button button 1 = new JoystickButton(leftJoy , 1);
		button 2 = new JoystickButton(leftJoy , 2);
		button 3 = new JoystickButton(rightJoy, 3);
		button 4 = new JoystickButton(rightJoy, 4);
		button 5 = new JoystickButton(rightJoy, 5);
		button 6 = new JoystickButton(leftJoy, 6);
		
		button1.whenPressed(new PrepareToGrab());
		button2.whenPressed(new Grab());
		button3.whenPressed(new Release());
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
