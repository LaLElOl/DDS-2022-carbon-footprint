package dominio.organizacion;

import dominio.organizacion.datos.Anual;
import dominio.organizacion.datos.DatoConsumo;
import dominio.organizacion.datos.Mensual;
import dominio.organizacion.datos.Periodicidad;
import dominio.persona.Contacto;
import dominio.transporte.Ubicacion;
import services.lectorExcel.AdapterLectorExcel;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
    private Set<Contacto> contactosANotificar;

    public Organizacion(){
        this.sectores = new ArrayList<>();
        this.contactosANotificar = new HashSet<>();
    }

    public void agregarSectores(Sector ... sectoresAAgregar){
        Collections.addAll(sectores,sectoresAAgregar);
    }

    public void quitarSector(Sector sector){
        this.sectores.remove(sector);
    }

    public List<DatoConsumo> leerExcel(String pathArchivo) throws IOException {
        return lectorExcel.leerExcel(pathArchivo,this);
    }

    public void suscribirARecomendacion(Contacto contacto){
        //TODO: Ver si agregamos los chequeos de que el miembro es de la organizacion
        this.contactosANotificar.add(contacto);
    }

    public void notificarRecomendacion(String link){
        this.contactosANotificar.forEach(
                contacto -> contacto.getMediosNotificacion().forEach(medio -> medio.notificar(contacto,link)));
    }

    public Double calcularHuella(int mes, int anio){
        //TODO: falta calcular la parte del excel y sumarla a "huella"
        double huella = 0.0;
        if(mes > 0 && mes <= 12){
            huella += obtenerHuellaMiembros();
            huella += obtenerHuellaOrganizacion(new Mensual(), LocalDate.of(anio,mes,1));
        }else{
            huella += obtenerHuellaMiembros() * 12;
            huella += obtenerHuellaOrganizacion(new Anual(),LocalDate.of(anio,1,1));
        }
        return huella;
    }

    public double obtenerHuellaMiembros() {
        return this.sectores.stream().mapToDouble(Sector::calcularHuella).sum();
    }

    private List<DatoConsumo> obtenerConsumos(LocalDate fecha){
        List<DatoConsumo> datos = new ArrayList<>();
        //TODO: Obtener datos de la base de datos, filtrando por periodo (año) y organizacion
        return datos;
    }

    public double obtenerHuellaOrganizacion(Periodicidad periodicidad, LocalDate fecha) {
        List<DatoConsumo> datos = obtenerConsumos(fecha);

        return periodicidad.filtrarDatos(datos,fecha)
                .stream()
                .mapToDouble(DatoConsumo::calcularHuella)
                .sum();
    }
}