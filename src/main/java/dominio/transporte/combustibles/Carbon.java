package dominio.transporte.combustibles;

public class Carbon extends Combustible{
    static private Carbon instancia;

    private Carbon(){}

    static public Carbon getInstancia(){
        if(instancia == null){
            instancia = new Carbon();
        }
        return instancia;
    }

    static public Double obtenerGramos(Double kilos){
        return kilos/1000;
    }
}
