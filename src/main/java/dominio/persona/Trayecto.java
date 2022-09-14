package dominio.persona;

import dominio.EntidadPersistente;
import dominio.transporte.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

@Entity
@Table(name = "trayecto")
public class Trayecto extends EntidadPersistente {

    @ManyToOne
    @JoinColumn(name = "miembro_id", referencedColumnName = "id")
    private Miembro miembro;

    //TODO: Revisar para poner one to one, si uso las ubicaciones despues
    @ManyToOne
    @JoinColumn(name = "ubicacion_fin_id", referencedColumnName = "id")
    private Ubicacion fin;

    @ManyToOne
    @JoinColumn(name = "ubicacion_inicio_id", referencedColumnName = "id")
    private Ubicacion inicio;

    @OneToMany(mappedBy = "trayecto")
    private final List<Tramo> tramos;

    public Trayecto(){
        this.tramos = new ArrayList<>();
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
        return this.tramos.stream().mapToDouble(t -> t.calcularHuella(miembro)).sum();
    }
}
