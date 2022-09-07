package dominio.transporte.vehiculos;

import dominio.EntidadPersistente;
import dominio.transporte.combustibles.Combustible;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table (name = "vehiculo")
@DiscriminatorColumn(name = "tipo")
public abstract class TipoVehiculo extends EntidadPersistente {

    @Column (name = "factor_vehiculo")
    protected Double factorVehiculo;

    @Transient
    private Combustible combustible;

    public Double calcularConsumoParticular() {

        return this.factorVehiculo * this.combustible.getFactorEmision();
    }
}
