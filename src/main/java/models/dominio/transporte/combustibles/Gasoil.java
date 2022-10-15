package models.dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("gasoil")
public class Gasoil extends Combustible {

    public Double obtenerGramos(Double litros){
        return litros*850;
    }
}
