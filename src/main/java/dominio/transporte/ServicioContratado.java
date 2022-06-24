package dominio.transporte;

import dominio.Ubicacion;
import dominio.persona.Miembro;
import dominio.persona.Tramo;
import service.RetrofitServicioGeo;
import service.entities.Distancia;

import java.io.IOException;

public class ServicioContratado implements Transporte {
    public Integer calcularConsumo() {
        return null;
    }

    public Integer calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {
        //Aca tengo el checkeo para los tramos compartidos
        //TODO: ver como resolver el acoplamiento del tramo
        if(tramo.getCompartido() && tramo.getDuenioTramo() != miembro) return 0;
        Distancia distancia = RetrofitServicioGeo.getInstancia().distancia(
                tramo.getInicioTramo().getLocalidad(),
                tramo.getInicioTramo().getCalle(),
                String.valueOf(tramo.getInicioTramo().getAltura()),
                tramo.getFinTramo().getLocalidad(),
                tramo.getFinTramo().getCalle(),
                tramo.getFinTramo().getAltura());
        return new Integer(distancia.valor);
    }
}
