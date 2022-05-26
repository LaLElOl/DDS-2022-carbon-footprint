package dominio.transporte;

import java.util.List;

public class Publico implements Transporte {
    private String nombre;
    private List<ParadasTransporte> paradas;

    public Integer calcularConsumo() {
        return 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ParadasTransporte> getParadas() {
        return paradas;
    }

    public void setParadas(List<ParadasTransporte> paradas) {
        this.paradas = paradas;
    }
}
