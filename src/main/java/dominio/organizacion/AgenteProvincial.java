package dominio.organizacion;

import dominio.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "agente_provincial")

public class AgenteProvincial extends EntidadPersistente {

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "nombre_agente")
    private String nombre;

    @OneToMany(mappedBy = "agenteProvincial",fetch = FetchType.LAZY)
    private List<AgenteMunicipal> agentesMunicipales;

    public AgenteProvincial() {
        this.agentesMunicipales = new ArrayList<>();
    }


    public Double calcularHuella(int mes, int anio){
        return this.agentesMunicipales.stream().mapToDouble(a -> a.calcularHuella(mes,anio)).sum();
    }
}
