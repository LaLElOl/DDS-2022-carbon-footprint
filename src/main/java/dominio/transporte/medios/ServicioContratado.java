package dominio.transporte.medios;

import dominio.persona.Miembro;
import dominio.persona.Tramo;
import services.distancias.AdapterGeoService;
import services.distancias.Distancia;

import java.io.IOException;

public class ServicioContratado implements Transporte {

    private CalculadorDistanciaAPI calculador = new CalculadorDistanciaAPI();
    private Double factorEmision = 3.0;

    public Double calcularConsumo() {
        return this.factorEmision;
    }

    public Double calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {
        //Aca tengo el checkeo para los tramos compartidos
        //TODO: ver como resolver el acoplamiento del tramo
        if(tramo.getCompartido() && tramo.getDuenioTramo() != miembro) return 0.0;
        return calculador.calcularDistancia(tramo,miembro);

    }
}
