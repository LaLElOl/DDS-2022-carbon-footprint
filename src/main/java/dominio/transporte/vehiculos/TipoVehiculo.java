package dominio.transporte.vehiculos;

import dominio.transporte.combustibles.Combustible;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class TipoVehiculo {

    protected Double factorVehiculo;
    private Combustible combustible;

    public Double calcularConsumoParticular() {

        return this.factorVehiculo * this.combustible.getFactorEmision();
    }
}
