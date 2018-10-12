package org.usfirst.frc.team1683.robot.commands;

import org.usfirst.frc.team1683.robot.HWR;
import org.usfirst.frc.team1683.robot.Robot;
import org.usfirst.frc.team1683.robot.motor.Filter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopDriveTrain extends Command {
	private Joystick leftJoystick = new Joystick(HWR.LEFT_JOYSTICK);
	private Joystick rightJoystick = new Joystick(HWR.RIGHT_JOYSTICK);
	private Joystick auxJoystick = new Joystick(HWR.AUX_JOYSTICK);

	private Filter rightFilter, leftFilter, elevatorFilter;
	public void initialize() {
		rightFilter = new Filter(0.12);
		leftFilter = new Filter(0.1);
		elevatorFilter = new Filter(0.1);
	
	}

	public void execute() {
		rightFilter.update(-rightJoystick.getY());
		leftFilter.update(-leftJoystick.getY());
		elevatorFilter.update(-auxJoystick.getY());
		
		
		Robot.drive.set(leftFilter.getValue(), rightFilter.getValue());
		Robot.elevator.moveUp(elevatorFilter.getValue());
		SmartDashboard.putNumber("pov", auxJoystick.getPOV());
	}

	Button button1 = new JoystickButton(leftJoystick,1),
		   button2 = new JoystickButton(leftJoystick,2),
		   button3 = new JoystickButton(rightJoystick, 3),
	       button4 = new JoystickButton(auxJoystick, 4),
	       auxBtn6 = new JoystickButton(auxJoystick, 6),
	       auxBtn3 = new JoystickButton(auxJoystick, 3),
	       button6 = new JoystickButton(leftJoystick,6);
	
	
	public TeleopDriveTrain() {
//		button4.whenPressed(new Grab());
		button4.whileHeld(new Grab()); // when button is released, should cancel command
		auxBtn6.whileHeld(new Release());
		auxBtn3.whenPressed(new Shake());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
