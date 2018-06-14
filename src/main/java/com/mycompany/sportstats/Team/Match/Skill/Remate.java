package com.mycompany.sportstats.Team.Match.Skill;

import com.mycompany.sportstats.Utils.Environment;

import java.util.ArrayList;

public class Remate extends Skill {
    private String tipo;
    private int origen;
    private int destino;

    public Remate (String matchId, String player, String value){
        super (matchId, player, value);
    }

    public Remate (String matchId, ArrayList movement){
        super(matchId,
                (movement.size()>1)?movement.get(0).toString():"",
                (movement.size()>2)?movement.get(1).toString():Environment.getPropertyValue("match.attack.defaultValue"));
        this.origen = (movement.size()>2)?Integer.parseInt(movement.get(2).toString()):0;
        this.destino = (movement.size()>2)?Integer.parseInt(movement.get(3).toString()):0;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        String skill = "";
        skill = String.valueOf(getPlayer()) + " REM " + getValue();
        return skill;
    }
}