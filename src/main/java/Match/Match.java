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

        for (Object objeto: listaMovimientos
                ) {
            if (objeto.getClass() == Convocatoria.class){
                convocatoria = (Convocatoria) objeto;
            }// else if ()
        }
    }
}
