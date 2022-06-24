package dominio.transporte.medios;

import dominio.persona.Miembro;
import dominio.persona.Tramo;

import java.io.IOException;

public interface Transporte {
    Integer calcularConsumo();
    Integer calcularDistancia(Tramo tramo, Miembro miembro) throws IOException;
}

