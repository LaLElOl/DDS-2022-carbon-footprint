package dominio.transporte.medios;

import dominio.persona.Miembro;
import dominio.persona.Tramo;
import services.distancias.AdapterGeoService;
import services.distancias.Distancia;

import java.io.IOException;

public class Ecologico implements Transporte {

    //private AdapterGeoService servicioDistancia;
    private CalculadorDistanciaAPI calculador = new CalculadorDistanciaAPI();

    public Integer calcularConsumo() {
        return 0;
    }
    //No uso miembro
    //TODO: bajar acoplamiento de la api, usar adapter para llamarla
    public Integer calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {

        return calculador.calcularDistancia(tramo,miembro);

    }
}
