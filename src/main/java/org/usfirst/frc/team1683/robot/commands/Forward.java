// Do not make this a negative

package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1683.robot.Robot;

public class Forwards extends Command {
    private double distance;
    private double desiredSpeed;
    private double startSpeed = 0.2;
    private double difference = desiredSpeed - startSpeed;
    private double speed = startSpeed;
    private double speedPerDistance = 1/10;
    private boolean accelerate = true;
    private boolean decelerate = false;
    private final double kP = 0.05;
    public Forwards(double forwardNum, double speed) {
        requires(Robot.drive);
        this.distance = forwardNum;
        this.desiredSpeed = speed;
    }

    @Override
    protected void initialize() {
        Robot.gyro.reset();
        Robot.drive.resetEncoders();
    }
    private double previousEncoder = Robot.drive.getLeftEncoder().getDistance();
    private double distanceToAccelerate = 0;
    @Override
    protected void execute() {
        if (speed >= desiredSpeed){
            accelerate = false;
        }

        if (distance - Robot.drive.getLeftEncoder().getDistance() <= distanceToAccelerate){
            decelerate = true;
        }

        if (Robot.drive.getLeftEncoder().getDistance() - previousEncoder >= 1){
            if (accelerate){
                speed += speedPerDistance * (difference);
                distanceToAccelerate += 1;
            } else if (decelerate){
                speed -= speedPerDistance * (difference);
            }
            previousEncoder = Robot.drive.getLeftEncoder().getDistance();
        }

        double error = Robot.gyro.getAngle();
        error *= kP;
        Robot.drive.set(speed - error, speed + error);

    }

    @Override
    protected boolean isFinished() {
        return Robot.drive.getLeftEncoder().getDistance() > distance ||
              Robot.drive.getRightEncoder().getDistance() > distance;
    }

    @Override
    protected void end() {
        Robot.drive.stop();
        decelerate = false;
    }

    @Override
    protected void interrupted() {
      end();
    }
}
