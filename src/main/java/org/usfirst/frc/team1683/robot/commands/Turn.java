package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.interfaces.Gyro;

import org.usfirst.frc.team1683.robot.Robot;

public class Turn extends Command {
  private final double increasePerDegree = .01;
  private final double minSpeed = .2;
  private double turnSpeed;
  private double turnAngle;
  public Turn(double turnAngle, double turnSpeed) {
    requires(Robot.drive);
    this.turnAngle = turnAngle;
    this.turnSpeed = turnSpeed;
  }

  @Override
  protected void initialize() {
    Robot.gyro.reset();
  }

  @Override
  protected void execute() {
    double speed;
    double gyro = Math.abs(Robot.gyro.getAngle());
    if (gyro < Math.abs(turnAngle/2)) {
      speed = minSpeed + increasePerDegree * gyro;
    } else {
      speed = minSpeed + increasePerDegree * (Math.abs(turnAngle) - gyro);
    }
    if (speed > turnSpeed) {
      speed = turnSpeed;
    }
    Robot.drive.turnInPlace(true, speed);
  }

  @Override
  protected boolean isFinished() {
    if (turnAngle > 0) {
      return Robot.gyro.getAngle() > turnAngle;
    } else {
      return Robot.gyro.getAngle() < turnAngle;
    }
  }

  @Override
  protected void end() {
    Robot.drive.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}