package dominio.transporte;

public class ParadasTransporte {

    private Integer distanciaALaAnterior;
    private Integer distanciaAlaSiguiente;
    private Parada paradaActual;
    private Parada paradaAnterior;
    private Parada paradaSiguiente;

    public Integer getDistanciaALaAnterior() {
        return distanciaALaAnterior;
    }

    public void setDistanciaALaAnterior(Integer distanciaALaAnterior) {
        this.distanciaALaAnterior = distanciaALaAnterior;
    }

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

    public Parada getParadaAnterior() {
        return paradaAnterior;
    }

    public void setParadaAnterior(Parada paradaAnterior) {
        this.paradaAnterior = paradaAnterior;
    }

    public Parada getParadaSiguiente() {
        return paradaSiguiente;
    }

    public void setParadaSiguiente(Parada paradaSiguiente) {
        this.paradaSiguiente = paradaSiguiente;
    }
}
