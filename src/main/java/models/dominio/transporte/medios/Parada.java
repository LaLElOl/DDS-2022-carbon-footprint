package models.dominio.transporte.medios;

import models.dominio.EntidadPersistente;
import models.dominio.transporte.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "parada")
public class Parada extends EntidadPersistente {

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id",referencedColumnName = "id")
    private Ubicacion ubicacion;
}
