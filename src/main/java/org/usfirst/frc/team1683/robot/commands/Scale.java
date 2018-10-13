/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Scale extends CommandGroup {
  public Scale(boolean right) {
    addSequential(new OutInShake());
    int side = right ? 1 : -1;
    addSequential(new Forward(315.7, 0.6, false));
    addSequential(new Turn(-90 * side, 0.4));
    addSequential(new LiftUp(false));
    addSequential(new Forward(21, 0.3));
    addSequential(new Release());
  }
}
