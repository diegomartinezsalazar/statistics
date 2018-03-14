package Match;

import java.util.ArrayList;

public class Bloqueo extends Skill {

    public Bloqueo (String matchId, int player, String value){
        super (matchId, player, value);
    }

    public Bloqueo (String matchId, ArrayList movement){
        super (matchId, Integer.parseInt(movement.get(1).toString()), movement.get(2).toString());
    }
}
