package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OutInShake extends CommandGroup {
    public OutInShake() {
        addSequential(new Jerk(true));
        addSequential(new Jerk(false));
        addSequential(new Grab(), 0.5);
    }
}
