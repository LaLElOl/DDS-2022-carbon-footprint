package dominio.transporte.vehiculos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("moto")

public class Moto extends TipoVehiculo {


}
