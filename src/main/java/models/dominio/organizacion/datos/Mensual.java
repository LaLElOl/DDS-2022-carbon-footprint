package models.dominio.organizacion.datos;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Mensual implements Periodicidad{

    public List<DatoConsumo> filtrarDatos(List<DatoConsumo> datos, LocalDate fecha){
        return datos.stream()
                .filter(dato -> dato.getPeriodo().getMonthValue() == fecha.getMonthValue())
                .filter(dato -> dato.getEPeriodicidad() == EPeriodicidad.MENSUAL)
                .collect(Collectors.toList());
    }
}
