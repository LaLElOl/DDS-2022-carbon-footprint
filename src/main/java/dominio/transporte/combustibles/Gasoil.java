package dominio.transporte.combustibles;

public class Gasoil extends Combustible {
    static private Gasoil instancia;

    private Gasoil(){}

    static public Gasoil getInstancia(){
        if(instancia == null){
            instancia = new Gasoil();
        }
        return instancia;
    }
}
