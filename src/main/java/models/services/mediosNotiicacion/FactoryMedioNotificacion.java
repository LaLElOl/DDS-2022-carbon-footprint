package models.services.mediosNotiicacion;

public class FactoryMedioNotificacion {

    public static MedioNotificacion obtenerMedioNotificacion(EMedioNotificacion medio){
        switch (medio){

            case WHATSAPP:
                return new Whatsapp();
            case EMAIL:
                return new Email();
            default:
                return null;
        }
    }
}
