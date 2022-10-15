package models.dominio.organizacion;

import models.dominio.EntidadPersistente;
import models.dominio.Usuario;
import models.dominio.organizacion.datos.*;
import models.dominio.organizacion.datos.EPeriodicidad;
import models.dominio.persona.Contacto;
import models.dominio.transporte.Ubicacion;
import models.dominio.organizacion.datos.DatoConsumo;
import models.dominio.organizacion.datos.FactoryPeriodicidad;
import models.services.lectorExcel.AdapterLectorExcel;
import lombok.Getter;
import lombok.Setter;
import models.services.lectorExcel.ApachePOIExcel;
import models.services.mediosNotiicacion.FactoryMedioNotificacion;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Getter
@Setter

@Entity
@Table(name = "organizacion")

public class Organizacion extends EntidadPersistente {

    @ManyToOne
    @JoinColumn(name = "clasificacion_id", referencedColumnName = "id")
    private Clasificacion clasificacion;

    @Column(name = "razon_social")
    private String razonSocial;

    @OneToMany(mappedBy = "organizacion", fetch = FetchType.LAZY)
    private List<Sector> sectores;

    @OneToOne
    private Ubicacion ubicacion;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "huellaCarbonoActual")
    private Double huellaCarbonoActual;

    @Column(name = "fechaUltimoCalculoHuella", columnDefinition = "DATE")
    private LocalDate fechaUltimoCalculoHuella;

    @Column(name = "cuit")
    private String cuit;

    @Transient
    private AdapterLectorExcel lectorExcel;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactos_a_notificar", referencedColumnName = "id")
    private Set<Contacto> contactosANotificar;

    @ManyToOne
    @JoinColumn(name = "agente_municipal_id", referencedColumnName = "id")
    private AgenteMunicipal agenteMunicipal;

    public Organizacion(){
        this.sectores = new ArrayList<>();
        this.contactosANotificar = new HashSet<>();
        this.lectorExcel = new ApachePOIExcel();
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
                contacto -> contacto.getMediosNotificacion().forEach(medio ->
                        FactoryMedioNotificacion.obtenerMedioNotificacion(medio)
                                .notificar(contacto,link)));
    }

    public Double calcularHuella(int mes, int anio){

        if((LocalDate.now().minus(2,ChronoUnit.DAYS).isBefore(this.fechaUltimoCalculoHuella))){
            return this.huellaCarbonoActual;
        }

        //TODO: falta calcular la parte del excel y sumarla a "huella"
        double huella = 0.0;
        if(mes > 0 && mes <= 12){
            huella += obtenerHuellaMiembros();
            huella += obtenerHuellaOrganizacion(EPeriodicidad.MENSUAL, LocalDate.of(anio,mes,1));
        }else{
            huella += obtenerHuellaMiembros() * 12;
            huella += obtenerHuellaOrganizacion(EPeriodicidad.ANUAL,LocalDate.of(anio,1,1));
        }
        this.fechaUltimoCalculoHuella = LocalDate.now();
        this.huellaCarbonoActual = huella;
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

    public double obtenerHuellaOrganizacion(EPeriodicidad periodicidad, LocalDate fecha) {
        List<DatoConsumo> datos = obtenerConsumos(fecha);

        return FactoryPeriodicidad.obtenerPeriodicidad(periodicidad).filtrarDatos(datos,fecha)
                .stream()
                .mapToDouble(DatoConsumo::calcularHuella)
                .sum();
    }
}