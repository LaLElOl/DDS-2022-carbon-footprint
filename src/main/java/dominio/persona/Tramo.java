package dominio.persona;

import dominio.Ubicacion;
import dominio.transporte.Transporte;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class Tramo {
    private Miembro duenioTramo;
    private Ubicacion finTramo;
    private Ubicacion inicioTramo;
    private Transporte transporte;
    private Boolean compartido;

    public Tramo(Miembro m){
        this.duenioTramo = m;
    }

    public Integer calcularConsumo() {
        return this.transporte.calcularConsumo();
    }

    public Integer calcularDistancia(Miembro miembro) throws IOException {
        return this.transporte.calcularDistancia(this, miembro);
    }
}
