// Do not make this a negative

package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1683.robot.Robot;

public class Backwards extends Command {
  private double BackwardNum;
  public Backwards(double BackwardNum) {
    requires(Robot.drive);
    this.BackwardNum = BackwardNum;
  }

  @Override
  protected void initialize() {
    Robot.gyro.reset();
    Robot.drive.resetEncoders();
  }

  @Override
  protected void execute() {
    Robot.drive.set(-0.5, -0.5);
  }

  @Override
  protected boolean isFinished() {
    return Robot.drive.getLeftEncoder().getDistance() < -BackwardNum;
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
