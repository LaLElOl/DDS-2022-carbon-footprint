package dominio.entradaDatos;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.persistence.Column;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LectorExcel {

    public Map<Integer, List<String>> readExcel(String pathArchivo) throws IOException {
        FileInputStream file = new FileInputStream(pathArchivo);
        Workbook workbook = new XSSFWorkbook(file);

        Sheet hoja = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row columna : hoja) {
            data.put(i, new ArrayList<String>());
            for (Cell celda : columna) {
                switch (celda.getCellType()) {
                    case STRING:
                        data.get(i).add(celda.getRichStringCellValue().getString() + "");
                        break;
                    case NUMERIC:
                        if(celda.getColumnIndex() == 5){
                            //Esta en la columna 5 que es la de fechas (ULTRA HARDCODED)
                            data.get(i).add(celda.getNumericCellValue() + "");
                        }else{
                            data.get(i).add(celda.getDateCellValue() + "");
                        }
                        break;
                    default:
                        data.get(i).add("null"); //Asi se puede ver algo en el print del test
                        break;
                }
            }
            i++;
        }
        file.close();
        return data;
    }
}
