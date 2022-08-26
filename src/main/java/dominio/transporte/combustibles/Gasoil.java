package dominio.transporte.combustibles;

public class Gasoil extends Combustible {

    public Double obtenerGramos(Double litros){
        return litros*850;
    }
}
