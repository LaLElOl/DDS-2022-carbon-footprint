package dominio.transporte.combustibles;

public class GasNatural extends Combustible{

    public Double obtenerGramos(Double m3){
        return m3*7;
    }
}
