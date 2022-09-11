package dominio.transporte.medios;

import dominio.persona.Miembro;
import dominio.persona.Tramo;
import lombok.Getter;
import lombok.Setter;
import services.distancias.AdapterGeoService;
import services.distancias.Distancia;

import javax.persistence.*;
import java.io.IOException;
@Setter
@Getter

@Entity
@DiscriminatorValue("ecologico")
public class Ecologico extends Transporte {

    @Transient
    private final CalculadorDistanciaAPI calculador = new CalculadorDistanciaAPI();

    public Double calcularConsumo() {
        return 0.0;
    }
    //No uso miembro

    public Double calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {
        return calculador.calcularDistancia(tramo,miembro);
    }
}
