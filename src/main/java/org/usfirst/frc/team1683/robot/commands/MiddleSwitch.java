/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleSwitch extends CommandGroup {
  public MiddleSwitch(boolean goRight) {
    addSequential(new OutInShake());
    addSequential(new Forward(25, 0.5, false));
    addSequential(new Turn(45 * (goRight ? 1 : -1), 0.6));
    addParallel(new LiftUp(true));
    addSequential(new Forward(76, 0.5));
    addSequential(new Turn(-45 * (goRight ? 1 : -1), 0.6));
    addSequential(new Forward(45.5, 0.3));
    addSequential(new Release());
  }
}
