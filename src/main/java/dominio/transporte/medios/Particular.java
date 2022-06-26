package dominio.transporte.medios;

import dominio.persona.Miembro;
import dominio.persona.Tramo;
import dominio.transporte.vehiculos.TipoVehiculo;
import dominio.transporte.combustibles.Combustible;
import lombok.Getter;
import lombok.Setter;
import services.distancias.AdapterGeoService;
import services.distancias.Distancia;

import java.io.IOException;

@Getter
@Setter
public class Particular implements Transporte {
    private Combustible combustible;
    private TipoVehiculo vehiculo;
    //private AdapterGeoService servicioDistancia;
    private CalculadorDistanciaAPI calculador = new CalculadorDistanciaAPI();

    public Integer calcularConsumo() {
        return 0;
    }

    public Integer calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {
        //Aca tengo el checkeo para los tramos compartidos
        //TODO: ver como resolver el acoplamiento del tramo
        if(tramo.getCompartido() && tramo.getDuenioTramo() != miembro) return 0;

        return calculador.calcularDistancia(tramo,miembro);
    }
}
