package models.dominio.organizacion.datos;

public class FactoryPeriodicidad {

    public static Periodicidad obtenerPeriodicidad(EPeriodicidad ePeriodicidad){
        switch (ePeriodicidad){
            case MENSUAL:
                return new Mensual();
            case ANUAL:
                return new Anual();
            default:
                return null;
        }
    }
}
