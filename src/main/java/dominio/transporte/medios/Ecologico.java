package dominio.transporte.medios;

import dominio.persona.Miembro;
import dominio.persona.Tramo;
import lombok.Getter;
import lombok.Setter;
import services.distancias.AdapterGeoService;
import services.distancias.Distancia;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.IOException;
@Setter
@Getter

@Entity
@Table(name = "transporte_ecologico")
public class Ecologico extends Transporte {

    //TODO: Ver como corregir la persistencia de Ecologico, que no tiene atributos persistentes
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
