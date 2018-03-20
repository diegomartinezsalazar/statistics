package Match;

import java.util.ArrayList;

public class Match {
    private String matchId;
    private ArrayList<Set> sets = new ArrayList<Set>();
    private ArrayList<Object> listaMovimientos = new ArrayList<Object>();
    private ArrayList<Object> listaMovimientosCompleta = new ArrayList<Object>();

    private Convocatoria convocatoria;

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
        int setsLocal = 0;
        int setsVisitante = 0;
        int setActual = 0;
        int tanteoActualLocal = 0;
        int tanteoActualVisitante = 0;
        int tiemposLocal = 0;
        int tiemposVisitante = 0;
        String equipoLocal = "";
        String equipoVisitante = "";
        Set set = new Set();

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
                set.addErrorContrario((ErrorContrario) objeto);
            } else if (objeto.getClass() == Tanteo.class){
                set.compruebaTanteo((Tanteo) objeto);
            }


            /*


        } else if (esTanteo(typeOfMovement)) {
            // Generar tanteo
            Tanteo tanteo = new Tanteo(matchId, movement);
        } else if (esCambio(typeOfMovement)) {
            // Generar cambio
            Cambio cambio = new Cambio(matchId, movement);
        } else if (esTiempo(typeOfMovement)) {
            // Generar tiempo
            Tiempo tiempo = new Tiempo(matchId, movement);
        } else if (esFinDeSet(typeOfMovement)) {
            // Generar fin de set
            FinDeSet finDeSet = new FinDeSet(matchId, movement);
        } else if (esErrorContrario(typeOfMovement)) {
            // Generar error contrario
            ErrorContrario errorContrario = new ErrorContrario(matchId);
        } else if (esPuntoContrario(typeOfMovement)) {
            // Generar punto contrario
            PuntoContrario puntoContrario = new PuntoContrario(matchId);
        } else if (esSaque(typeOfMovement)) {
            // Generar saque
            Saque saque = new Saque(matchId, movement);
        } else if (esRecepcion(typeOfMovement)) {
            // Generar recepción
            Recepcion recepcion = new Recepcion(matchId, movement);
        } else if (esColocacion(typeOfMovement)) {
            // Generar colocación
            Colocacion colocacion = new Colocacion(matchId, movement);
        } else if (esRemate(typeOfMovement)) {
            // Generar remate
            Remate remate = new Remate(matchId, movement);
        } else if (esBloqueo(typeOfMovement)) {
            // Generar bloqueo
            Bloqueo bloqueo = new Bloqueo(matchId, movement);
        } else if (esDefensa(typeOfMovement)) {
            // Generar defensa
            Defensa defensa = new Defensa(matchId, movement);
        }
            * */


        }
    }
}
