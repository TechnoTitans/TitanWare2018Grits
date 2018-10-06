/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OppositeSwitch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public OppositeSwitch(boolean right) {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
    int side = right ? 1 : -1;
    addSequential(new Forward(200, 0.5));
    
    addSequential(new Turn(-90 * side, 0.4));
    addSequential(new Forward(170, 0.5));
    addParallel(new LiftUp(true));
    addSequential(new Turn(-90 * side, 0.4));
    addSequential(new Release());
  }
}
