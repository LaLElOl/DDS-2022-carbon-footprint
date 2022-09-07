package dominio.transporte.medios;

import dominio.EntidadPersistente;
import dominio.transporte.Ubicacion;
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
