package org.usfirst.frc.team1683.robot.subsystems;
	
import org.usfirst.frc.team1683.robot.Robot;
import org.usfirst.frc.team1683.robot.motor.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Grabber extends Subsystem{
   private TalonSRX left;
   private TalonSRX right;
   private TalonSRX tilt;
   
   public Grabber(TalonSRX left, TalonSRX right, TalonSRX tilt) {
		this.left = left;
		this.right = right;
		this.tilt = tilt;
	}

   public void moveIn(double speed) {
	   set(-speed);
   }
   public void moveOut(double speed) {
	   set(speed);
   }
   public void set(double speed) {
    //  if (!Robot.limitSwitchBottom.get()) return;
	   left.set(speed);
	   right.set(-speed);
   }
   
   public void tilt(double speed) {
	   this.tilt.set(speed);
   }
   
  @Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
