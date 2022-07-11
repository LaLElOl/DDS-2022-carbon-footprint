package dominio.transporte.combustibles;

import lombok.Setter;

public abstract class Combustible {
    @Setter
    private Double factorEmision;

    public Double consumoCombustible(){
        return factorEmision;
    }
}
