package dominio.persona;

import dominio.EntidadPersistente;
import dominio.transporte.Ubicacion;
import dominio.organizacion.Sector;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "miembro")
public class Miembro extends EntidadPersistente {

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "contrasenia")
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Ubicacion domicilio;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nro_doc")
    private Integer numDoc;

    @ManyToMany(mappedBy = "miembros",fetch = FetchType.LAZY)
    private List<Sector> sectores;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_doc")
    private TipoDoc tipoDoc;

    @OneToMany(mappedBy = "miembro", fetch = FetchType.LAZY)
    private List<Trayecto> trayectos;

    @Column(name = "usuario")
    private String usuario;

    @OneToOne
    private Contacto contacto;

    public Miembro(){
        this.sectores = new ArrayList<>();
        this.trayectos = new ArrayList<>();
    }

    public void agregarSectores(Sector...sectores){
        Collections.addAll(this.sectores, sectores);
    }

    public void quitarSector(Sector sector){
        this.sectores.remove(sector);
    }

    public void agregarTrayectos(Trayecto...trayectos){
        Collections.addAll(this.trayectos, trayectos);
    }

    public void quitarTrayecto(Trayecto trayecto){
        this.trayectos.remove(trayecto);
    }

    public Double calcularHuella() {
        Double huella = 0.0;
        huella = this.trayectos.stream().mapToDouble(trayecto -> trayecto.calcularHuella(this)).sum();
        return huella;
    }

    public double calcularImpactoEnOrganizacion(Sector sector,int mes, int anio){
        double porcentajeImpacto = 0.0;
        boolean trabajoAca = this.sectores.contains(sector);
        if(trabajoAca){
            double huellaOrganizacion = sector.getOrganizacion().calcularHuella(mes,anio);
            porcentajeImpacto = (this.calcularHuella() / huellaOrganizacion)*100;
        }
        return porcentajeImpacto;
    }
}
