package models.dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("kerosene")
public class Kerosene extends Combustible{

    public Kerosene(){
        setNombre("kerosene");
    }

    public Double obtenerGramos(Double litros){
        return litros*817;
    }
}
