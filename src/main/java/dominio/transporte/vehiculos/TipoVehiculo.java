package dominio.transporte.vehiculos;

import dominio.transporte.medios.Particular;

public interface TipoVehiculo {
    public Integer calcularConsumoParticular(Particular particular);
}

