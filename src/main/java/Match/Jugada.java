package Match;

import java.util.ArrayList;

public class Jugada {
    private ArrayList movimientos = new ArrayList();
    private ArrayList alineacion = new ArrayList();
    private Tanteo tanteoInicial;
    private Tanteo tanteoFinal;

    public ArrayList getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList movimientos) {
        this.movimientos = movimientos;
    }

    public ArrayList getAlineacion() {
        return alineacion;
    }

    public void setAlineacion(ArrayList alineacion) {
        this.alineacion = alineacion;
    }

    public Tanteo getTanteoInicial() {
        return tanteoInicial;
    }

    public void setTanteoInicial(Tanteo tanteoInicial) {
        this.tanteoInicial = tanteoInicial;
    }

    public Tanteo getTanteoFinal() {
        return tanteoFinal;
    }

    public void setTanteoFinal(Tanteo tanteoFinal) {
        this.tanteoFinal = tanteoFinal;
    }

    public void addMovimiento(Object objeto){
        movimientos.add(objeto);
    }
}
