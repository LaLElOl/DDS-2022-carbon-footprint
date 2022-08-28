package dominio.organizacion.datos;

import java.time.LocalDate;
import java.util.List;

abstract public class Periodicidad {

    public abstract List<DatoConsumo> filtrarDatos(List<DatoConsumo> datos, LocalDate fecha);
}
