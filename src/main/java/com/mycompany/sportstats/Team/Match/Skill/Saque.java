package com.mycompany.sportstats.Team.Match.Skill;

import com.mycompany.sportstats.Utils.Environment;

import java.util.ArrayList;

public class Saque extends Skill {
    private int origen;
    private int destino;
    private String tipo;

    public Saque (String matchId, String player, String value){
        super (matchId, player, value);
    }

    public Saque (String matchId, ArrayList movement){
        super(matchId,
                (movement.size()>1)?movement.get(0).toString():"",
                (movement.size()>2)?movement.get(1).toString():Environment.getPropertyValue("match.serve.defaultValue"));
        //this.setPlayer(Integer.parseInt(movement.get(0).toString()));
        //this.setValue((movement.size()>2)?movement.get(1).toString():Environment.getPropertyValue("match.serve.defaultValue"));
        //super (matchId, Integer.parseInt(movement.get(0).toString()), movement.get(1).toString());
        this.tipo = (movement.size()>2)?movement.get(2).toString():"";
        this.origen = (movement.size()>2)?Integer.parseInt(movement.get(3).toString()):0;
        this.destino = (movement.size()>2)?Integer.parseInt(movement.get(4).toString()):0;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        String skill = "";
        skill = String.valueOf(getPlayer()) + " SAQ " + getValue();
        return skill;
    }
}
