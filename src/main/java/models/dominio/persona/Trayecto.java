package models.dominio.persona;

import models.dominio.EntidadPersistente;
import models.dominio.transporte.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

@Entity
@Table(name = "trayecto")
public class Trayecto extends EntidadPersistente {

    @Column(name="nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "miembro_id", referencedColumnName = "id")
    private Miembro miembro;

    //TODO: Revisar para poner one to one, si uso las ubicaciones despues
    @OneToOne
    @JoinColumn(name = "ubicacion_fin_id", referencedColumnName = "id")
    private Ubicacion fin;

    @OneToOne
    @JoinColumn(name = "ubicacion_inicio_id", referencedColumnName = "id")
    private Ubicacion inicio;

    @OneToMany(mappedBy = "trayecto")
    private final List<Tramo> tramos;

    @Column(name = "fecha_alta_tramo", columnDefinition = "DATE")
    private LocalDate fechaAltaTramo;

    public Trayecto(){
        this.tramos = new ArrayList<>();
        this.fechaAltaTramo = LocalDate.now();
    }

    public Double distanciaTotal(Miembro miembro){
        return this.distanciaDeTramos(miembro).stream().mapToDouble(d -> d).sum();
    }

    public List<Double> distanciaDeTramos(Miembro miembro){
        return this.tramos.stream().map(t -> {
            try {
                return t.calcularDistancia(miembro);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

    public void agregarTramos(Tramo...tramos){
        Collections.addAll(this.tramos,tramos);
    }

    public void quitarTramo(Tramo tramo){
        this.tramos.remove(tramo);
    }

    public Double calcularHuella(Miembro miembro) {
        if(this.inicio.algunNull()|| this.fin.algunNull()){
            return 0.0;
        }

        return this.tramos.stream().mapToDouble(t -> t.calcularHuella(miembro)).sum();
    }
}
