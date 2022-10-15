package models.dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("electrico")
public class Electrico extends Combustible {

    public Double obtenerGramos(Double kwh){
        //TODO: INCHEQUEABLE
        return kwh*122;
    }
}
