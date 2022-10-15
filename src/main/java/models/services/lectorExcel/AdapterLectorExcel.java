package models.services.lectorExcel;

import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.datos.DatoConsumo;

import java.io.IOException;
import java.util.List;

public interface AdapterLectorExcel {
    List<DatoConsumo> leerExcel(String pathArchivo, Organizacion organizacion) throws IOException;
}
