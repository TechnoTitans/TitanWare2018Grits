package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1683.robot.Robot;

public class PIDTurn extends Command {

    private double kP = 0.05;
    private double kI = 0;
    private double kD = 0.06;
    private double previousError = 0;
    private double currentError = 0;
    private double integralError = 0;
    private double angle;

    public PIDTurn (double angle) {
        this.angle = angle;

    }

    public void execute(){
        double currentAngle = Robot.gyro.getAngle();
        currentError = angle - currentAngle;
        integralError += currentError;
        double speed = kP * currentError + kI * integralError + kD * (currentError - previousError);
        Robot.drive.setLeft(speed);
        Robot.drive.setRight(-speed);
        previousError = currentError;

    }

    @Override
    protected boolean isFinished() {
        return angle > 0 ? Robot.gyro.getAngle() >= angle : Robot.gyro.getAngle() <= angle;
    }

    public void end(){
        Robot.drive.stop();
    }


}
