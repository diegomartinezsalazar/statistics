package Match;

public class Saque extends Skill {
    private int origen;
    private int destino;
    private String tipo;

    public Saque (String matchId, Player player, String value){
        super (matchId, player, value);
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
