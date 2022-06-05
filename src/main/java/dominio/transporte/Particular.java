package dominio.transporte;

import dominio.Ubicacion;
import service.ServicioGeo;
import service.entities.Distancia;

import java.io.IOException;

public class Particular implements Transporte {
    private Combustible combustible;
    private TipoVehiculo vehiculo;

    public Integer calcularConsumo() {
        return 0;
    }

    public Integer calcularDistancia(Ubicacion ubicacionInicial, Ubicacion ubicacionFinal) throws IOException {
        Distancia distancia = ServicioGeo.getInstancia().distancia(ubicacionInicial.getLocalidad(), ubicacionInicial.getCalle(),
                                                    String.valueOf(ubicacionInicial.getAltura()),ubicacionFinal.getLocalidad(),
                                                    ubicacionFinal.getCalle(),ubicacionFinal.getAltura());
        return new Integer(distancia.valor);
    }

    public Combustible getCombustible() {
        return combustible;
    }

    public void setCombustible(Combustible combustible) {
        this.combustible = combustible;
    }

    public TipoVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(TipoVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
