package Match;

import java.util.ArrayList;

public class FinDeSet extends Movement{
    private int tanteoLocal;
    private int tanteoVisitante;

    public FinDeSet (String matchId, int tanteoLocal, int tanteoVisitante){
        super(matchId);
        this.tanteoLocal = tanteoLocal;
        this.tanteoVisitante = tanteoVisitante;
    }

    public FinDeSet (String matchId, ArrayList movement){
        super(matchId);
        this.tanteoLocal = Integer.parseInt(movement.get(1).toString());
        this.tanteoVisitante = Integer.parseInt(movement.get(2).toString());
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
