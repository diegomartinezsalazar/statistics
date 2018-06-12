package com.mycompany.sportstats.Team.Match.Skill;

import com.mycompany.sportstats.Utils.Environment;

import java.util.ArrayList;

public class Bloqueo extends Skill {

    public Bloqueo (String matchId, int player, String value){
        super (matchId, player, value);
    }

    public Bloqueo (String matchId, ArrayList movement){
        super(matchId,
                Integer.parseInt(movement.get(0).toString()),
                (movement.size()>2)?movement.get(1).toString():Environment.getPropertyValue("match.block.defaultValue"));
    }

    @Override
    public String toString() {
        String skill = "";
        skill = String.valueOf(getPlayer()) + " BLO " + getValue();
        return skill;
    }
}
