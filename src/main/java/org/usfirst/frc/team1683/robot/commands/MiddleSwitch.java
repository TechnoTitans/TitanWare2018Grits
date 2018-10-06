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
    addSequential(new Forward(120, 0.5));
    addSequential(new Turn(90 * (goRight ? 1 : -1), 0.4));
    addSequential(new Forward(110, 0.5));
    addSequential(new Turn(90 * (goRight ? -1 : 1), 0.4));
    addSequential(new Forward(41, 0.3));
  }
}
