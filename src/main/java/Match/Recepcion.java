package Match;

public class Recepcion extends Skill {
    private String tipo;

    public Recepcion (String matchId, Player player, String value){
        super (matchId, player, value);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
