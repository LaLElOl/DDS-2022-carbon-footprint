package dominio.transporte.medios;

import dominio.persona.Miembro;
import dominio.persona.Tramo;
import services.distancias.AdapterGeoService;
import services.distancias.Distancia;

import java.io.IOException;



public class CalculadorDistanciaAPI {

    private AdapterGeoService servicioDistancia;


    public Integer calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {
        Distancia d = servicioDistancia.distancia(
                tramo.getInicioTramo().getLocalidad(),
                tramo.getInicioTramo().getCalle(),
                String.valueOf(tramo.getInicioTramo().getAltura()),
                tramo.getFinTramo().getLocalidad(),
                tramo.getFinTramo().getCalle(),
                tramo.getFinTramo().getAltura());
        return new Integer(d.getValor());
    }
}
