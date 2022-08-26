package dominio.transporte.combustibles;

public class Nafta extends Combustible {
    static private Nafta instancia;

    private Nafta(){}

    static public Nafta getInstancia(){
        if(instancia == null){
            instancia = new Nafta();
        }
        return instancia;
    }

    static public Double obtenerGramos(Double litros){
        return litros*850;
    }
}
