package Match;

public class Tanteo extends Movement{
    private int tanteoLocal;
    private int tanteoVisitante;

    public Tanteo (String matchId, int tanteoLocal, int tanteoVisitante){
        super(matchId);
        this.tanteoLocal = tanteoLocal;
        this.tanteoVisitante = tanteoVisitante;
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
}
