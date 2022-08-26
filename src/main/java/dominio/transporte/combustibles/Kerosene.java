package dominio.transporte.combustibles;

public class Kerosene extends Combustible{

    public Double obtenerGramos(Double litros){
        return litros*817;
    }
}
