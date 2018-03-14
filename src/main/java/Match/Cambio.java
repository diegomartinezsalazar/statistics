package Match;

import java.util.ArrayList;

public class Cambio extends Movement {
    private int tanteoLocal;
    private int tanteoVisitante;
    private int entra;
    private int seRetira;

    public Cambio(String matchId, int tanteoLocal, int tanteoVisitante, int entra, int seRetira) {
        super(matchId);
        this.tanteoLocal = tanteoLocal;
        this.tanteoVisitante = tanteoVisitante;
        this.entra = entra;
        this.seRetira = seRetira;
    }

    public Cambio(String matchId, ArrayList movement) {
        super(matchId);
        this.tanteoLocal = Integer.parseInt(movement.get(1).toString());;
        this.tanteoVisitante = Integer.parseInt(movement.get(2).toString());;
        this.entra = Integer.parseInt(movement.get(3).toString());;
        this.seRetira = Integer.parseInt(movement.get(4).toString());;
    }

    public int getTanteoVisitante() {
        return tanteoVisitante;
    }

    public void setTanteoVisitante(int tanteoVisitante) {
        this.tanteoVisitante = tanteoVisitante;
    }

    public int getTanteoLocal() {
        return tanteoLocal;
    }

    public void setTanteoLocal(int tanteoLocal) {
        this.tanteoLocal = tanteoLocal;
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