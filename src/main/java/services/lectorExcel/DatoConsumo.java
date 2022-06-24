package services.lectorExcel;

import dominio.transporte.combustibles.Combustible;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DatoConsumo {
    private String actividad;
    private Combustible tipoConsumo;
    private Double valor;
    private Periodicidad periodicidad;
    private Date periodo;
}
