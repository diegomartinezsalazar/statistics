package Match;

public class CambioBuilder {
    private int nuestroTanteo;
    private int suTanteo;
    private int entra;
    private int seRetira;

    public Cambio build(){
        return new Cambio(this);
    }

    public CambioBuilder withNuestoTanteo(int nuestroTanteo){
        this.nuestroTanteo = nuestroTanteo;
        return this;
    }

    public CambioBuilder withSuTanteo(int suTanteo){
        this.suTanteo = suTanteo;
        return this;
    }

    public CambioBuilder withEntra(int entra){
        this.entra = entra;
        return this;
    }

    public CambioBuilder withSeRetira(int seRetira){
        this.seRetira = seRetira;
        return this;
    }

    public int getNuestroTanteo(){
        return nuestroTanteo;
    }

    public void setNuestroTanteo(int nuestroTanteo){
        this.nuestroTanteo = nuestroTanteo;
    }

    public int getSuTanteo() {
        return suTanteo;
    }

    public void setSuTanteo(int suTanteo) {
        this.suTanteo = suTanteo;
    }

    public int getEntra() {
        return entra;
    }

    public void setEntra(int entra) {
        this.entra = entra;
    }

    public int getSeRetira() {
        return seRetira;
    }

    public void setSeRetira(int seRetira) {
        this.seRetira = seRetira;
    }
}
