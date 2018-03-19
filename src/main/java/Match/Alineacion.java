package Match;

import java.util.ArrayList;

public class Alineacion extends Movement {
    private ArrayList jugadoresAlineacion;
    public Alineacion(String matchId, ArrayList<String> alineacion){
        super(matchId);
        for (String player: alineacion
                ) {
            getJugadoresAlineacion().add(Integer.parseInt(player));
        }
    }

    public ArrayList getPlayers() {
        return getJugadoresAlineacion();
    }

    public void setPlayer(String player) {
        getJugadoresAlineacion().add(player);
    }

    public ArrayList getJugadoresAlineacion() {
        return jugadoresAlineacion;
    }

    public void setJugadoresAlineacion(ArrayList jugadoresAlineacion) {
        this.jugadoresAlineacion = jugadoresAlineacion;
    }
}
