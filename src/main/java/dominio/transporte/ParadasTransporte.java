package dominio.transporte;

public class ParadasTransporte {

    private Integer distanciaAlaSiguiente;
    private Parada paradaActual;
    private Parada paradaSiguiente;

    public Integer getDistanciaAlaSiguiente() {
        return distanciaAlaSiguiente;
    }

    public void setDistanciaAlaSiguiente(Integer distanciaAlaSiguiente) {
        this.distanciaAlaSiguiente = distanciaAlaSiguiente;
    }

    public Parada getParadaActual() {
        return paradaActual;
    }

    public void setParadaActual(Parada paradaActual) {
        this.paradaActual = paradaActual;
    }

    public Parada getParadaSiguiente() {
        return paradaSiguiente;
    }

    public void setParadaSiguiente(Parada paradaSiguiente) {
        this.paradaSiguiente = paradaSiguiente;
    }
}
