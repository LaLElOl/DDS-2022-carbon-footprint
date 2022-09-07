package dominio.transporte.vehiculos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("auto")

public class Auto extends TipoVehiculo {

}
