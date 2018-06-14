package com.mycompany.sportstats.Team.Match.Skill;

import com.mycompany.sportstats.Utils.Environment;

import java.util.ArrayList;

public class Defensa extends Skill {
    public Defensa (String matchId, String player, String value){
        super (matchId, player, value);
    }

    public Defensa (String matchId, ArrayList movement){
        super(matchId,
                (movement.size()>1)?movement.get(0).toString():"",
                (movement.size()>2)?movement.get(1).toString():Environment.getPropertyValue("match.defense.defaultValue"));
    }

    @Override
    public String toString() {
        String skill = "";
        skill = String.valueOf(getPlayer()) + " DEF " + getValue();
        return skill;
    }
}
