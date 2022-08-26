package dominio.transporte.combustibles;

public class GNC extends Combustible {

    public Double obtenerGramos(Double litros){
        return litros*152;
    }
}
