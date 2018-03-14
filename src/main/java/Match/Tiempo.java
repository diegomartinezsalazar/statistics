package Match;

import java.util.ArrayList;

public class Tiempo extends Movement {
    private int tanteoLocal;
    private int tanteoVisitante;
    private boolean pedidoLocal;

    public Tiempo (String matchId, int tanteoLocal, int tanteoVisitante, boolean pedidoLocal){
        super(matchId);
        this.tanteoLocal = tanteoLocal;
        this.tanteoVisitante = tanteoVisitante;
        this.pedidoLocal = pedidoLocal;
    }

    public Tiempo (String matchId, ArrayList movement){
        super(matchId);
        this.tanteoLocal = Integer.parseInt(movement.get(1).toString());
        this.tanteoVisitante = Integer.parseInt(movement.get(2).toString());
        this.pedidoLocal = Boolean.getBoolean(movement.get(3).toString());
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

    public boolean isPedidoLocal() {
        return pedidoLocal;
    }

    public void setPedidoLocal(boolean pedidoLocal) {
        this.pedidoLocal = pedidoLocal;
    }
}
