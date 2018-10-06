package org.usfirst.frc.team1683.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

class SuperiorGroup extends CommandGroup{

    @Override
    protected void interrupted() {
        end();
    }


}