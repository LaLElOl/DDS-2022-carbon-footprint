//import models.dominio.organizacion.Organizacion;
//import models.services.lectorExcel.ApachePOIExcel;
//import org.junit.Test;
//import models.dominio.organizacion.datos.DatoConsumo;
//
//import java.io.IOException;
//import java.util.List;
//
//public class ApachePOIExcelTest {
//
//    private final String pathArchivo = "src/main/resources/files/tablaTest.xlsx";
//    private ApachePOIExcel lector = new ApachePOIExcel();
//
//    @Test
//    public void excelTest() throws IOException {
//        List<DatoConsumo> datos = lector.leerExcel(pathArchivo,new Organizacion());
//
//        datos.forEach( dato ->
//                System.out.println(dato.getActividad() + " "
//                + dato.getTipoConsumo() + " "
//                + dato.getValor() + " "
//                + dato.getEPeriodicidad() + " "
//                + dato.getPeriodo())
//        );
//    }
//}
