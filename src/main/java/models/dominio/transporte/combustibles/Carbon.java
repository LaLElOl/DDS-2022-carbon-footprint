package models.dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("carbon")
public class Carbon extends Combustible{

    public Carbon(){
        setNombre("carbon");
    }

    public Double obtenerGramos(Double kilos){
        return kilos/1000;
    }
}
