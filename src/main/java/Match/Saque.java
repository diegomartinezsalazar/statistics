package Match;

import java.util.ArrayList;

public class Saque extends Skill {
    private int origen;
    private int destino;
    private String tipo;

    public Saque (String matchId, int player, String value){
        super (matchId, player, value);
    }

    public Saque (String matchId, ArrayList movement){
        super (matchId, Integer.parseInt(movement.get(1).toString()), movement.get(2).toString());
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
