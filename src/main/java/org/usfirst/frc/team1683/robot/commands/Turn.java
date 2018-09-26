package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1683.robot.Robot;

public class Turn extends Command {
  private double turnAngle;
  public Turn(double turnAngle) {
    requires(Robot.drive);
    this.turnAngle = turnAngle;
  }

  @Override
  protected void initialize() {
    Robot.gyro.reset();
  }

  @Override
  protected void execute() {
    Robot.drive.turnInPlace(turnAngle > 0, 0.4);
  }

  @Override
  protected boolean isFinished() {
    return Robot.gyro.getAngle() > turnAngle;
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