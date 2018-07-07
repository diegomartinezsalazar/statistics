package com.mycompany.sportstats.Team.Match;

import com.mycompany.sportstats.Utils.UtilsNumber;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class Alineacion extends Movement {
    private ArrayList<Integer> jugadoresAlineacion = new ArrayList();
    public Alineacion(String matchId, ArrayList<String> alineacion){
        super(matchId);
        jugadoresAlineacion = new ArrayList();
        ArrayList<String> alineacionCleared = UtilsNumber.replaceAllButNumbers(alineacion);
        String alineacionBase = "";
        for (String player: alineacionCleared
                ) {
            alineacionBase += player;
        }
        alineacionCleared = new ArrayList<String>(Arrays.asList(alineacionBase.split(" ")));

        for (String player: alineacionCleared
                ) {
            if (NumberUtils.isCreatable(player.trim())){
                jugadoresAlineacion.add(Integer.parseInt(player));
            }
        }

        if (jugadoresAlineacion.size() != 7){
            System.out.println("Error Alineación: Jugadores distintos de 7");
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

    public ArrayList<Integer> getJugadoresAlineacion() {
        return jugadoresAlineacion;
    }

    public void setJugadoresAlineacion(ArrayList jugadoresAlineacion) {
        this.jugadoresAlineacion = jugadoresAlineacion;
    }

    public void addCambio (int saledelCampo, int entraAlCampo){
        boolean cambioRealizado = false;
        for (int i = 0; i < 7; i++){
            if (Integer.parseInt(jugadoresAlineacion.get(i).toString()) == saledelCampo){
                jugadoresAlineacion.set(i, entraAlCampo);
                cambioRealizado = true;
                break;
            }
        }
        if (!cambioRealizado){
            System.out.println("Error Cambio: El jugador " + saledelCampo + " no se encuentra en el campo. " + toString());
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
