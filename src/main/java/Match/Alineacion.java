package Match;

import java.util.ArrayList;

public class Alineacion extends Movement {
    private ArrayList jugadoresAlineacion;
    public Alineacion(String matchId, ArrayList<String> alineacion){
        super(matchId);
        jugadoresAlineacion = new ArrayList();
        for (String player: alineacion
                ) {
            jugadoresAlineacion.add(Integer.parseInt(player));
        }
    }

    public Alineacion (){
        super();
    }

    public ArrayList getPlayers() {
        return getJugadoresAlineacion();
    }

    public void setPlayer(int player) {
        getJugadoresAlineacion().add(player);
    }

    public ArrayList getJugadoresAlineacion() {
        return jugadoresAlineacion;
    }

    public void setJugadoresAlineacion(ArrayList jugadoresAlineacion) {
        this.jugadoresAlineacion = jugadoresAlineacion;
    }

    public void addCambio (int saledelCampo, int entraAlCampo){
        for (int i = 0; i < 7; i++){
            if (Integer.parseInt((String)jugadoresAlineacion.get(i)) == saledelCampo){
                jugadoresAlineacion.set(i, entraAlCampo);
                break;
            }
        }
    }
}
