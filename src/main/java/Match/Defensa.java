package Match;

public class Defensa extends Skill {
    private String tipo;

    public Defensa (String matchId, Player player, String value){
        super (matchId, player, value);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
