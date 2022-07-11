package services.lectorExcel;

import dominio.organizacion.DatoConsumo;

import java.io.IOException;
import java.util.List;

public interface AdapterLectorExcel {
    List<DatoConsumo> leerExcel(String pathArchivo) throws IOException;
}
