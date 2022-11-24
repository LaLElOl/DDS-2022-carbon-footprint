package models.dominio.transporte.combustibles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("fuel_oil")
public class FuelOil extends Combustible{

    public FuelOil(){
        setNombre("fuel_oil");
    }

    public Double obtenerGramos(Double litros){
        return litros*850;
    }
}
