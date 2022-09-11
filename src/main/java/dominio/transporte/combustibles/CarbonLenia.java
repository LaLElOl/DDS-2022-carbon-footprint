package dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("carbon_lenia")
public class CarbonLenia extends Combustible{

    public Double obtenerGramos(Double kilos){
        return kilos/1000;
    }
}
