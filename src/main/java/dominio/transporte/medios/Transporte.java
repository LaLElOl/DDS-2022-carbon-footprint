package dominio.transporte.medios;

import dominio.EntidadPersistente;
import dominio.persona.Miembro;
import dominio.persona.Tramo;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.IOException;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Transporte extends EntidadPersistente {
    public abstract Double calcularConsumo();
    public abstract Double calcularDistancia(Tramo tramo, Miembro miembro) throws IOException;
}

