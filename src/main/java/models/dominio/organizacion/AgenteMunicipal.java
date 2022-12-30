package models.dominio.organizacion;

import com.twilio.rest.api.v2010.account.incomingphonenumber.Local;
import models.dominio.EntidadPersistente;
import models.dominio.Usuario;
import lombok.Getter;
import lombok.Setter;
import models.dominio.organizacion.datos.DatoConsumo;
import models.dominio.organizacion.datos.EPeriodicidad;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

@Entity
@Table(name = "agente_municipal")

public class AgenteMunicipal extends EntidadPersistente {

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "nombre_agente")
    private String nombre;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "agenteMunicipal", fetch = FetchType.LAZY)
    private List<Organizacion> organizaciones;

    @ManyToOne
    @JoinColumn(name = "agente_provincial_id", referencedColumnName = "id")
    private AgenteProvincial agenteProvincial;

    @Column(name = "huellaCarbonoMensual")
    private Double huellaCarbonoActualMensual;

    @Column(name = "fechaUltimoCalculoHuellaMensual", columnDefinition = "DATE")
    private LocalDate fechaUltimoCalculoHuellaMensual;

    @Column(name = "huellaCarbonoAnual")
    private Double huellaCarbonoActualAnual;

    @Column(name = "fechaUltimoCalculoHuellaAnual", columnDefinition = "DATE")
    private LocalDate fechaUltimoCalculoHuellaAnual;

    public AgenteMunicipal() {
        this.organizaciones = new ArrayList<>();
        this.setHuellaCarbonoActualAnual(0.0);
        this.setHuellaCarbonoActualMensual(0.0);
    }

    public void agregarOrganizaciones(Organizacion... organizaciones) {
        Collections.addAll(this.organizaciones, organizaciones);
    }

    public Double calcularHuella(int mes, int anio) {

        double huella = 0.0;
        if (mes > 0 && mes <= 12) {
            /*if (
                    LocalDate.now().minus(30, ChronoUnit.DAYS).isBefore(this.fechaUltimoCalculoHuellaMensual) &&
                            this.fechaUltimoCalculoHuellaMensual != null
            ) {
                return this.huellaCarbonoActualMensual;
            }*/
            huella += this.organizaciones.stream().mapToDouble(o -> o.calcularHuella(mes, anio)).sum();
            this.huellaCarbonoActualMensual = huella;
            this.fechaUltimoCalculoHuellaMensual = LocalDate.now();
        } else {
            /*if (
                    LocalDate.now().minus(30, ChronoUnit.DAYS).isBefore(this.fechaUltimoCalculoHuellaAnual) &&
                            this.fechaUltimoCalculoHuellaAnual != null
            ) {
                return this.huellaCarbonoActualAnual;
            }*/
            huella += this.organizaciones.stream().mapToDouble(o -> o.calcularHuella(mes, anio)).sum();
            this.huellaCarbonoActualAnual = huella;
            this.fechaUltimoCalculoHuellaAnual = LocalDate.now();
        }
        return huella;
    }

    public Double huellaTotal(){
        double huella = 0.0;

        huella += this.organizaciones.stream().mapToDouble( o -> o.huellaTotal()).sum();

        return huella;
    }

    public Double huellaSegunActividad(String actividad){
        double huella = 0.0;

        huella += this.organizaciones.stream().mapToDouble(o -> o.huellaSegunActividad(actividad)).sum();

        return huella;
    }

    public List<Integer> aniosDatosConsumos(){
        List<Integer> lista = new ArrayList<>();

        this.organizaciones.stream().forEach(o -> lista.addAll(o.aniosDatosConsumos()));

        return lista.stream().distinct().collect(Collectors.toList());
    }
}