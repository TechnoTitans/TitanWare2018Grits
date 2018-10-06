package org.usfirst.frc.team1683.robot.automation;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team1683.robot.commands.Forward;
import org.usfirst.frc.team1683.robot.commands.MiddleSwitch;
import org.usfirst.frc.team1683.robot.commands.Switch;

public class Priority {
    String gameData;

    public Character start_side;

    private Command todo;

    boolean go_to_same_switch = false;
    boolean go_to_same_scale = false;
    boolean go_to_opp_switch = false;
    boolean go_to_opp_scale = false;
    boolean go_to_right_switch = false;
    boolean go_to_left_switch = false;

    private Target[] switches_and_sides;

    public Priority(Target[] switches_and_sides, char start_side){
        this.start_side = start_side;
        this.switches_and_sides = switches_and_sides;
        
    }

    public Command getTodo() {
        gameData = DriverStation.getInstance().getGameSpecificMessage();

        if (start_side == 'L' || start_side == 'R'){
            for (Target priority : switches_and_sides){
                if (priority == Target.SAME_SCALE && check_side_scale()){
                    firstAction(priority);
                    break;
                } else if (priority == Target.OPP_SCALE && !check_side_scale()){
                    firstAction(priority);
                    break;
                } else if (priority == Target.SAME_SWITCH && check_side_switch()){
                    firstAction(priority);
                    break;
                } else if (priority == Target.OPP_SWITCH && !check_side_switch()){
                    firstAction(priority);
                    break;
                }
            }
        } else {
            if (gameData.charAt(0) == 'R'){
                firstAction(Target.RIGHT_SWITCH);
            } else {
                firstAction(Target.LEFT_SWITCH);
            }
        }
        return todo;
    }

    public boolean check_side_scale(){
        if (gameData.charAt(1) == start_side){
            return true;
        } else {
            return false;
        }
    }

    public boolean check_side_switch(){
        if (gameData.charAt(0) == start_side){
            return true;
        } else {
            return false;
        }
    }

    public void firstAction(Target priority){
        SmartDashboard.putString("Auto priority", priority.toString());
        if (priority == Target.SAME_SWITCH){
            todo = new Switch(start_side == 'R');
        } else if (priority == Target.SAME_SCALE) {
            todo = new Forward(120, 0.5);
        } else if (priority == Target.OPP_SWITCH){
            todo = new Forward(120, 0.5);
        } else if (priority == Target.OPP_SCALE){
            todo = new Forward(120, 0.5);
        } else if (priority == Target.RIGHT_SWITCH){
            todo = new MiddleSwitch(true);
        } else if (priority == Target.LEFT_SWITCH){
            todo = new MiddleSwitch(false);
        }
        todo = new Forward(120, 0.5);
    }



}
