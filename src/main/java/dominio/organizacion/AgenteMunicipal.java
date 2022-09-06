package dominio.organizacion;

import dominio.EntidadPersistente;
import dominio.organizacion.datos.Periodicidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "agente_municipal")

public class AgenteMunicipal extends EntidadPersistente {

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "nombre_agente")
    private String nombre;

    @OneToMany(mappedBy = "agenteMunicipal", fetch = FetchType.LAZY)
    private List<Organizacion> organizaciones;

    @ManyToOne
    @JoinColumn(name = "agente_provincial_id",referencedColumnName = "id")
    private AgenteProvincial agenteProvincial;

    public AgenteMunicipal(){
        this.organizaciones = new ArrayList<>();
    }

    public void agregarOrganizaciones(Organizacion...organizaciones){
        Collections.addAll(this.organizaciones,organizaciones);
    }

    public Double calcularHuella(int mes, int anio) {
        return this.organizaciones.stream().mapToDouble(o -> o.calcularHuella(mes, anio)).sum();
    }
}
