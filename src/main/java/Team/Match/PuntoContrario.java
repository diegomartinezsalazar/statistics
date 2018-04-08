package Team.Match;

public class PuntoContrario extends Movement {
    public PuntoContrario (String matchId){
        super(matchId);
    }

    @Override
    public String toString() {
        //return super.toString();
        String puntoContrario = "";
        puntoContrario = "Punto contrario";
        return puntoContrario;
    }
}
