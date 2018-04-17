package com.mycompany.sportstats.Team.Match;

import com.mycompany.sportstats.Team.Match.Skill.*;

import java.util.ArrayList;
import java.util.Objects;

public class Set {
    private Alineacion alineacionInicial;
    private Alineacion alineacionActual = new Alineacion();
    private ArrayList<Object> tiempos;
    private ArrayList<Jugada> jugadas;
    private ArrayList<Cambio> cambios;
    private int puntosNuestros = 0;
    private int puntosSuyos = 0;
    private boolean saqueInicialFavor = false;
    private boolean saqueActualFavor = false;
    private Jugada jugadaActual;
    private boolean setTerminado = false;
    private int numSet = 0;
    private boolean setGanado = false;

    public Set (){
        setJugadas(new ArrayList<Jugada>());
        tiempos = new ArrayList<>();
        cambios = new ArrayList<>();
        inicializaJugada();
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
        ArrayList<Integer> alineacionAux = new ArrayList<>(alineacionActual.getJugadoresAlineacion());

        aux = alineacionAux.get(0);
        for (int i = 1; i < 6; i++){
            alineacionAux.set(i-1, alineacionAux.get(i));
        }
        alineacionAux.set(5, aux);
        alineacionActual.setJugadoresAlineacion(alineacionAux);
    }

    public void terminaJugada (){
        jugadaActual.setNuestroTanteoFinal(getPuntosNuestros());
        jugadaActual.setSuTanteoFinal(getPuntosSuyos());
        getJugadas().add(jugadaActual);

    }

    public void inicializaJugada(){
        jugadaActual = new Jugada();
        jugadaActual.setAlineacion(alineacionActual);
        jugadaActual.setNuestroTanteoInicial(getPuntosNuestros());
        jugadaActual.setSuTanteoInicial(getPuntosSuyos());
    }

    public void puntoNuestro(){
        setPuntosNuestros(getPuntosNuestros() + 1);
        if (!saqueActualFavor){
            rotacion();
            saqueActualFavor = true;
        }
        terminaJugada();
        terminaSet();
        inicializaJugada();
    }

    public void puntoSuyo(){
        setPuntosSuyos(getPuntosSuyos() + 1);
        saqueActualFavor = false;
        terminaJugada();
        terminaSet();
        inicializaJugada();
    }

    public void compruebaTanteo (Tanteo tanteo){
        if ((tanteo.getNuestroTanteo() != getPuntosNuestros()) || (tanteo.getSuTanteo() != getPuntosSuyos())) {
            System.out.println("Tanteo momentáneo incorrecto");
        }
    }

    public void addCambio (Cambio cambio){
        cambio.setNuestroTanteo(puntosNuestros);
        cambio.setSuTanteo(puntosSuyos);
        cambios.add(cambio);
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
            if ((getPuntosNuestros() ==15) && (getPuntosSuyos() <=13)){
                setSetTerminado(true);
                setSetGanado(true);
            } else if ((getPuntosSuyos() ==15) && (getPuntosNuestros() <=13)){
                setSetTerminado(true);
                setSetGanado(false);
            } else if (((getPuntosSuyos() >15) || (getPuntosNuestros() >15)) && (Math.abs(getPuntosSuyos() - getPuntosNuestros()) >= 2)){
                setSetTerminado(true);
                setSetGanado(getPuntosNuestros() > getPuntosSuyos());
            }
        } else {
            if ((getPuntosNuestros() ==25) && (getPuntosSuyos() <=23)){
                setSetTerminado(true);
                setSetGanado(true);
            } else if ((getPuntosSuyos() ==25) && (getPuntosNuestros() <=23)){
                setSetTerminado(true);
                setSetGanado(false);
            } else if (((getPuntosSuyos() >25) || (getPuntosNuestros() >25)) && (Math.abs(getPuntosSuyos() - getPuntosNuestros()) >= 2)){
                setSetTerminado(true);
                setSetGanado(getPuntosNuestros() > getPuntosSuyos());
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

    /*public void printSet(){
        System.out.println("Comienzo Set " + numSet);
        System.out.println ("Alineación inicial: " + alineacionInicial.toString());
        for (Jugada jugada: jugadas
             ) {
            System.out.println(jugada.toString());
        }
    }*/

    @Override
    public String toString() {
        String set;

        set = "Comienzo Set " + numSet + "\n";
        set += "Alineación inicial: " + alineacionInicial.toString() + "\n";
        for (Jugada jugada: getJugadas()
            ) {
            set += jugada.toString() + "\n";
        }

        return set;
    }

    public int getPuntosNuestros() {
        return puntosNuestros;
    }

    public void setPuntosNuestros(int puntosNuestros) {
        this.puntosNuestros = puntosNuestros;
    }

    public int getPuntosSuyos() {
        return puntosSuyos;
    }

    public void setPuntosSuyos(int puntosSuyos) {
        this.puntosSuyos = puntosSuyos;
    }

    public ArrayList<Jugada> getJugadas() {
        return jugadas;
    }

    public void setJugadas(ArrayList<Jugada> jugadas) {
        this.jugadas = jugadas;
    }

    public ArrayList<Cambio> getCambios() {
        return cambios;
    }
}
