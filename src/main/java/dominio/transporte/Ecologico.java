package dominio.transporte;

import dominio.persona.Miembro;
import dominio.persona.Tramo;
import service.RetrofitServicioGeo;
import service.entities.Distancia;

import java.io.IOException;

public class Ecologico implements Transporte {
    public Integer calcularConsumo() {
        return 0;
    }
    //No uso miembro
    //TODO: bajar acoplamiento de la api, usar adapter para llamarla
    public Integer calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {
        Distancia distancia = RetrofitServicioGeo.getInstancia().distancia(tramo.getInicioTramo().getLocalidad(), tramo.getInicioTramo().getCalle(),
                String.valueOf(tramo.getInicioTramo().getAltura()),tramo.getFinTramo().getLocalidad(),
                tramo.getFinTramo().getCalle(),tramo.getFinTramo().getAltura());
        return new Integer(distancia.valor);
    }
}
