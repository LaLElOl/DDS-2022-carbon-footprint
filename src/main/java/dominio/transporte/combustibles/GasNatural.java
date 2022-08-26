package dominio.transporte.combustibles;

public class GasNatural extends Combustible{

    static private GasNatural instancia;

    private GasNatural(){}

    static public GasNatural getInstancia() {
        if(instancia == null){
            instancia = new GasNatural();
        }
        return instancia;
    }

    static public Double obtenerGramos(Double m3){
        return m3*7;
    }
}
