package dominio.transporte.medios;

import dominio.persona.Miembro;
import dominio.persona.Tramo;
import services.distancias.AdapterGeoService;
import services.distancias.Distancia;

import java.io.IOException;

public class ServicioContratado implements Transporte {
    //private AdapterGeoService servicioDistancia;
    private CalculadorDistanciaAPI calculador = new CalculadorDistanciaAPI();

    public Integer calcularConsumo() {
        return null;
    }

    public Integer calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {
        //Aca tengo el checkeo para los tramos compartidos
        //TODO: ver como resolver el acoplamiento del tramo
        //TODO: bajar acoplamiento de la api, usar adapter para llamarla
        if(tramo.getCompartido() && tramo.getDuenioTramo() != miembro) return 0;
        return calculador.calcularDistancia(tramo,miembro);

    }
}
