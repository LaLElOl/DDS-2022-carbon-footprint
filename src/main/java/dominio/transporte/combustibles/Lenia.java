package dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("lenia")
public class Lenia extends Combustible{

    public Double obtenerGramos(Double kilos){
        return kilos/1000;
    }
}
