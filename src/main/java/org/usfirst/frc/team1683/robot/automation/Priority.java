package org.usfirst.frc.team1683.robot.automation;

import edu.wpi.first.wpilibj.DriverStation;

import java.util.HashMap;
import java.util.Map;

public class Priority {
    String gameData;

    public Character start_side;

    boolean go_to_same_switch = false;
    boolean go_to_same_scale = false;
    boolean go_to_opp_switch = false;
    boolean go_to_opp_scale = false;
    boolean go_to_right_switch = false;
    boolean go_to_left_switch = false;

    enum Target {
        SAME_SWITCH,
        SAME_SCALE,
        OPP_SWITCH,
        OPP_SCALE,
        RIGHT_SWITCH,
        LEFT_SWITCH,
    }

    public Priority(Target[] switches_and_sides, char start_side){
        this.start_side = start_side;
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
        if (priority == Target.SAME_SWITCH){
            go_to_same_switch = true;
        } else if (priority == Target.SAME_SCALE){
            go_to_same_scale = true;
        } else if (priority == Target.OPP_SWITCH){
            go_to_opp_switch = true;
        } else if (priority == Target.OPP_SCALE){
            go_to_opp_scale = true;
        } else if (priority == Target.RIGHT_SWITCH){
            go_to_right_switch = true;
        } else if (priority == Target.LEFT_SWITCH){
            go_to_left_switch = true;
        }
    }



}
