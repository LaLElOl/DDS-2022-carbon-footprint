package dominio.transporte;

import dominio.persona.Miembro;
import dominio.persona.Tramo;
import service.AdapterGeoService;
import service.RetrofitServicioGeo;
import service.entities.Distancia;

import java.io.IOException;

public class ServicioContratado implements Transporte {
    private AdapterGeoService servicioDistancia;

    public Integer calcularConsumo() {
        return null;
    }

    public Integer calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {
        //Aca tengo el checkeo para los tramos compartidos
        //TODO: ver como resolver el acoplamiento del tramo
        //TODO: bajar acoplamiento de la api, usar adapter para llamarla
        if(tramo.getCompartido() && tramo.getDuenioTramo() != miembro) return 0;
        Distancia distancia = this.servicioDistancia.distancia(
                tramo.getInicioTramo().getLocalidad(),
                tramo.getInicioTramo().getCalle(),
                String.valueOf(tramo.getInicioTramo().getAltura()),
                tramo.getFinTramo().getLocalidad(),
                tramo.getFinTramo().getCalle(),
                tramo.getFinTramo().getAltura());
        return new Integer(distancia.valor);
    }
}
