package com.mycompany.sportstats.Team.Match.Skill;

import com.mycompany.sportstats.Utils.Environment;

import java.util.ArrayList;

public class Colocacion extends Skill {
    private String tipo;
    private String posicion;

    public Colocacion (String matchId, String player, String value){
        super (matchId, player, value);
    }

    public Colocacion (String matchId, ArrayList movement){
        //super (matchId, Integer.parseInt(movement.get(0).toString()), movement.get(1).toString());
        super(matchId,
                (movement.size()>1)?movement.get(0).toString():"",
                (movement.size()>2)?movement.get(1).toString():Environment.getPropertyValue("match.set.defaultValue"));
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        String skill = "";
        skill = String.valueOf(getPlayer()) + " COL " + getValue();
        return skill;
    }
}
