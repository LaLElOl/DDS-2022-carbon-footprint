package models.services.mediosNotiicacion;

import models.dominio.persona.Contacto;

public interface MedioNotificacion {

    void notificar(Contacto contacto, String mensaje);
}
