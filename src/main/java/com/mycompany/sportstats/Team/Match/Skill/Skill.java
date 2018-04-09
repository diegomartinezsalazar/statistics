package com.mycompany.sportstats.Team.Match.Skill;

import com.mycompany.sportstats.Team.Match.Movement;

public class Skill extends Movement {
    private int player;
    private String value;

    public Skill (String matchId, int player, String value){
        super(matchId);
        this.setPlayer(player);
        this.setValue(value);
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

}
