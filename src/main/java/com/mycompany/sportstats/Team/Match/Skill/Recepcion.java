package com.mycompany.sportstats.Team.Match.Skill;

import com.mycompany.sportstats.Utils.Environment;

import java.util.ArrayList;

public class Recepcion extends Skill {
    private String tipo;

    public Recepcion (String matchId, String player, String value){
        super (matchId, player, value);
    }

    public Recepcion (String matchId, ArrayList movement){
        super(matchId,
                (movement.size()>1)?movement.get(0).toString():"",
                (movement.size()>2)?movement.get(1).toString():Environment.getPropertyValue("match.reception.defaultValue"));
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        String skill = "";
        skill = String.valueOf(getPlayer()) + " REC " + getValue();
        return skill;
    }
}
