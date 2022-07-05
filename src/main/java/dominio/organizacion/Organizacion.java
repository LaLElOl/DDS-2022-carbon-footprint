package dominio.organizacion;

import dominio.persona.Contacto;
import dominio.persona.Miembro;
import dominio.transporte.Ubicacion;
import services.lectorExcel.AdapterLectorExcel;
import lombok.Getter;
import lombok.Setter;
import services.lectorExcel.DatoConsumo;

import java.io.IOException;
import java.util.*;

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
    private Set<Miembro> miembrosANotificar;

    public Organizacion(){
        this.sectores = new ArrayList<>();
        this.miembrosANotificar = new HashSet<>();
    }

    public void agregarSectores(Sector ... sectoresAAgregar){
        Collections.addAll(sectores,sectoresAAgregar);
    }

    public void quitarSector(Sector sector){
        this.sectores.remove(sector);
    }

    public List<DatoConsumo> leerExcel(String pathArchivo) throws IOException {
        return lectorExcel.leerExcel(pathArchivo);
    }

    public void suscribirARecomendacion(Miembro miembro){
        //TODO: Ver si agregamos los chequeos de que el miembro es de la organizacion
        this.miembrosANotificar.add(miembro);
    }

    public void notificarRecomendacion(String link){
        this.miembrosANotificar.forEach(
                miembro -> {
                    Contacto contacto = miembro.getContacto();
                    contacto.getMediosNotificacion().forEach(medio -> medio.notificar(contacto,link));
                });
    }
}
