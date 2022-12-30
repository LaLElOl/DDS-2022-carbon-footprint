package models.dominio.organizacion;

import models.dominio.EntidadPersistente;
import models.dominio.persona.Miembro;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "sector")

public class Sector extends EntidadPersistente {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id",referencedColumnName = "id")
    private List<Solicitud> solicitudes;

    @ManyToMany
    @JoinTable(name = "empleados_sector",
            joinColumns = @JoinColumn(name = "sector_id"),
            inverseJoinColumns = @JoinColumn(name = "miembro_id")
            )
    private Set<Miembro> miembros;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
    private Organizacion organizacion;

    @Column(name = "promedio_huella_miembro")
    private Double promedioHCMiembro;

    @Column(name = "huellaCarbonoActual")
    private Double huellaCarbonoActual;

    @Column(name = "periodo", columnDefinition = "DATE")
    private LocalDate fechaUltimoCalculoHuella;

    public Sector(){
        this.miembros = new HashSet<Miembro>();
        this.solicitudes = new ArrayList<Solicitud>() {
        };
    }

    public void darDeAltaAMiembro(Solicitud solicitud) {
        if(this.solicitudes.remove(solicitud)){
            solicitud.setFecha_respuesta(LocalDate.now());
            solicitud.setEstado(EstadoSolicitud.ACEPTADA);
            this.miembros.add(solicitud.getSolicitante());
        }
    }

    public void rechazarSolicitud(Solicitud solicitud){
        solicitud.setFecha_respuesta(LocalDate.now());
        solicitud.setEstado(EstadoSolicitud.RECHAZADA);
        this.solicitudes.remove(solicitud);
    }

    public Set<Miembro> getMiembros() {
        return miembros;
    }

    public void agregarMiembroSolicitante(Solicitud solicitud){this.solicitudes.add(solicitud);}

    public List<Solicitud> getMiembrosSolicitantes() {
        return solicitudes;
    }

    public Double calcularHuella() {


        double huella =  this.getMiembros().stream().mapToDouble(Miembro::calcularHuella).sum();
        this.promedioHCMiembro = huella / getMiembros().size();
        this.fechaUltimoCalculoHuella = LocalDate.now();
        this.huellaCarbonoActual = huella;
        return huella;
    }
}
