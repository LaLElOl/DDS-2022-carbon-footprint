package dominio.persona;

import dominio.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Trayecto {
    private Ubicacion fin;
    private Ubicacion inicio;
    private final List<Tramo> tramos;

    public Trayecto(){
        this.tramos = new ArrayList<>();
    }

    public int distanciaTotal(Miembro miembro){
        return this.distanciaDeTramos(miembro).stream().mapToInt(d -> d).sum();
    }

    public List<Integer> distanciaDeTramos(Miembro miembro){
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
}
