package dominio.transporte.combustibles;

public class FuelOil extends Combustible{
    static private FuelOil instancia;

    private FuelOil(){}

    static public FuelOil getInstancia(){
        if(instancia == null){
            instancia = new FuelOil();
        }
        return instancia;
    }

    static public Double obtenerGramos(Double litros){
        return litros*850;
    }
}
