package services.lectorExcel;

import dominio.transporte.combustibles.Combustible;
import dominio.transporte.combustibles.Kerosene;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ApachePOIExcel implements AdapterLectorExcel {

    public List<DatoConsumo> leerExcel(String pathArchivo) throws IOException {
        FileInputStream file = new FileInputStream(pathArchivo);
        Workbook workbook = new XSSFWorkbook(file);
        List<DatoConsumo> data = new ArrayList<>();

        Sheet hoja = workbook.getSheetAt(0);

        //Empezamos por la fila nro 3 porque las primeras dos son titulos nomas
        for(int j=2 ; j < hoja.getLastRowNum() ; j++){
            DatoConsumo datoConsumo = new DatoConsumo();
            Row fila = hoja.getRow(j);
            for (int i = 0 ; i < 5; i++){
                switch (i){
                    case 0:
                        datoConsumo.setActividad(this.obtenerActividad(fila.getCell(i)));
                        break;
                    case 1:
                        datoConsumo.setTipoConsumo(this.obtenerCombustible(fila.getCell(i)));
                        break;
                    case 2:
                        datoConsumo.setValor(this.obtenerValor(fila.getCell(i)));
                        break;
                    case 3:
                        datoConsumo.setPeriodicidad(this.obtenerPeriodicidad(fila.getCell(i)));
                        break;
                    case 4:
                        datoConsumo.setPeriodo(this.obtenerPeriodo(fila.getCell(i)));
                        break;
                    default:
                        break;
                }
            }
            data.add(datoConsumo);
        }
        file.close();
        return data;
    }

    private Date obtenerPeriodo(Cell cell) {
        return cell.getDateCellValue();
    }

    private Periodicidad obtenerPeriodicidad(Cell cell) {
        switch (cell.getRichStringCellValue().getString().toLowerCase(Locale.ROOT)){
            case "mensual":
                return Periodicidad.MENSUAL;
            default:
                return Periodicidad.ANUAL;
        }
    }

    private Double obtenerValor(Cell cell) {
        return cell.getNumericCellValue();
    }

    private Combustible obtenerCombustible(Cell cell) {
        //TODO: Necesitamos alternativa a un switch para hacer esto
        return new Kerosene();
    }

    private String obtenerActividad(Cell cell) {
        return cell.getRichStringCellValue().getString();
    }
}
