package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandGroupAlpha extends CommandGroup {
  public CommandGroupAlpha() {
    addSequential(new Forwards(5));
    addSequential(new Turn(90, .5));
    addSequential(new Forwards(2.5));
    addSequential(new Turn(-90, .5));
    addSequential(new Forwards(2.5));
    addSequential(new Turn(-90, .5));
    addSequential(new Forwards(2.5));
    addSequential(new Turn(90, .5));
    addSequential(new Backwards(4096));
  }
}
