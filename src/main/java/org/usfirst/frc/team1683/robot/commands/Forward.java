// Do not make this a negative

package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1683.robot.Robot;

public class Forward extends Command {
    private double distance;
    private double desiredSpeed;
    private double startSpeed = 0.2;
    // private double difference = desiredSpeed - startSpeed;
    private double speed = startSpeed;
    private double speedPerDistance = 0.05;
    private boolean accelerate = true;
    private boolean decelerate = false;
    private final double kP = 0.05; // error for angle
    private double previousEncoder = 0;

    private Timer minTime;

    public Forward(double forwardNum, double speed, double acceleration) {
        requires(Robot.drive);
        this.distance = forwardNum;
        this.desiredSpeed = speed;
        this.speedPerDistance = acceleration;
        minTime = new Timer();
    }

    public Forward(double forwardNum, double speed) {
        this(forwardNum, speed, 0);
	}

	@Override
    protected void initialize() {
        Robot.gyro.reset();
        Robot.drive.resetEncoders();
        SmartDashboard.putNumber("Started forward", distance);
        SmartDashboard.putBoolean("Is finished?", isFinished());
        minTime.start();
    }

    private double distanceToAccelerate = 0; //number of ticks to increase to maximum speed
    @Override
    protected void execute() {
        speed = desiredSpeed;
        /*if (speed >= desiredSpeed){
            accelerate = false;
        }

        double delta = Robot.drive.getLeftEncoder().getDistance() - previousEncoder;
        if (distance - Robot.drive.getLeftEncoder().getDistance() <= distanceToAccelerate){
            decelerate = true;
        }

        if (decelerate) {
            speed -= speedPerDistance * delta;
        } else if (accelerate){
            speed += speedPerDistance * delta;
            distanceToAccelerate += delta;
        }
        previousEncoder = Robot.drive.getLeftEncoder().getDistance();*/
        SmartDashboard.putNumber("Speed", speed);
        double error = Robot.gyro.getAngle();
        error *= kP;
        SmartDashboard.putNumber("Error", error);
        Robot.drive.set(speed - error, speed + error);
    }

    @Override
    protected boolean isFinished() {
        return minTime.get() > 0.2 && (Robot.drive.getLeftEncoder().getDistance() > distance ||
              Robot.drive.getRightEncoder().getDistance() > distance);
    }

    @Override
    protected void end() {
        Robot.drive.stop();
        decelerate = false;
        accelerate = true;
    }

    @Override
    protected void interrupted() {
      end();
    }
}
