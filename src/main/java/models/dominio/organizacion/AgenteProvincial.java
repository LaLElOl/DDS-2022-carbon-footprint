package models.dominio.organizacion;

import models.dominio.EntidadPersistente;
import models.dominio.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

@Entity
@Table(name = "agente_provincial")

public class AgenteProvincial extends EntidadPersistente {

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "nombre_agente")
    private String nombre;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "agenteProvincial",fetch = FetchType.LAZY)
    private List<AgenteMunicipal> agentesMunicipales;

    @Column(name = "huellaCarbonoMensual")
    private Double huellaCarbonoActualMensual;

    @Column(name = "fechaUltimoCalculoHuellaMensual", columnDefinition = "DATE")
    private LocalDate fechaUltimoCalculoHuellaMensual;

    @Column(name = "huellaCarbonoAnual")
    private Double huellaCarbonoActualAnual;

    @Column(name = "huellaTotal")
    private Double huellaTotal;

    @Column(name = "fechaUltimoCalculoHuellaAnual", columnDefinition = "DATE")
    private LocalDate fechaUltimoCalculoHuellaAnual;

    public AgenteProvincial() {
        this.agentesMunicipales = new ArrayList<>();
    }

    public void agregarAgentesMunicipales(AgenteMunicipal...agenteMunicipales){
        Collections.addAll(this.agentesMunicipales,agenteMunicipales);
    }

    public Double calcularHuella(int mes, int anio){
        Double huella = 0.0;

        if(mes == 0){
            this.fechaUltimoCalculoHuellaAnual = LocalDate.now();
            this.huellaCarbonoActualAnual = this.agentesMunicipales.stream().mapToDouble(a -> a.calcularHuella(mes,anio)).sum();
            huella = this.huellaCarbonoActualAnual;
        }
        else{
            this.fechaUltimoCalculoHuellaMensual = LocalDate.now();
            this.huellaCarbonoActualMensual = this.agentesMunicipales.stream().mapToDouble(a -> a.calcularHuella(mes,anio)).sum();
            huella = this.huellaCarbonoActualMensual;
        }
        return huella;
    }

    public List<Integer> aniosDatosConsumos() {
        List<Integer> lista = new ArrayList<>();

        this.agentesMunicipales.stream().forEach(o -> lista.addAll(o.aniosDatosConsumos()));

        return lista.stream().distinct().collect(Collectors.toList());
    }

    public Double huellaTotal(){
        double huella = 0.0;
        huella = this.agentesMunicipales.stream().mapToDouble( o -> o.huellaTotal()).sum();
        return huella;
    }

}
