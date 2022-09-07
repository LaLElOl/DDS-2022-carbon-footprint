package dominio.organizacion;

import dominio.EntidadPersistente;
import dominio.persona.Miembro;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private final Set<Miembro> miembros;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
    private Organizacion organizacion;

    @Column(name = "promedio_huella_miembro")
    private Double promedioHCMiembro;

    public Sector(){
        this.miembros = new HashSet<Miembro>();
        this.solicitudes = new ArrayList<Solicitud>() {
        };
    }

    public void darDeAltaAMiembro(Solicitud solicitud) {
        if(this.solicitudes.remove(solicitud)){
            this.miembros.add(solicitud.getSolicitante());
        }
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
        return huella;
    }
}
