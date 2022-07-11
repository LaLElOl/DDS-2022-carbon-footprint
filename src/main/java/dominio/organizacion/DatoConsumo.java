package dominio.organizacion;

import dominio.transporte.combustibles.Combustible;
import lombok.Getter;
import lombok.Setter;
import services.lectorExcel.Periodicidad;
import java.time.LocalDate;

@Getter
@Setter
public class DatoConsumo {
    private String actividad;
    private Combustible tipoConsumo;
    private Double valor;
    private Periodicidad periodicidad;
    private LocalDate periodo;

    public double calcularHuella(){
        return this.valor * this.tipoConsumo.consumoCombustible();
    }
}
