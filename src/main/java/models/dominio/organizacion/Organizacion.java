package models.dominio.organizacion;

import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;
import models.dominio.EntidadPersistente;
import models.dominio.ReporteHuellaCarbono;
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
import java.util.stream.Collectors;

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

    @Column(name = "huellaCarbonoMensual")
    private Double huellaCarbonoActualMensual;

    @Column(name = "fechaUltimoCalculoHuellaMensual", columnDefinition = "DATE")
    private LocalDate fechaUltimoCalculoHuellaMensual;

    @Column(name = "huellaCarbonoAnual")
    private Double huellaCarbonoActualAnual;

    @Column(name = "fechaUltimoCalculoHuellaAnual", columnDefinition = "DATE")
    private LocalDate fechaUltimoCalculoHuellaAnual;

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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "datos_consumo", referencedColumnName = "id")
    private List<DatoConsumo> datosConsumo;

    public Organizacion(){
        this.sectores = new ArrayList<>();
        this.datosConsumo = new ArrayList<>();
        this.contactosANotificar = new HashSet<>();
        this.lectorExcel = new ApachePOIExcel();
        this.huellaCarbonoActualMensual = 0.0;
        this.fechaUltimoCalculoHuellaMensual = LocalDate.now();
        this.huellaCarbonoActualAnual = 0.0;
        this.fechaUltimoCalculoHuellaAnual = LocalDate.now();
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

        double huella = 0.0;
        if(mes > 0 && mes <= 12){
           /* if(
                    LocalDate.now().minus(30,ChronoUnit.DAYS).isBefore(this.fechaUltimoCalculoHuellaMensual) &&
                            this.fechaUltimoCalculoHuellaMensual != null
            ){
                return this.huellaCarbonoActualMensual;
            }*/

            huella += obtenerHuellaMiembros();
            huella += obtenerHuellaOrganizacion(EPeriodicidad.MENSUAL, LocalDate.of(anio,mes,1));
            this.fechaUltimoCalculoHuellaMensual = LocalDate.now();
            this.huellaCarbonoActualMensual = huella;
        }else{
            /*if(
                    LocalDate.now().minus(30,ChronoUnit.DAYS).isBefore(this.fechaUltimoCalculoHuellaAnual) &&
                            this.fechaUltimoCalculoHuellaAnual != null
            ){
                return this.huellaCarbonoActualAnual;
            }*/
            huella += obtenerHuellaMiembros() * 12;
            huella += obtenerHuellaOrganizacion(EPeriodicidad.ANUAL,LocalDate.of(anio,1,1));
            this.fechaUltimoCalculoHuellaAnual = LocalDate.now();
            this.huellaCarbonoActualAnual = huella;
        }
        return huella;
    }

    public double obtenerHuellaMiembros() {
        return this.sectores.stream().mapToDouble(Sector::calcularHuella).sum();
    }

    private List<DatoConsumo> obtenerConsumos(EPeriodicidad periodicidad,LocalDate fecha){

        switch(periodicidad){
            case MENSUAL:
                return this.datosConsumo.stream()
                        .filter(dato -> dato.getEPeriodicidad() == periodicidad &&
                                dato.getPeriodo().getYear() == fecha.getYear() &&
                                dato.getPeriodo().getMonthValue() == fecha.getMonthValue())
                        .collect(Collectors.toList());
            case ANUAL:
                return this.datosConsumo.stream()
                        .filter(dato -> dato.getPeriodo().getYear() == fecha.getYear())
                        .collect(Collectors.toList());
            default:
                return new ArrayList<>();
        }
    }

    public double obtenerHuellaOrganizacion(EPeriodicidad periodicidad, LocalDate fecha) {
        List<DatoConsumo> datos = obtenerConsumos(periodicidad,fecha);
        return datos.stream().mapToDouble(DatoConsumo::calcularHuella).sum();
    }

    public void agregarDatosConsumo(List<DatoConsumo> datosConsumo) {
        datosConsumo.forEach(datoConsumo -> {
            Collections.addAll(this.datosConsumo,datoConsumo);
        });
    }

    public ReporteHuellaCarbono generarReporte(LocalDate fecha, EPeriodicidad periodicidad) {
        if (periodicidad == EPeriodicidad.ANUAL){
            return new ReporteHuellaCarbono(this,fecha,periodicidad,this.huellaCarbonoActualAnual);
        }else{
            return new ReporteHuellaCarbono(this,fecha,periodicidad,this.huellaCarbonoActualMensual);
        }
    }

    public Double huellaTotal(){
        double huella = 0.0;

        huella += obtenerHuellaMiembros();
        huella += this.datosConsumo.stream().mapToDouble(DatoConsumo::calcularHuella).sum();

        return huella;

    }
}