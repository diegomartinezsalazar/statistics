package Match;

import java.util.ArrayList;

public class Cambio extends Movement {
    private int nuestroTanteo;
    private int suTanteo;
    private int entra;
    private int seRetira;

    public Cambio(String matchId, int tanteoLocal, int tanteoVisitante, int seRetira, int entra) {
        super(matchId);
        this.nuestroTanteo = tanteoLocal;
        this.suTanteo = tanteoVisitante;
        this.entra = entra;
        this.seRetira = seRetira;
    }

    public Cambio(String matchId, ArrayList movement) {
        super(matchId);
        this.nuestroTanteo = (movement.size()>8)?Integer.parseInt(movement.get(5).toString()):0;
        this.suTanteo = (movement.size()>8)?Integer.parseInt(movement.get(7).toString()):0;
        this.seRetira = Integer.parseInt(movement.get(3).toString());;
        this.entra = Integer.parseInt(movement.get(1).toString());;
    }

    public Cambio (CambioBuilder cambioBuilder){
        this.nuestroTanteo = cambioBuilder.getNuestroTanteo();
        this.suTanteo = cambioBuilder.getSuTanteo();
        this.entra = cambioBuilder.getEntra();
        this.seRetira = cambioBuilder.getSeRetira();
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

    @Override
    public String toString() {
        String cambio = "";
        cambio = String.valueOf("CAMBIO: Sale " + getEntra() + " entra " + getSeRetira());
        return cambio;
    }
}