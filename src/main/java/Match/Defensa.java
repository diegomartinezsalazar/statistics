package Match;

import java.util.ArrayList;

public class Defensa extends Skill {
    public Defensa (String matchId, int player, String value){
        super (matchId, player, value);
    }

    public Defensa (String matchId, ArrayList movement){
        super (matchId, Integer.parseInt(movement.get(0).toString()), movement.get(1).toString());
    }

    @Override
    public String toString() {
        String skill = "";
        skill = String.valueOf(getPlayer()) + " DEF " + getValue();
        return skill;
    }
}
