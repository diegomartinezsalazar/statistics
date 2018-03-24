package Match;

import java.util.ArrayList;

public class Cambio extends Movement {
    private int nuestroTanteo;
    private int suTanteo;
    private int entra;
    private int seRetira;

    public Cambio(String matchId, int tanteoLocal, int tanteoVisitante, int entra, int seRetira) {
        super(matchId);
        this.nuestroTanteo = tanteoLocal;
        this.suTanteo = tanteoVisitante;
        this.entra = entra;
        this.seRetira = seRetira;
    }

    public Cambio(String matchId, ArrayList movement) {
        super(matchId);
        this.nuestroTanteo = (movement.size()>2)?Integer.parseInt(movement.get(2).toString()):0;
        this.suTanteo = (movement.size()>2)?Integer.parseInt(movement.get(3).toString()):0;
        this.entra = Integer.parseInt(movement.get(0).toString());;
        this.seRetira = Integer.parseInt(movement.get(1).toString());;
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

    public int getEntra() {
        return entra;
    }

    public void setEntra(int entra) {
        this.entra = entra;
    }

    public int getSeRetira() {
        return seRetira;
    }

    public void setSeRetira(int seRetira) {
        this.seRetira = seRetira;
    }
}