import dominio.entradaDatos.ApachePOIExcelAdapter;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApachePOIExcelTest {

    private final String pathArchivo = "src/main/resources/files/tablaTest.xlsx";

    @Test
    public void excelTest() throws IOException {
        ApachePOIExcelAdapter lector = new ApachePOIExcelAdapter();
        Map<Integer, List<String>> datos = lector.leerExcel(pathArchivo);


        for(int i = 0;i < 5;i++){
            for(String dato : datos.get(i)){
                System.out.print(dato + "\t");
            }
            System.out.println();
        }
    }
}
