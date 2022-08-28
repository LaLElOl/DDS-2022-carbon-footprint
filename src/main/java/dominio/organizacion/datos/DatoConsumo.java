package dominio.organizacion.datos;

import dominio.organizacion.Organizacion;
import dominio.transporte.combustibles.Combustible;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class DatoConsumo {
    private String actividad;
    private Combustible tipoConsumo;
    private Double valor;
    private Periodicidad periodicidad;
    private LocalDate periodo;
    private Organizacion organizacion;

    public double calcularHuella(){
        return this.valor * this.tipoConsumo.getFactorEmision();
    }
}
