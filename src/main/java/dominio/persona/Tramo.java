package dominio.persona;

import dominio.transporte.Ubicacion;
import dominio.transporte.medios.Transporte;
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

    public Double calcularConsumo() {
        return this.transporte.calcularConsumo();
    }

    public Double calcularDistancia(Miembro miembro) throws IOException {
        return this.transporte.calcularDistancia(this, miembro);
    }

    public Double calcularHuella(Miembro miembro){
        double huella = 0.0;
        try{
            huella = this.calcularConsumo() * this.calcularDistancia(miembro);
        }catch(IOException e){
            e.printStackTrace();
        }
        return huella;
    }
}
