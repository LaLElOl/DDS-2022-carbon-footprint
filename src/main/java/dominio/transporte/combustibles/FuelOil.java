package dominio.transporte.combustibles;

public class FuelOil extends Combustible{

    public Double obtenerGramos(Double litros){
        return litros*850;
    }
}
