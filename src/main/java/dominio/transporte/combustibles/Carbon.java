package dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("carbon")
public class Carbon extends Combustible{

    public Double obtenerGramos(Double kilos){
        return kilos/1000;
    }
}
