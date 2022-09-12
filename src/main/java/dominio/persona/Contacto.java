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

    @ElementCollection(targetClass = EMedioNotificacion.class)
    @JoinTable(name = "medio_notificacion", joinColumns = @JoinColumn(name = "contacto_id"))
    @Column(name = "medio_notificacion")
    @Enumerated(EnumType.STRING)
    private List<EMedioNotificacion> mediosNotificacion;

    public Contacto(){
        this.mediosNotificacion = new ArrayList<>();
    }
}
