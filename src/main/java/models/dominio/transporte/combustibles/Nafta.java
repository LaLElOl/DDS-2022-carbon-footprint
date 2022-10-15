package models.dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("nafta")
public class Nafta extends Combustible {

    public Double obtenerGramos(Double litros){
        return litros*850;
    }
}
