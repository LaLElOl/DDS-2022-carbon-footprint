package dominio.transporte.medios;

import dominio.transporte.Ubicacion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Parada {
    private String nombre;
    private Ubicacion ubicacion;
}
