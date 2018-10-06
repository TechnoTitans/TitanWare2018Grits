package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1683.robot.Robot;

public class Turn extends Command {
  private final double increasePerDegree = .005;
  private final double minSpeed = .35;
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

  private double getTargetAngle() {
    return turnAngle - Math.signum(turnAngle) * (30*turnSpeed);
  }

  @Override
  protected void execute() {
    double speed;
    double gyro = Math.abs(Robot.gyro.getAngle());
    SmartDashboard.putNumber("Turn gyro (abs)", gyro);
    SmartDashboard.putNumber("Target angle", getTargetAngle());
    if (gyro < Math.abs(getTargetAngle()/2)) {
      speed = minSpeed + increasePerDegree * gyro;
    } else {
      speed = minSpeed + increasePerDegree * (Math.abs(getTargetAngle()) - gyro);
    }
    if (speed > turnSpeed) {
      speed = turnSpeed;
    }
    Robot.drive.turnInPlace(turnAngle > 0, speed);
    SmartDashboard.putNumber("Turn speed", speed);
  }

  @Override
  protected boolean isFinished() {
    if (timeSinceInitialized() < 0.2) return false;
    if (turnAngle > 0) {
      return Robot.gyro.getAngle() > getTargetAngle();
    } else {
      return Robot.gyro.getAngle() < getTargetAngle();
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
