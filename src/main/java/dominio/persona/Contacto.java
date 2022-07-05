package dominio.persona;

import lombok.Getter;
import lombok.Setter;
import services.mediosNotiicacion.MedioNotificacion;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Contacto {

    private String numTelefono;
    private String email;
    private List<MedioNotificacion> mediosNotificacion;

    public Contacto(){
        this.mediosNotificacion = new ArrayList<>();
    }
}
