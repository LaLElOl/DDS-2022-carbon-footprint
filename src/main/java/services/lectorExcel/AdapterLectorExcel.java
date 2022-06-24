package services.lectorExcel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AdapterLectorExcel {
    List<DatoConsumo> leerExcel(String pathArchivo) throws IOException;
}
