package dominio.transporte.medios;

import dominio.persona.Miembro;
import dominio.persona.Tramo;
import dominio.transporte.vehiculos.Vehiculo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;

@Getter
@Setter

@Entity
@DiscriminatorValue("particular")
public class Particular extends Transporte {

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "id")
    private Vehiculo vehiculo;

    @Transient
    private CalculadorDistanciaAPI calculador = new CalculadorDistanciaAPI();

    public Double calcularConsumo() {
        return this.vehiculo.calcularConsumoParticular();
    }

    public Double calcularDistancia(Tramo tramo, Miembro miembro) throws IOException {
        //Aca tengo el checkeo para los tramos compartidos
        //TODO: ver como resolver el acoplamiento del tramo
        if(tramo.getCompartido() && tramo.getDuenioTramo() != miembro) return 0.0;
        return calculador.calcularDistancia(tramo,miembro);
    }
}
