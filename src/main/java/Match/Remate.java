package Match;

public class Remate extends Skill {
    private String tipo;
    private int origen;
    private int destino;

    public Remate (String matchId, Player player, String value){
        super (matchId, player, value);
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