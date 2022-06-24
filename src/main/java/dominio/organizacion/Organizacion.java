package dominio.organizacion;

import dominio.Ubicacion;
import dominio.entradaDatos.AdapterLectorExcel;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Organizacion {
    private Clasificable clasificacion;
    private String razonSocial;
    private List<Sector> sectores;
    private TipoOrganizacion tipo;
    private Ubicacion ubicacion;
    private String usuario;
    private String contrasenia;
    private AdapterLectorExcel lectorExcel;

    public Organizacion(){
        this.sectores = new ArrayList<>();
    }

    public void agregarSectores(Sector ... sectoresAAgregar){
        Collections.addAll(sectores,sectoresAAgregar);
    }

    public void quitarSector(Sector sector){
        this.sectores.remove(sector);
    }

    public Map<Integer, List<String>> leerExcel(String pathArchivo) throws IOException {
        return lectorExcel.leerExcel(pathArchivo);
    }
}
