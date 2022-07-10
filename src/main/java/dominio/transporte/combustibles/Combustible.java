package dominio.transporte.combustibles;

public abstract class Combustible {

    Double factorEmision;

    public Double consumoCombustible(){
        return factorEmision;
    };
}
