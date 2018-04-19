package com.mycompany.sportstats.Team.Match;

import com.mycompany.sportstats.Team.Match.Skill.Skill;

import java.util.ArrayList;

/**
 * Esta es la clase principal del partido
 *
 * @author Diego Martínez Salazar
 * @version 1.0
 */
public class Match {
    private NuestroEquipo nuestroEquipo;
    private EquipoContrario equipoContrario;
    private boolean enCasa;
    private String matchId;
    private ArrayList<Set> sets = new ArrayList<Set>();
    private ArrayList<Object> listaMovimientos = new ArrayList<Object>();
    private Convocatoria convocatoria;
    private int setsAFavor = 0;
    private int setsEnContra = 0;
    private boolean partidoTerminado = false;
    private boolean partidoGanado = false;

    public Convocatoria getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }

    public ArrayList<Object> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setListaMovimientos(ArrayList<Object> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    public void addMovimiento (Object movimiento){
        listaMovimientos.add(movimiento);
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public ArrayList<Set> getSets() {
        return sets;
    }

    public void setSets(ArrayList<Set> sets) {
        this.sets = sets;
    }

    public void startMatch(){
        Set set = new Set();
        int numSet = 1;
        set.setNumSet(numSet);

        for (Object objeto: listaMovimientos
                ) {
            if (objeto.getClass() == Convocatoria.class){
                convocatoria = (Convocatoria) objeto;
            } else if (objeto.getClass() == Alineacion.class) {
                Alineacion alineacion = (Alineacion) objeto;
                set.setAlineacion(alineacion);
            } else if (objeto instanceof Skill){
                //Significa que es parte de una jugada, así que la agrego a la jugada
                set.addJugada(objeto);
            } else if (objeto.getClass() == PuntoContrario.class){
                set.addPuntoContrario((PuntoContrario) objeto);
            } else if (objeto.getClass() == ErrorContrario.class){
                set.addErrorContrario((ErrorContrario) objeto);
            } else if (objeto.getClass() == Tanteo.class){
                set.compruebaTanteo((Tanteo) objeto);
            } else if (objeto.getClass() == Cambio.class){
                set.addCambio((Cambio) objeto);
            } else if (objeto.getClass() == Tiempo.class){
                set.addTimeOut((Tiempo) objeto);
            } else if (objeto.getClass() == FinDeSet.class){
                //set.addTimeOut((Tiempo) objeto);
            } else if (objeto.getClass() == NuestroEquipo.class){
                nuestroEquipo = (NuestroEquipo) objeto;
            } else if (objeto.getClass() == EquipoContrario.class) {
                equipoContrario = (EquipoContrario) objeto;
            } else if (objeto.getClass() == Campo.class) {
                enCasa = ((Campo) objeto).isEnCasa();
            }


            if (set.isSetTerminado() == true){
                //System.out.println(set.toString());
                sets.add(set);
                calculaEstadoPartido();
                if (!partidoTerminado) {
                    set = new Set();
                    numSet += 1;
                    set.setNumSet(numSet);
                }
            }
            /*
        } else if (esFinDeSet(typeOfMovement)) {
            // Generar fin de set
            FinDeSet finDeSet = new FinDeSet(matchId, movement);
            * */
        }
        System.out.println(toString());
    }

    public void calculaEstadoPartido(){
        setsAFavor = 0;
        setsEnContra = 0;
        for (Set set: sets
             ) {
            if (set.isSetGanado()){
                setsAFavor += 1;
            } else {
                setsEnContra += 1;
            }
        }

        partidoGanado = (setsAFavor == 3?true:false);
        partidoTerminado = ((setsAFavor == 3) || (setsEnContra == 3))?true:false;
    }

    public int getSetsAFavor() {
        calculaEstadoPartido();
        return setsAFavor;
    }

    public int getSetsEnContra() {
        calculaEstadoPartido();
        return setsEnContra;
    }

    public boolean isPartidoGanado() {
        return partidoGanado;
    }

    public boolean isPartidoTerminado() {
        return partidoTerminado;
    }

    public void setPartidoTerminado(boolean partidoTerminado) {
        this.partidoTerminado = partidoTerminado;
    }

    /*public void printSet(Set set){
        set.printSet();
    }*/

    @Override
    public String toString(){
        String match;
        String resumenSets = "";
        match = "COMIENZO DEL PARTIDO " + matchId + "\n";
        match += "Convocatoria: " + convocatoria.toString() + "\n\n";
        for (Set set: sets
             ) {
            match += "\n" + set.toString();
            resumenSets += "Set " + set.getNumSet() + ": " + set.getPuntosNuestros() + "-" + set.getPuntosSuyos() + "\n";
        }
        match += "\nResultado: " + setsAFavor + "-" + setsEnContra + "\n";
        match += resumenSets;
        match += "\n";
        match += "FINAL DEL PARTIDO";
        return match;
    }

    public NuestroEquipo getNuestroEquipo() {
        return nuestroEquipo;
    }

    public void setNuestroEquipo(NuestroEquipo nuestroEquipo) {
        this.nuestroEquipo = nuestroEquipo;
    }

    public EquipoContrario getEquipoContrario() {
        return equipoContrario;
    }

    public void setEquipoContrario(EquipoContrario equipoContrario) {
        this.equipoContrario = equipoContrario;
    }

    public boolean isEnCasa() {
        return enCasa;
    }

    public void setEnCasa(boolean enCasa) {
        this.enCasa = enCasa;
    }
}
