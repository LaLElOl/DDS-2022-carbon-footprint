package dominio.persona;

import dominio.Ubicacion;
import dominio.transporte.Transporte;

public class Tramo {
    private Ubicacion finTramo;
    private Ubicacion inicioTramo;
    private Transporte transporte;

    public Integer calcularConsumo() {
        return 0;
    }

    public Integer calcularDistancia() {
        return 0;
    }

    public Ubicacion getFinTramo() {
        return finTramo;
    }

    public void setFinTramo(Ubicacion finTramo) {
        this.finTramo = finTramo;
    }

    public Ubicacion getInicioTramo() {
        return inicioTramo;
    }

    public void setInicioTramo(Ubicacion inicioTramo) {
        this.inicioTramo = inicioTramo;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }
}
