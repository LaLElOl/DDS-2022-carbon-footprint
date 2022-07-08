package dominio.organizacion;

import dominio.persona.Miembro;

import java.util.Date;

public class Solicitud {
    private Date fecha;
    private Miembro solicitante;

    public Miembro getSolicitante() {
        return solicitante;
    }
}
