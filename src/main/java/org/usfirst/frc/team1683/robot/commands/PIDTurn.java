package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1683.robot.Robot;

public class PIDTurn extends PIDCommand {

    private double kP = 0.05;
    private double kI = 0;
    private double kD = 0.3;
    private double previousError = 0;
    private double currentError = 0;
    private double integralError = 0;
    private double angle;

    public PIDTurn (double angle) {
        super(0.051, 0, 0.18);
        this.angle = angle;
        this.setSetpoint(angle);
        this.getPIDController().setOutputRange(-0.4, 0.4);
    }

    public void initialize() {
        Robot.gyro.reset();
    }
//
//    public void execute(){
//        double currentAngle = Robot.gyro.getAngle();
//        currentError = angle - currentAngle;
//        integralError += currentError;
//        previousError = currentError;
//
//    }

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
        Robot.drive.set(output, -output);
    }
}
