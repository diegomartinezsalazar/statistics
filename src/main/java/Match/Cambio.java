package Match;

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