package models.dominio.transporte.medios;

import models.dominio.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "parada_transporte")
public class ParadasTransporte extends EntidadPersistente {

    @Column(name = "distancia_siguiente")
    private Double distanciaAlaSiguiente;

    @ManyToOne
    @JoinColumn(name = "parada_actual_id",referencedColumnName = "id")
    private Parada paradaActual;

    @OneToOne
    @JoinColumn(name = "parada_siguiente_id",referencedColumnName = "id")
    private ParadasTransporte paradaSiguiente;

    @ManyToOne
    @JoinColumn(name = "transporte_id", referencedColumnName = "id")
    private Publico transportePublico;
}
