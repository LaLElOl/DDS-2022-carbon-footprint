package models.dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("lenia")
public class Lenia extends Combustible{

    public Lenia(){
        setNombre("lenia");
    }

    public Double obtenerGramos(Double kilos){
        return kilos/1000;
    }
}
