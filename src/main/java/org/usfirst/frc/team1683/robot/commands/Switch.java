package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Switch extends CommandGroup {
    public Switch(boolean right) {
        int side = right ? 1 : -1;
        addSequential(new Forward(120, 0.5));
        addSequential(new Turn(-90 * side, 0.4));
        addSequential(new LiftUp(true));
        addSequential(new Forward(35, 0.3));
        addSequential(new Release());
    }
}
