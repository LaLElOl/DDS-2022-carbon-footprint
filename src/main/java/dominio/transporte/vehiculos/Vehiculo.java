package dominio.transporte.vehiculos;

import dominio.EntidadPersistente;
import dominio.transporte.combustibles.Combustible;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table (name = "vehiculo")
public class Vehiculo extends EntidadPersistente {

    @Column (name = "factor_vehiculo")
    protected Double factorVehiculo;

    @Enumerated(EnumType.STRING)
    @Column (name = "tipo_vehiculo")
    protected TipoVehiculo tipoVehiculo;

    @Transient
    private Combustible combustible;

    public Double calcularConsumoParticular() {
        return this.factorVehiculo * this.combustible.getFactorEmision();
    }
}
