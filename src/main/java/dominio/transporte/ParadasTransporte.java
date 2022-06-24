package dominio.transporte;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParadasTransporte {
    private Integer distanciaAlaSiguiente;
    private Parada paradaActual;
    private Parada paradaSiguiente;
}
