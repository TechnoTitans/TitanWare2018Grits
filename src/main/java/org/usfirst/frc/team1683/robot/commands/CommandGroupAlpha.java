package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandGroupAlpha extends CommandGroup {
    public CommandGroupAlpha(){
        addSequential(new Forward(100, 0.3, 0));
        addSequential(new PIDTurn(90));
        addSequential(new Forward(50, 0.3, 0));
        

    }
}
