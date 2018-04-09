package com.mycompany.sportstats.Team.Match;

public class ErrorContrario extends Movement {
    public ErrorContrario (String matchId){
        super(matchId);
    }

    @Override
    public String toString() {
        //return super.toString();
        String errorContrario = "";
        errorContrario = "Error contrario";
        return errorContrario;
    }
}
