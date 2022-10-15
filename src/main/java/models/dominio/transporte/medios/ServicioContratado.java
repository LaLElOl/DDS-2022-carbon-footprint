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
@DiscriminatorValue("servicio_contratado")
public class ServicioContratado extends Transporte {

    @Transient
    private CalculadorDistanciaAPI calculador = new CalculadorDistanciaAPI();

    @Column(name = "factor_emision")
    private Double factorEmision;

    public Double calcularConsumo() {
        return this.factorEmision;
    }

    public Double calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {
        //Aca tengo el checkeo para los tramos compartidos
        //TODO: ver como resolver el acoplamiento del tramo
        if(tramo.getCompartido() && tramo.getDuenioTramo() != miembro) return 0.0;
        return calculador.calcularDistancia(tramo,miembro);

    }
}
