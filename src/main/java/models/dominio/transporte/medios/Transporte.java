package models.dominio.transporte.medios;

import lombok.Getter;
import lombok.Setter;
import models.dominio.EntidadPersistente;
import models.dominio.persona.Miembro;
import models.dominio.persona.Tramo;

import javax.persistence.*;
import java.io.IOException;

@Setter
@Getter

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_transporte")
@Table(name = "transporte")
public abstract class Transporte extends EntidadPersistente {
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    public abstract Double calcularConsumo();
    public abstract Double calcularDistancia(Tramo tramo, Miembro miembro) throws IOException;
}

