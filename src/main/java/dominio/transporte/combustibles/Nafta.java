package dominio.transporte.combustibles;

public class Nafta extends Combustible {

    public Double obtenerGramos(Double litros){
        return litros*850;
    }
}
