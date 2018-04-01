package Match;

import java.util.ArrayList;

public class Convocatoria extends Movement{
    private ArrayList players;
    public Convocatoria (String matchId, ArrayList<String> playerList){
        super(matchId);
        players = new ArrayList();
        for (String player: playerList
             ) {
            players.add(player);
        }
    }

    public ArrayList getPlayers() {
        return players;
    }

    public void setPlayer(String player) {
        players.add(player);
    }

    @Override
    public String toString() {
        //return super.toString();
        String convocatoria = "";
        for (Object player : players
                ) {
            convocatoria = (convocatoria == "")?player.toString():convocatoria + " " + player.toString();
            //resultado = resultado + " " + player.toString();
        }
        return convocatoria;
    }
}
