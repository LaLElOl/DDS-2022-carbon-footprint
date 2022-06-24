package dominio.entradaDatos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DatoConsumo {
    private String actividad;
    private String tipoConsumo;
    private String valor;
    private Periodicidad periodicidad;
    private Date periodo;
}
