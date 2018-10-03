package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1683.robot.Robot;
import org.usfirst.frc.team1683.robot.motor.Filter;

public class PIDTurn extends PIDCommand {
    private double angle;
    private Filter filter = new Filter(0.0001);
    public PIDTurn(double angle) {
        this(angle, 0.4);
    }
    
    public PIDTurn (double angle, double speed) {
        super(0.051, 0, 0.18);
        this.angle = angle;
        this.setSetpoint(angle);
        this.getPIDController().setOutputRange(-speed, speed);
    }

    public void initialize() {
        Robot.gyro.reset();
    }


    @Override
    protected boolean isFinished() {
        return angle > 0 ? Robot.gyro.getAngle() >= angle : Robot.gyro.getAngle() <= angle;
    }

    public void end(){
        Robot.drive.stop();
        SmartDashboard.putBoolean("ended turn", true);
        this.getPIDController().disable();
    }


    @Override
    protected double returnPIDInput() {
        return Robot.gyro.getAngle();
    }

    @Override
    protected void usePIDOutput(double output) {
        if (output < 0 && angle > 0 || output > 0 && angle < 0) output = 0;
        filter.update(output);
        Robot.drive.set(filter.getValue(), -filter.getValue());
    }
}
