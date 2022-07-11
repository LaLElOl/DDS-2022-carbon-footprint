package dominio.transporte.combustibles;

public class Kerosene extends Combustible{
    static private Kerosene instancia;

    private Kerosene(){}

    static public Kerosene getInstancia(){
        if(instancia == null){
            instancia = new Kerosene();
        }
        return instancia;
    }
}
