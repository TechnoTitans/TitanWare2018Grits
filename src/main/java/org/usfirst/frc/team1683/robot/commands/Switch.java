package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Switch extends CommandGroup {
    public Switch(boolean right) {
        addSequential(new OutInShake());
        int side = right ? 1 : -1;
        addSequential(new Forward(132, 0.5, false));
        addSequential(new Turn(-90 * side, 0.4));
        addSequential(new LiftUp(true));
        addSequential(new Forward(45, 0.3));
        addSequential(new Release());
    }
}
