package com.mycompany.sportstats.Team.Match.Skill;

import com.mycompany.sportstats.Utils.Environment;

import java.util.ArrayList;

public class Bloqueo extends Skill {

    public Bloqueo (String matchId, String player, String value){
        super (matchId, player, value);
    }

    public Bloqueo (String matchId, ArrayList movement){
        super(matchId,
                (movement.size()>0)?movement.get(0).toString():"",
                (movement.size()>1)?movement.get(1).toString():Environment.getPropertyValue("match.block.defaultValue"));
    }

    @Override
    public String toString() {
        String skill = "";
        skill = String.valueOf(getPlayer()) + " BLO " + getValue();
        return skill;
    }
}
