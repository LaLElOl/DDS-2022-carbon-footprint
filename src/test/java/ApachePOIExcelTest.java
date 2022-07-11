import dominio.organizacion.Organizacion;
import services.lectorExcel.ApachePOIExcel;
import org.junit.Test;
import services.lectorExcel.DatoConsumo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApachePOIExcelTest {

    private final String pathArchivo = "src/main/resources/files/tablaTest.xlsx";
    private ApachePOIExcel lector = new ApachePOIExcel();

    @Test
    public void excelTest() throws IOException {
        List<DatoConsumo> datos = lector.leerExcel(pathArchivo);

        datos.forEach( dato ->
                System.out.println(dato.getActividad() + " "
                + dato.getTipoConsumo() + " "
                + dato.getValor() + " "
                + dato.getPeriodicidad() + " "
                + dato.getPeriodo())
        );
    }
}
