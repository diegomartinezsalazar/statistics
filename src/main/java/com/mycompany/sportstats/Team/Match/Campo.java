package com.mycompany.sportstats.Team.Match;

public class Campo {
    private boolean enCasa;

    public Campo (boolean enCasa){
        this.enCasa = enCasa;
    }

    public boolean isEnCasa() {
        return enCasa;
    }

    public void setEnCasa(boolean enCasa) {
        this.enCasa = enCasa;
    }
}
