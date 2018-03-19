package Match;

import java.util.ArrayList;

public class Set {
    private int numeroSet;
    private Alineacion alineacionInicial;
    private Alineacion alineacionActual;
    private ArrayList<Object> movimientos;
    private ArrayList<Object> jugada;
    public int puntosNuestros = 0;
    public int puntosSuyos = 0;
    public boolean saqueInicialFavor = false;
    public boolean saqueActualFavor = false;

    public int getNumeroSet() {
        return numeroSet;
    }

    public void setNumeroSet(int numeroSet) {
        this.numeroSet = numeroSet;
    }

    public Alineacion getAlineacionInicial() {
        return alineacionInicial;
    }

    public void setAlineacionInicial(Alineacion alineacionInicial) {
        this.alineacionInicial = alineacionInicial;
        this.alineacionActual = alineacionInicial;
    }

    public Alineacion getAlineacionActual() {
        return alineacionActual;
    }

    public void setAlineacion(Alineacion alineacionActual) {
        this.alineacionActual = alineacionActual;
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

    public void addMovimientoJugada (Object object){
        if (object.getClass() == Saque.class){
            Saque saque = (Saque)object;
            switch (saque.getValue()){
                case "++":{
                    puntosNuestros += 1;
                    rotacion();
                    saqueActualFavor = true;
                    break;
                }
                case "--":{
                    puntosSuyos += 1;
                    rotacion();
                    saqueActualFavor = false;
                    break;
                }
            }
            addMovimiento(object);
        } else if (object.getClass() == Recepcion.class){

        } else if (object.getClass() == Colocacion.class){

        } else if (object.getClass() == Remate.class){

        } else if (object.getClass() == Bloqueo.class){

        } else if (object.getClass() == Defensa.class){

        }
    }

    public void rotacion(){
        int aux = 0;
        ArrayList<String> alineacionAux = alineacionActual.getPlayers();
        for (int i = 0; i<5; i++){
            aux = Integer.parseInt(alineacionAux.get(i+1));
            alineacionAux.set(i+1, alineacionAux.get(i));
        }
        alineacionAux.set(0, Integer.toString(aux));
        alineacionActual.setJugadoresAlineacion(alineacionAux);
    }
}
