package dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("fuel_oil")
public class FuelOil extends Combustible{

    public Double obtenerGramos(Double litros){
        return litros*850;
    }
}
