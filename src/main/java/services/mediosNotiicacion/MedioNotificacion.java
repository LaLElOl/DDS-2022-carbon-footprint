package services.mediosNotiicacion;

import dominio.persona.Contacto;

public interface MedioNotificacion {

    void notificar(Contacto contacto, String mensaje);
}
