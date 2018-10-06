/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team1683.robot;

import org.usfirst.frc.team1683.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1683.robot.subsystems.Elevator;
import org.usfirst.frc.team1683.robot.subsystems.Grabber;
import org.usfirst.frc.team1683.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1683.robot.motor.TalonSRX;
import org.usfirst.frc.team1683.robot.sensors.*;
import org.usfirst.frc.team1683.robot.automation.Priority;
import org.usfirst.frc.team1683.robot.automation.Target;
import org.usfirst.frc.team1683.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final boolean LEFT_REVERSE = false;
	public static final boolean RIGHT_REVERSE = true;
	
	public static Gyro gyro;
	public static DigitalInput limitSwitchBottom;
	
	public static TalonSRX grabberLeft, grabberRight, elevatorTalon,
		leftETalonSRX, rightETalonSRX, grabberTilt;

	public static DriveTrain drive;
	public static Grabber grabber;
	public static Elevator elevator;

	private SendableChooser<Object> autoChooser;
	
	private static final double INCHES_PER_PULSE = 0.00475; // configure
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		gyro = new AnalogGyro(HWR.GYRO);
		limitSwitchBottom = new DigitalInput(HWR.LIMIT_SWITCH_BOTTOM);
		
		grabberLeft = new TalonSRX(HWR.GRABBER_LEFT, false);
		grabberRight = new TalonSRX(HWR.GRABBER_RIGHT, false);
		grabberTilt = new TalonSRX(HWR.GRABBER_TILT, false);
		elevatorTalon = new TalonSRX(HWR.ELEVATOR_MAIN, false);
		elevatorTalon.setEncoder(new QuadEncoder(elevatorTalon, 0.00112, true));
		leftETalonSRX = new TalonSRX(HWR.LEFT_DRIVE_TRAIN_FRONT, LEFT_REVERSE);
		rightETalonSRX = new TalonSRX(HWR.RIGHT_DRIVE_TRAIN_FRONT, RIGHT_REVERSE);
		leftETalonSRX.setEncoder(new QuadEncoder(leftETalonSRX, INCHES_PER_PULSE, true));
		rightETalonSRX.setEncoder(new QuadEncoder(rightETalonSRX, INCHES_PER_PULSE, true));
		
		TalonSRX leftFollow1 = new TalonSRX(HWR.LEFT_DRIVE_TRAIN_MIDDLE, LEFT_REVERSE),
				 leftFollow2 = new TalonSRX(HWR.LEFT_DRIVE_TRAIN_BACK, LEFT_REVERSE),
				 rightFollow1 = new TalonSRX(HWR.RIGHT_DRIVE_TRAIN_MIDDLE, RIGHT_REVERSE),
				 rightFollow2 = new TalonSRX(HWR.RIGHT_DRIVE_TRAIN_BACK, RIGHT_REVERSE);
		
		leftFollow1.follow(leftETalonSRX);
		leftFollow2.follow(leftETalonSRX);
		rightFollow1.follow(rightETalonSRX);
		rightFollow2.follow(rightETalonSRX);
		
		leftETalonSRX.setupCurrentLimiting();
		rightETalonSRX.setupCurrentLimiting();
		leftFollow1.setupCurrentLimiting();
		leftFollow2.setupCurrentLimiting();
		rightFollow1.setupCurrentLimiting();
		rightFollow2.setupCurrentLimiting();
		
		drive = new TankDrive(leftETalonSRX, rightETalonSRX, gyro);
		grabber = new Grabber(grabberLeft, grabberRight, grabberTilt);
		elevator = new Elevator(elevatorTalon);

		SmartDashboard.putData((AnalogGyro) gyro);

		Target[] priorities = new Target[] { Target.SAME_SWITCH, Target.OPP_SWITCH };
		autoChooser = new SendableChooser<Object>();

		autoChooser.addDefault("Go forward", new Forward(100, 0.4, 0));
		autoChooser.addObject("Start right", new Priority(priorities, 'R'));
		autoChooser.addObject("Switch from left", new Priority(priorities, 'L'));
		autoChooser.addObject("Switch from middle", new Priority(priorities, 'M') );

		SmartDashboard.putData(autoChooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	/**
	 * Called once at the beginning of autonomous
	 */
	@Override
	public void autonomousInit() {
		Object o = autoChooser.getSelected();
		if (o instanceof Forward) {
			Command c = (Command) o;
			c.start();
		}
		else if (o instanceof Priority) {
			Priority p = (Priority) o;
			p.getTodo().start();
		}

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		// Before we used Command.runAllCommands(); This is the wpilib equivalent
		Scheduler.getInstance().run();
		
		// debug
		SmartDashboard.putNumber("Gyro angle", gyro.getAngle());
		SmartDashboard.putNumber("Left Encoder Value", drive.getLeftEncoder().getDistance());
		SmartDashboard.putNumber("Right Encoder Value", drive.getRightEncoder().getDistance());
	}

	@Override
	public void teleopInit() {
		new TeleopDriveTrain().start();
	}

	/** 
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		

		// debug
		SmartDashboard.putNumber("Gyro angle", gyro.getAngle());
		SmartDashboard.putNumber("Left Encoder Value", drive.getLeftEncoder().getDistance());
		SmartDashboard.putNumber("Right Encoder Value", drive.getRightEncoder().getDistance());
		SmartDashboard.putBoolean("Limit Switch", limitSwitchBottom.get());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
