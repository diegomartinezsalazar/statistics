package com.mycompany.sportstats.Team.Match.Skill;

import com.mycompany.sportstats.Team.Match.Movement;
import com.mycompany.sportstats.Utils.UtilsNumber;

public class Skill extends Movement {
    private int player;
    private String value;

    public Skill (String matchId, String player, String value){
        super(matchId);
        setValues(player, value);
    }

    public Skill (String matchId){
        super (matchId);
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValues (String player, String value) {
        if (UtilsNumber.isInteger(player)) {
            this.setPlayer(Integer.parseInt(player));
            this.setValue(value);
        }
        else{
            this.setValue(player);
        }
    }
}
