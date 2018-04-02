package Match;

import java.util.ArrayList;

public class Jugada {
    private ArrayList movimientos = new ArrayList();
    private Alineacion alineacion = new Alineacion();
    private int nuestroTanteoInicial = 0;
    private int nuestroTanteoFinal = 0;
    private int suTanteoInicial = 0;
    private int suTanteoFinal = 0;

    public Jugada (){
        movimientos = new ArrayList();
    }

    public ArrayList getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList movimientos) {
        this.movimientos = movimientos;
    }

    public Alineacion getAlineacion() {
        return alineacion;
    }

    public void setAlineacion(Alineacion alineacion) {
        this.alineacion = alineacion;
    }

    public void addMovimiento(Object objeto){
        movimientos.add(objeto);
    }

    public int getNuestroTanteoInicial() {
        return nuestroTanteoInicial;
    }

    public void setNuestroTanteoInicial(int nuestroTanteoInicial) {
        this.nuestroTanteoInicial = nuestroTanteoInicial;
    }

    public int getNuestroTanteoFinal() {
        return nuestroTanteoFinal;
    }

    public void setNuestroTanteoFinal(int nuestroTanteoFinal) {
        this.nuestroTanteoFinal = nuestroTanteoFinal;
    }

    public int getSuTanteoInicial() {
        return suTanteoInicial;
    }

    public void setSuTanteoInicial(int suTanteoInicial) {
        this.suTanteoInicial = suTanteoInicial;
    }

    public int getSuTanteoFinal() {
        return suTanteoFinal;
    }

    public void setSuTanteoFinal(int suTanteoFinal) {
        this.suTanteoFinal = suTanteoFinal;
    }

    @Override
    public String toString() {
        //return super.toString();
        String jugada = "";
        jugada += nuestroTanteoFinal + " - " + suTanteoFinal;
        jugada += " / " + alineacion.toString() + " /";
        for (Object movimiento: movimientos
             ) {
            jugada += " " + movimiento.toString();
        }
        return jugada;
    }
}
