package models.services.lectorExcel;

import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.datos.DatoConsumo;
import models.dominio.organizacion.datos.EPeriodicidad;
import models.dominio.transporte.combustibles.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ApachePOIExcel implements AdapterLectorExcel {

    public List<DatoConsumo> leerExcel(String pathArchivo, Organizacion organizacion) throws IOException {
        FileInputStream file = new FileInputStream(pathArchivo);
        Workbook workbook = new XSSFWorkbook(file);
        List<DatoConsumo> data = new ArrayList<>();

        Sheet hoja = workbook.getSheetAt(0);

        //Empezamos por la fila nro 3 porque las primeras dos son titulos nomas
        for(int j=2 ; j < hoja.getLastRowNum() + 1; j++){
            DatoConsumo datoConsumo = new DatoConsumo();
            datoConsumo.setOrganizacion(organizacion);
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
                        datoConsumo.setEPeriodicidad(this.obtenerPeriodicidad(fila.getCell(i)));
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

    private LocalDate obtenerPeriodo(Cell cell) {
        LocalDateTime valor = cell.getLocalDateTimeCellValue();

        return LocalDate.of(valor.getYear(),valor.getMonth(),valor.getDayOfMonth());
    }


    private EPeriodicidad obtenerPeriodicidad(Cell cell) {
        switch (cell.getRichStringCellValue().getString().toLowerCase(Locale.ROOT)){
            case "mensual":
                return EPeriodicidad.MENSUAL;
            case "anual":
                return EPeriodicidad.ANUAL;
            default:
                return null;
        }
    }

    private Double obtenerValor(Cell cell) {
        //TODO: para el 3er tipo de emisiones controlar si recibe o no un valor numerico
        if(cell.getCellType() == CellType.NUMERIC)
            return cell.getNumericCellValue();
        return null;
    }

    private Combustible obtenerCombustible(Cell cell) {
        //TODO: Necesitamos alternativa a un switch para hacer esto
        //TODO: Tiene que recuperar la instancia unica de cada combustible desde la DB
        switch(cell.getRichStringCellValue().toString().toLowerCase(Locale.ROOT)){
            case "gas natural":
                return new GasNatural();
            case "fuel oil":
                return new FuelOil();
            case "carbon":
                return new Carbon();
            case "leña":
                return new Lenia();
            case "kerosene":
                return new Kerosene();
            case "carbon de leña":
                return new CarbonLenia();
            case "gnc":
                return new GNC();
            case "nafta":
                return new Nafta();
            case "electricidad":
                return new Electrico();
            case "gasoil":
                return new Gasoil();
            default:
                return null;
        }
    }

    private String obtenerActividad(Cell cell) {
        return cell.getRichStringCellValue().getString();
    }
}
