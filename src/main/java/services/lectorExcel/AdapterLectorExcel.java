package dominio.entradaDatos;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AdapterLectorExcel {
    Map<Integer, List<String>> leerExcel(String pathArchivo) throws IOException;
}
