package dominio.persona;

import dominio.Ubicacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Ubicacion getFin() {
        return fin;
    }

    public void setFin(Ubicacion fin) {
        this.fin = fin;
    }

    public Ubicacion getInicio() {
        return inicio;
    }

    public void setInicio(Ubicacion inicio) {
        this.inicio = inicio;
    }

    public List<Tramo> getTramos() {
        return tramos;
    }
}
