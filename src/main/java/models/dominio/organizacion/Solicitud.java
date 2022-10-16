package models.dominio.organizacion;

import models.dominio.EntidadPersistente;
import models.dominio.persona.Miembro;
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

    @ManyToOne
    @JoinColumn(name = "sector_id", referencedColumnName = "id")
    private Sector sector;

    public Miembro getSolicitante() {
        return solicitante;
    }
}