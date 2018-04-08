package Team.Match;

import java.util.ArrayList;

public class Tanteo extends Movement{
    private int nuestroTanteo;
    private int suTanteo;

    public Tanteo (String matchId, int nuestroTanteo, int suTanteo){
        super(matchId);
        this.nuestroTanteo = nuestroTanteo;
        this.suTanteo = suTanteo;
    }

    public Tanteo (String matchId, ArrayList movement){
        super(matchId);
        this.nuestroTanteo = Integer.parseInt(movement.get(0).toString());
        this.suTanteo = Integer.parseInt(movement.get(1).toString());
    }

    public int getSuTanteo() {
        return suTanteo;
    }

    public void setSuTanteo(int suTanteo) {
        this.suTanteo = suTanteo;
    }

    public int getNuestroTanteo() {
        return nuestroTanteo;
    }

    public void setNuestroTanteo(int nuestroTanteo) {
        this.nuestroTanteo = nuestroTanteo;
    }
}
