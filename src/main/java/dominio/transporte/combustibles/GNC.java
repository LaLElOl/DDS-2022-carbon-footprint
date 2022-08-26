package dominio.transporte.combustibles;

public class GNC extends Combustible {
    static private GNC instancia;

    private GNC(){}

    static public GNC getInstancia(){
        if(instancia == null){
            instancia = new GNC();
        }
        return instancia;
    }

    static public Double obtenerGramos(Double litros){
        return litros*152;
    }
}
