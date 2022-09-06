package dominio.organizacion;

import dominio.EntidadPersistente;
import dominio.persona.Miembro;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter

@Entity
@Table(name = "solicitud")

public class Solicitud extends EntidadPersistente {

    @Column(name = "fecha", columnDefinition = "DATE")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "solicitante_id", referencedColumnName = "id")
    private Miembro solicitante;

    public Miembro getSolicitante() {
        return solicitante;
    }
}
