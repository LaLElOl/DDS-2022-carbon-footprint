package dominio.transporte.combustibles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Combustible {

    private Double factorEmision;

    abstract public Double obtenerGramos(Double kilos);
}
