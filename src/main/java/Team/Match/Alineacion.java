package Team.Match;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;

public class Alineacion extends Movement {
    private ArrayList jugadoresAlineacion = new ArrayList();
    public Alineacion(String matchId, ArrayList<String> alineacion){
        super(matchId);
        jugadoresAlineacion = new ArrayList();
        for (String player: alineacion
                ) {
            if (NumberUtils.isCreatable(player)){
                jugadoresAlineacion.add(Integer.parseInt(player));
            }
        }
    }

    public Alineacion (){
        super();
    }

    public Alineacion (Alineacion alineacion){
        super();
        jugadoresAlineacion = new ArrayList(alineacion.getJugadoresAlineacion());
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
            if (Integer.parseInt(jugadoresAlineacion.get(i).toString()) == saledelCampo){
                jugadoresAlineacion.set(i, entraAlCampo);
                break;
            }
        }
    }

    @Override
    public String toString() {
        //return super.toString();
        String resultado = "";
        for (Object player : jugadoresAlineacion
             ) {
            resultado = (resultado == "")?player.toString():resultado + " " + player.toString();
            //resultado = resultado + " " + player.toString();
        }
        return resultado;
    }
}
