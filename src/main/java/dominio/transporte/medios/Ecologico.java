package dominio.transporte.medios;

import dominio.persona.Miembro;
import dominio.persona.Tramo;
import services.distancias.AdapterGeoService;
import services.distancias.Distancia;

import java.io.IOException;

public class Ecologico implements Transporte {


    private final CalculadorDistanciaAPI calculador = new CalculadorDistanciaAPI();

    public Double calcularConsumo() {
        return 0.0;
    }
    //No uso miembro
    public Double calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {
        return calculador.calcularDistancia(tramo,miembro);
    }
}
