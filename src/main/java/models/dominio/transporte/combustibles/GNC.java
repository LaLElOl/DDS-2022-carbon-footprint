package models.dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("gnc")
public class GNC extends Combustible {

    public GNC(){
        setNombre("gnc");
    }

    public Double obtenerGramos(Double litros){
        return litros*152;
    }
}
