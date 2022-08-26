package dominio.transporte.combustibles;

public class Electrico extends Combustible {

    public Double obtenerGramos(Double kwh){
        //TODO: INCHEQUEABLE
        return kwh*122;
    }

}
