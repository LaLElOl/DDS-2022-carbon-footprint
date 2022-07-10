package dominio.transporte.medios;

import dominio.persona.Miembro;
import dominio.persona.Tramo;
import dominio.transporte.vehiculos.TipoVehiculo;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class Particular implements Transporte {
    private TipoVehiculo vehiculo;
    private CalculadorDistanciaAPI calculador = new CalculadorDistanciaAPI();

    public Double calcularConsumo() {
        return this.vehiculo.calcularConsumoParticular();
    }

    public Double calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {
        //Aca tengo el checkeo para los tramos compartidos
        //TODO: ver como resolver el acoplamiento del tramo
        if(tramo.getCompartido() && tramo.getDuenioTramo() != miembro) return 0.0;
        return calculador.calcularDistancia(tramo,miembro);
    }
}
