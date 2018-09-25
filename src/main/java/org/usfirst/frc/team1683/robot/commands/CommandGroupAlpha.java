package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandGroupAlpha extends CommandGroup {
  public CommandGroupAlpha() {
    addSequential(new Forwards(500));
    addSequential(new Turn(90));
    addSequential(new Forwards(250));
    addSequential(new Turn(-90));
    addSequential(new Forwards(250));
    addSequential(new Turn(-90));
    addSequential(new Forwards(250));
    addSequential(new Turn(90));
    addSequential(new Backwards(750));
  }
}
