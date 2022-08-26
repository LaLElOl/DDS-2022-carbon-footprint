package dominio.transporte.combustibles;

public class Lenia extends Combustible{
    static private Lenia instancia;

    private Lenia(){}

    static public Lenia getInstancia(){
        if(instancia == null){
            instancia = new Lenia();
        }
        return instancia;
    }

    static public Double obtenerGramos(Double kilos){
        return kilos/1000;
    }
}
