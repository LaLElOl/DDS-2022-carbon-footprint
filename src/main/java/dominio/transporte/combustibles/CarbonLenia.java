package dominio.transporte.combustibles;

public class CarbonLenia extends Combustible{

    public Double obtenerGramos(Double kilos){
        return kilos/1000;
    }
}
