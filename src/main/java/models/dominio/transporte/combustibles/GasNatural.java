package models.dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("gas_natural")
public class GasNatural extends Combustible{

    public GasNatural(){
        setNombre("gas_natural");
    }

    public Double obtenerGramos(Double m3){
        return m3*7;
    }
}
