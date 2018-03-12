package Match;

public class Colocacion extends Skill {
    private String tipo;
    private String posicion;

    public Colocacion (String matchId, Player player, String value){
        super (matchId, player, value);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
