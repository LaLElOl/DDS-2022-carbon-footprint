package services.lectorExcel;

import dominio.organizacion.Organizacion;
import dominio.organizacion.datos.DatoConsumo;

import java.io.IOException;
import java.util.List;

public interface AdapterLectorExcel {
    List<DatoConsumo> leerExcel(String pathArchivo, Organizacion organizacion) throws IOException;
}
