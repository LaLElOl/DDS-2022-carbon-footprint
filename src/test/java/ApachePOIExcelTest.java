import services.lectorExcel.ApachePOIExcel;
import org.junit.Test;
import services.lectorExcel.DatoConsumo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApachePOIExcelTest {

    private final String pathArchivo = "src/main/resources/files/tablaTest.xlsx";

    @Test
    public void excelTest() throws IOException {
        ApachePOIExcel lector = new ApachePOIExcel();
        List<DatoConsumo> datos = lector.leerExcel(pathArchivo);


        DatoConsumo dato = datos.get(0);
        System.out.println(dato.getActividad() + dato.getTipoConsumo().getClass().toString() + dato.getValor() );
    }
}
