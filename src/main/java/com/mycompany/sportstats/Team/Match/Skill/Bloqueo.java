package com.mycompany.sportstats.Team.Match.Skill;

import java.util.ArrayList;

public class Bloqueo extends Skill {

    public Bloqueo (String matchId, int player, String value){
        super (matchId, player, value);
    }

    public Bloqueo (String matchId, ArrayList movement){
        super (matchId, Integer.parseInt(movement.get(0).toString()), movement.get(1).toString());
    }

    @Override
    public String toString() {
        String skill = "";
        skill = String.valueOf(getPlayer()) + " BLO " + getValue();
        return skill;
    }
}
