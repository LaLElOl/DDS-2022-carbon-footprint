package dominio.persona;

import dominio.Ubicacion;
import dominio.transporte.Transporte;

import java.io.IOException;

public class Tramo {
    private Miembro duenioTramo;
    private Ubicacion finTramo;
    private Ubicacion inicioTramo;
    private Transporte transporte;

    public Tramo(Miembro m){
        this.duenioTramo = m;
    }

    public Miembro getDuenioTramo() {
        return duenioTramo;
    }

    public Integer calcularConsumo() {
        return 0;
    }

    public Integer calcularDistancia(Miembro miembro) throws IOException {
        if(miembro == duenioTramo) return this.transporte.calcularDistancia(this.inicioTramo,this.finTramo);
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
