package dominio.transporte;

import java.util.List;

public class Publico implements Transporte {
    private String nombre;
    private List<Parada> paradas;

    public Integer calcularConsumo() {
        return 0;
    }
}
