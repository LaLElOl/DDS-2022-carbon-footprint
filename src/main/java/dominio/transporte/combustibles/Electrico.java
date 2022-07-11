package dominio.transporte.combustibles;

public class Electrico extends Combustible {
    static private Electrico instancia;

    private Electrico(){}

    static public Electrico getInstancia(){
        if(instancia == null){
            instancia = new Electrico();
        }
        return instancia;
    }
}
