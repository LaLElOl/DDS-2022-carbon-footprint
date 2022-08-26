package dominio.transporte.combustibles;

import java.time.LocalDate;

public class Carbon extends Combustible{

    public Double obtenerGramos(Double kilos){
        return kilos/1000;
    }
}
