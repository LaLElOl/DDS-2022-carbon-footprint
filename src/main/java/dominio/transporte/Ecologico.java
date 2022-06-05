package dominio.transporte;

import dominio.Ubicacion;
import service.ServicioGeo;
import service.entities.Distancia;

import java.io.IOException;

public class Ecologico implements Transporte {
    public Integer calcularConsumo() {
        return 0;
    }

    public Integer calcularDistancia(Ubicacion ubicacionInicial, Ubicacion ubicacionFinal) throws IOException {
        Distancia distancia = ServicioGeo.getInstancia().distancia(ubicacionInicial.getLocalidad(), ubicacionInicial.getCalle(),
                String.valueOf(ubicacionInicial.getAltura()),ubicacionFinal.getLocalidad(),
                ubicacionFinal.getCalle(),ubicacionFinal.getAltura());
        return new Integer(distancia.valor);
    }
}
