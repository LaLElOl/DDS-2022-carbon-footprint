package dominio.transporte.medios;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParadasTransporte {
    private Double distanciaAlaSiguiente;
    private Parada paradaActual;
    private Parada paradaSiguiente;//Cambiar a tipo ParadaTransporte
    private ParadasTransporte paradaTransporteSiguiente;//algo asi
}
