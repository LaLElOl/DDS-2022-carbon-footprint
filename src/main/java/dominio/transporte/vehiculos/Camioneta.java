package dominio.transporte.vehiculos;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("camioneta")

public class Camioneta extends TipoVehiculo {


}
