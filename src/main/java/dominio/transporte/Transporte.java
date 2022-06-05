package dominio.transporte;

import dominio.Ubicacion;
import java.io.IOException;

public interface Transporte {
    Integer calcularConsumo();
    Integer calcularDistancia(Ubicacion ubicacionInicial, Ubicacion ubicacionFinal) throws IOException;
}

