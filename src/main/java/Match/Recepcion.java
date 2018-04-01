package Match;

import java.util.ArrayList;

public class Recepcion extends Skill {
    private String tipo;

    public Recepcion (String matchId, int player, String value){
        super (matchId, player, value);
    }

    public Recepcion (String matchId, ArrayList movement){
        super (matchId, Integer.parseInt(movement.get(0).toString()), movement.get(1).toString());
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        String skill = "";
        skill = String.valueOf(getPlayer()) + " REC " + getValue();
        return skill;
    }
}
