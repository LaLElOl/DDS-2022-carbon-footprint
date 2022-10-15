package models.dominio.transporte.medios;

import models.dominio.persona.Miembro;
import models.dominio.persona.Tramo;
import lombok.Getter;
import lombok.Setter;

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
