package dominio.transporte.combustibles;

public class Lenia extends Combustible{

    public Double obtenerGramos(Double kilos){
        return kilos/1000;
    }
}
