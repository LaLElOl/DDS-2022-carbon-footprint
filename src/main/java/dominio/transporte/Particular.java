package dominio.transporte;

public class Particular implements Transporte {
    private Combustible combustible;
    private TipoVehiculo vehiculo;

    public Integer calcularConsumo() {
        return 0;
    }

    public Combustible getCombustible() {
        return combustible;
    }

    public void setCombustible(Combustible combustible) {
        this.combustible = combustible;
    }

    public TipoVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(TipoVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
