package Match;

import java.util.ArrayList;

public class Remate extends Skill {
    private String tipo;
    private int origen;
    private int destino;

    public Remate (String matchId, int player, String value){
        super (matchId, player, value);
    }

    public Remate (String matchId, ArrayList movement){
        super(matchId, Integer.parseInt(movement.get(1).toString()), movement.get(4).toString());
        this.origen = Integer.parseInt(movement.get(2).toString());
        this.destino = Integer.parseInt(movement.get(3).toString());
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }
}