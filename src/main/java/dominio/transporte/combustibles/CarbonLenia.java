package dominio.transporte.combustibles;

public class CarbonLenia extends Combustible{
    static private CarbonLenia instancia;

    private CarbonLenia(){}

    static public CarbonLenia getInstancia(){
        if(instancia == null){
            instancia = new CarbonLenia();
        }
        return instancia;
    }

    static public Double obtenerGramos(Double kilos){
        return kilos/1000;
    }
}
