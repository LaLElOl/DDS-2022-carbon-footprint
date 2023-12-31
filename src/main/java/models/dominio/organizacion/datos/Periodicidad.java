package models.dominio.organizacion.datos;

import java.time.LocalDate;
import java.util.List;

public interface Periodicidad {

    List<DatoConsumo> filtrarDatos(List<DatoConsumo> datos, LocalDate fecha);
}
