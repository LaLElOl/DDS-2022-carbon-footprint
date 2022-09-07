package dominio.transporte.medios;

import dominio.persona.Miembro;
import dominio.persona.Tramo;
import lombok.Getter;
import lombok.Setter;
import services.distancias.AdapterGeoService;
import services.distancias.Distancia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.IOException;
@Setter
@Getter

@Entity
@Table(name = "transporte_contratado")
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
