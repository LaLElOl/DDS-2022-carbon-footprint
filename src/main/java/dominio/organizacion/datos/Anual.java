package dominio.organizacion.datos;

import java.time.LocalDate;
import java.util.List;

public class Anual implements Periodicidad{

    public List<DatoConsumo> filtrarDatos(List<DatoConsumo> datos, LocalDate fecha){
        return datos;
    }
}
