package Match;

import java.util.ArrayList;

public class Set {
    private int numeroSet;
    private Alineacion alineacion;
    private ArrayList<Object> movimientos;


    public int getNumeroSet() {
        return numeroSet;
    }

    public void setNumeroSet(int numeroSet) {
        this.numeroSet = numeroSet;
    }

    public Alineacion getAlineacion() {
        return alineacion;
    }

    public void setAlineacion(Alineacion alineacion) {
        this.alineacion = alineacion;
    }

    public ArrayList<Object> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Object> movimientos) {
        this.movimientos = movimientos;
    }

    public void addMovimiento(Object movimiento) {
        movimientos.add(movimiento);
    }
}
