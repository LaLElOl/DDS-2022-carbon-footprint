package dominio.transporte;

public class Particular implements Transporte {
    private Combustible combustible;
    private TipoVehiculo vehiculo;

    public Integer calcularConsumo() {
        return 0;
    }
}
