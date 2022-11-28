package models.dominio.transporte.medios;

import models.dominio.persona.Miembro;
import models.dominio.persona.Tramo;
import models.services.distancias.AdapterGeoService;
import models.services.distancias.Distancia;
import models.services.distancias.RetrofitServicioGeo;

import java.io.IOException;



public class CalculadorDistanciaAPI {

    private AdapterGeoService servicioDistancia;


    public CalculadorDistanciaAPI(){
        servicioDistancia = new RetrofitServicioGeo();
    }


    public Double calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {
        Distancia d = servicioDistancia.distancia(
                tramo.getInicioTramo().getLocalidad(),
                tramo.getInicioTramo().getCalle(),
                String.valueOf(tramo.getInicioTramo().getAltura()),
                tramo.getFinTramo().getLocalidad(),
                tramo.getFinTramo().getCalle(),
                tramo.getFinTramo().getAltura());
        return new Double(d.getValor());
    }
}
