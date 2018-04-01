package Match;

import java.util.ArrayList;
import java.util.Objects;

public class Set {
    private int numeroSet;
    private Alineacion alineacionInicial;
    private Alineacion alineacionActual;
    private ArrayList<Object> tiempos;
    private ArrayList<Jugada> jugadas;
    public int puntosNuestros = 0;
    public int puntosSuyos = 0;
    public boolean saqueInicialFavor = false;
    public boolean saqueActualFavor = false;
    public Jugada jugadaActual;
    private boolean setTerminado = false;
    private int numSet = 0;
    private boolean setGanado = false;

    public Set (){
        jugadas = new ArrayList<Jugada>();
        tiempos = new ArrayList<>();
        inicializaJugada();
    }

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
        if (hasAlineacionInicial()){
            this.alineacionInicial =  new Alineacion();
            alineacionInicial.setMatchId(alineacionActual.getMatchId());
            alineacionInicial.setJugadoresAlineacion(new ArrayList(alineacionActual.getJugadoresAlineacion()));
        }
        this.alineacionActual = alineacionActual;
        inicializaJugada();
    }

    public ArrayList<Object> getTiempos() {
        return tiempos;
    }

    public void setTiempos(ArrayList<Object> tiempos) {
        this.tiempos = tiempos;
    }

    public void addMovimiento(Object movimiento) {
        tiempos.add(movimiento);
    }

    public void addJugada(Object object){
        jugadaActual.addMovimiento(object);
        if (object.getClass() == Saque.class){
            Saque saque = (Saque)object;
            //jugadaActual.addMovimiento(object);
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
            //jugadaActual.addMovimiento(object);
            if (Objects.equals("--", recepcion.getValue())){
                puntoSuyo();;
            }
        } else if (object.getClass() == Colocacion.class){
            Colocacion colocacion = (Colocacion) object;
            //jugadaActual.addMovimiento(object);
            if (Objects.equals("--", colocacion.getValue())){
                puntoSuyo();;
            }
        } else if (object.getClass() == Remate.class){
            Remate remate = (Remate)object;
            //jugadaActual.addMovimiento(object);
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
            //jugadaActual.addMovimiento(object);
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
            //jugadaActual.addMovimiento(object);
            if (Objects.equals("--", defensa.getValue())){
            //if ("--".equals(defensa.getValue())){
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
        Integer aux = 0;
        ArrayList<Integer> alineacionAux = new ArrayList<>(alineacionActual.getPlayers());

        aux = alineacionAux.get(0);
        for (int i = 1; i < 6; i++){
            alineacionAux.set(i-1, alineacionAux.get(i));
        }
        alineacionAux.set(5, aux);
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
        terminaSet();
        inicializaJugada();
    }

    public void puntoSuyo(){
        puntosSuyos += 1;
        saqueActualFavor = false;
        terminaJugada();
        terminaSet();
        inicializaJugada();
    }

    public void compruebaTanteo (Tanteo tanteo){
        if ((tanteo.getNuestroTanteo() != puntosNuestros) || (tanteo.getSuTanteo() != puntosSuyos)) {
            System.out.println("Tanteo momentáneo incorrecto");
        }
    }

    public void addCambio (Cambio cambio){
        alineacionActual.addCambio(cambio.getSeRetira(), cambio.getEntra());
    }

    public int getNumSet() {
        return numSet;
    }

    public void setNumSet(int numSet) {
        this.numSet = numSet;
    }

    public void terminaSet(){
        if (numSet == 5){
            if ((puntosNuestros==15) && (puntosSuyos<=13)){
                setSetTerminado(true);
                setSetGanado(true);
            } else if ((puntosSuyos==15) && (puntosNuestros<=13)){
                setSetTerminado(true);
                setSetGanado(false);
            } else if (((puntosSuyos>15) || (puntosNuestros>15)) && (Math.abs(puntosSuyos - puntosNuestros) >= 2)){
                setSetTerminado(true);
                setSetGanado(puntosNuestros>puntosSuyos);
            }
        } else {
            if ((puntosNuestros==25) && (puntosSuyos<=23)){
                setSetTerminado(true);
                setSetGanado(true);
            } else if ((puntosSuyos==25) && (puntosNuestros<=23)){
                setSetTerminado(true);
                setSetGanado(false);
            } else if (((puntosSuyos>25) || (puntosNuestros>25)) && (Math.abs(puntosSuyos - puntosNuestros) >= 2)){
                setSetTerminado(true);
                setSetGanado(puntosNuestros>puntosSuyos);
            }
        }
    }

    public boolean isSetTerminado() {
        return setTerminado;
    }

    public void setSetTerminado(boolean setTerminado) {
        this.setTerminado = setTerminado;
    }

    public boolean isSetGanado() {
        return setGanado;
    }

    public void setSetGanado (boolean setGanado) {
        this.setGanado = setGanado;
    }

    public void addTimeOut (Tiempo tiempo){
        tiempos.add (tiempo);
    }

    public boolean hasAlineacionInicial(){
        return alineacionInicial == null;
    }

    public void printSet(){
        System.out.println("Comienzo Set " + numSet);
        System.out.println ("Alineación inicial: " + alineacionInicial.toString());
        for (Jugada jugada: jugadas
             ) {
            System.out.println(jugada.toString());
        }
    }
}
