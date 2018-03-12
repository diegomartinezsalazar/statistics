package Match;

import java.util.ArrayList;

public class Alineacion extends Movement {
    private ArrayList jugadoresAlineacion;
    public Alineacion(String matchId, ArrayList<String> alineacion){
        super(matchId);
        for (String player: alineacion
                ) {
            jugadoresAlineacion.add(player);
        }
    }

    public ArrayList getPlayers() {
        return jugadoresAlineacion;
    }

    public void setPlayer(String player) {
        jugadoresAlineacion.add(player);
    }
}
