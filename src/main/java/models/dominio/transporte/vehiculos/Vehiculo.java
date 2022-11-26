package models.dominio.transporte.vehiculos;

import models.dominio.EntidadPersistente;
import models.dominio.persona.Miembro;
import models.dominio.transporte.combustibles.Combustible;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table (name = "vehiculo")
public class Vehiculo extends EntidadPersistente {

    @Column (name = "nombre")
    protected String nombre;

    @Column (name = "factor_vehiculo")
    protected Double factorVehiculo;

    @Enumerated(EnumType.STRING)
    @Column (name = "tipo_vehiculo")
    protected TipoVehiculo tipoVehiculo;

    @ManyToOne
    @JoinColumn(name = "combustible_id", referencedColumnName = "id")
    private Combustible combustible;

    @ManyToOne
    @JoinColumn(name = "miembro_id", referencedColumnName = "id")
    private Miembro miembro;

    public Double calcularConsumoParticular() {
        return this.factorVehiculo * this.combustible.getFactorEmision();
    }
}
