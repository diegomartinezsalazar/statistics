package Match;

import java.util.ArrayList;

public class Convocatoria extends Movement{
    private ArrayList players;
    public Convocatoria (String matchId, ArrayList<String> playerList){
        super(matchId);
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
}
