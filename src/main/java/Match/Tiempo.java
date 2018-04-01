package Match;

import java.util.ArrayList;

public class Tiempo extends Movement {
    private int nuestroTanteo;
    private int suTanteo;
    private boolean pedidoLocal;

    public Tiempo (String matchId, int tanteoLocal, int tanteoVisitante, boolean pedidoLocal){
        super(matchId);
        this.nuestroTanteo = tanteoLocal;
        this.suTanteo = tanteoVisitante;
        this.pedidoLocal = pedidoLocal;
    }

    public Tiempo (String matchId, ArrayList movement){
        super(matchId);
        this.nuestroTanteo = (movement.size()>0)?Integer.parseInt(movement.get(0).toString()):0;
        this.suTanteo = (movement.size()>0)?Integer.parseInt(movement.get(1).toString()):0;
        //this.pedidoLocal = (movement.size()>0)?Integer.parseInt(movement.get(2).toString()):0;
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

    public boolean isPedidoLocal() {
        return pedidoLocal;
    }

    public void setPedidoLocal(boolean pedidoLocal) {
        this.pedidoLocal = pedidoLocal;
    }

    @Override
    public String toString() {
        //return super.toString();
        String tiempo = "";
        tiempo = "Tiempo";
        return tiempo;
    }
}
