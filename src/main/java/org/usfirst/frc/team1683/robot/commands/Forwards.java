// Do not make this a negative

package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1683.robot.Robot;

public class Forwards extends Command {
  private double forwardNum;
  public Forwards(double forwardNum) {
    requires(Robot.drive);
    this.forwardNum = forwardNum;
  }

  @Override
  protected void initialize() {
    Robot.gyro.reset();
    Robot.drive.resetEncoders();
  }

  @Override
  protected void execute() {
    Robot.drive.set(0.5, 0.5);
  }

  @Override
  protected boolean isFinished() {
    return Robot.drive.getLeftEncoder().getDistance() > forwardNum;
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
