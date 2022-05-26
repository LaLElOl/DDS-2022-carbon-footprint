package dominio.persona;

import dominio.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class Trayecto {
    private Ubicacion fin;
    private Ubicacion inicio;
    private final List<Tramo> tramos;

    public Trayecto(){
        this.tramos = new ArrayList<Tramo>();
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
