package Match;

import java.util.ArrayList;

public class Set {
    private int numeroSet;
    private Alineacion alineacionInicial;
    private Alineacion alineacionActual;
    private ArrayList<Object> movimientos;
    private ArrayList<Object> jugadas;
    public int puntosNuestros = 0;
    public int puntosSuyos = 0;
    public boolean saqueInicialFavor = false;
    public boolean saqueActualFavor = false;
    public Jugada jugadaActual;

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

    public void addJugada(Object object){
        if (object.getClass() == Saque.class){
            Saque saque = (Saque)object;
            jugadaActual.addMovimiento(object);
            switch (saque.getValue()){
                case "++":{
                    puntoNuestro();
                    break;
                }
                case "--":{
                    puntoSuyo();
                    break;
                }
            }

        } else if (object.getClass() == Recepcion.class){
            Recepcion recepcion = (Recepcion) object;
            jugadaActual.addMovimiento(object);
            if (recepcion.getValue() == "--"){
                puntoSuyo();;
            }
        } else if (object.getClass() == Colocacion.class){
            Colocacion colocacion = (Colocacion) object;
            jugadaActual.addMovimiento(object);
            if (colocacion.getValue() == "--"){
                puntoSuyo();;
            }
        } else if (object.getClass() == Remate.class){
            Remate remate = (Remate)object;
            jugadaActual.addMovimiento(object);
            switch (remate.getValue()){
                case "++":{
                    puntoNuestro();
                    break;
                }
                case "--":{
                    puntoSuyo();
                    break;
                }
            }

        } else if (object.getClass() == Bloqueo.class){
            Bloqueo bloqueo = (Bloqueo) object;
            jugadaActual.addMovimiento(object);
            switch (bloqueo.getValue()){
                case "++":{
                    puntoNuestro();
                    break;
                }
                case "--":{
                    puntoSuyo();
                    break;
                }
            }
        } else if (object.getClass() == Defensa.class){
            Defensa defensa = (Defensa) object;
            jugadaActual.addMovimiento(object);
            if (defensa.getValue() == "--"){
                puntoSuyo();;
            }
        }
    }

    public void addErrorContrario(ErrorContrario errorContrario){
        jugadaActual.addMovimiento(errorContrario);
        puntoNuestro();
    }

    public void addPuntoContrario(PuntoContrario puntoContrario){
        jugadaActual.addMovimiento(puntoContrario);
        puntoSuyo();
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

    public void terminaJugada (){
        jugadaActual.setNuestroTanteoFinal(puntosNuestros);
        jugadaActual.setSuTanteoFinal(puntosSuyos);
        jugadas.add(jugadaActual);

    }

    public void inicializaJugada(){
        jugadaActual = new Jugada();
        jugadaActual.setAlineacion(alineacionActual);
        jugadaActual.setNuestroTanteoInicial(puntosNuestros);
        jugadaActual.setSuTanteoInicial(puntosSuyos);
    }

    public void puntoNuestro(){
        puntosNuestros += 1;
        if (!saqueActualFavor){
            rotacion();
            saqueActualFavor = true;
        }
        terminaJugada();
    }

    public void puntoSuyo(){
        puntosSuyos += 1;
        saqueActualFavor = false;
        terminaJugada();
    }

    public void compruebaTanteo (Tanteo tanteo){
        if ((tanteo.getNuestroTanteo() != puntosNuestros) || (tanteo.getSuTanteo() != puntosSuyos)) {
            System.out.println("Tanteo momentáneo incorrecto");
        }
    }
}
