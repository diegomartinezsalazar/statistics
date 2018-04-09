package com.mycompany.sportstats.Team.Match;

import java.util.ArrayList;

public class FinDeSet extends Movement{
    private int nuestroTanteo;
    private int suTanteo;

    public FinDeSet (String matchId, int nuestroTanteo, int tanteoVisitante){
        super(matchId);
        this.nuestroTanteo = nuestroTanteo;
        this.suTanteo = tanteoVisitante;
    }

    public FinDeSet (String matchId, ArrayList movement){
        super(matchId);
        this.nuestroTanteo = Integer.parseInt(movement.get(0).toString());
        this.suTanteo = Integer.parseInt(movement.get(2).toString());
    }

    public int getSuTanteo() {
        return suTanteo;
    }

    public void setSuTanteo(int suTanteo) {
        this.suTanteo = suTanteo;
    }

    public int getNuestroTanteo() {
        return nuestroTanteo;
    }

    public void setNuestroTanteo(int nuestroTanteo) {
        this.nuestroTanteo = nuestroTanteo;
    }
}
