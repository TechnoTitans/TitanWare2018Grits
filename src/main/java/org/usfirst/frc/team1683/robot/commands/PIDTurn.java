package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1683.robot.Robot;
import org.usfirst.frc.team1683.robot.motor.Filter;

public class PIDTurn extends PIDCommand {
    private double angle;
    private Filter filter = new Filter(1);

    private static final double ANGLE_TOLERANCE = 1;

    public PIDTurn(double angle) {
        this(angle, 0.4);
    }
    
    public PIDTurn (double angle, double speed) {
        super(0.051, 0, 0.18);
        this.getPIDController().disable();
        this.angle = angle;
        this.setSetpoint(angle);
        this.getPIDController().setAbsoluteTolerance(ANGLE_TOLERANCE);
        this.getPIDController().setOutputRange(-speed, speed);
    }

    public void initialize() {
        Robot.gyro.reset();
        this.getPIDController().enable();
    }


    @Override
    protected boolean isFinished() {
        return (angle > 0 ? Robot.gyro.getAngle() >= angle - ANGLE_TOLERANCE : Robot.gyro.getAngle() <= angle + ANGLE_TOLERANCE) || this.getPIDController().onTarget();
    }

    public void end() {
        this.getPIDController().disable();
        Robot.drive.stop();
    }


    @Override
    protected double returnPIDInput() {
        return Robot.gyro.getAngle();
    }

    @Override
    protected void usePIDOutput(double output) {
        if (output < 0 && angle > 0 || output > 0 && angle < 0) Robot.drive.stop();
        else {
            filter.update(output);
            double val = filter.getValue();
            Robot.drive.set(val, -val);
        }
    }
}
