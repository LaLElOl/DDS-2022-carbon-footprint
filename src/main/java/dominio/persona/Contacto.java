package dominio.persona;

import dominio.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
import services.mediosNotiicacion.EMedioNotificacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "contacto")

public class Contacto extends EntidadPersistente {

    @Column(name = "num_telefono")
    private String numTelefono;

    @Column(name = "email")
    private String email;

    //TODO: ver como mapear la lista de enums
    @Transient
    private List<EMedioNotificacion> mediosNotificacion;

    public Contacto(){
        this.mediosNotificacion = new ArrayList<>();
    }
}
