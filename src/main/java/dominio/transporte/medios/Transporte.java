package dominio.transporte.medios;

import dominio.EntidadPersistente;
import dominio.persona.Miembro;
import dominio.persona.Tramo;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_transporte")
@Table(name = "transporte")
public abstract class Transporte extends EntidadPersistente {
    public abstract Double calcularConsumo();
    public abstract Double calcularDistancia(Tramo tramo, Miembro miembro) throws IOException;
}

