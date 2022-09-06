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

    @OneToMany(mappedBy = "sector", fetch = FetchType.LAZY)
    private List<Solicitud> solicitudes;

    //TODO: resolver many to many contra miembros
    @OneToMany(mappedBy = "sector", fetch = FetchType.LAZY)
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
