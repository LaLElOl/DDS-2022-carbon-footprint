package models.dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("kerosene")
public class Kerosene extends Combustible{

    public Double obtenerGramos(Double litros){
        return litros*817;
    }
}
