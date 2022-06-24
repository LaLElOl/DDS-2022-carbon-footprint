package dominio.transporte;

import dominio.Ubicacion;
import dominio.persona.Miembro;
import dominio.persona.Tramo;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Publico implements Transporte {
    private String nombre;
    private List<ParadasTransporte> paradas;

    public Integer calcularConsumo() {
        return 0;
    }

    public Integer calcularDistancia(Tramo tramo, Miembro miembro){

        int distanciaRecorrida = 0;
        int indiceParadaInicial = obtenerIndiceParada(tramo.getInicioTramo());
        int indiceParadaFinal = obtenerIndiceParada(tramo.getFinTramo());
        int aux = indiceParadaInicial;
        //TODO: Probar usando Iterator
        while(aux < indiceParadaFinal){
            distanciaRecorrida += this.paradas.get(aux).getDistanciaAlaSiguiente();
            aux++;
        }
        return distanciaRecorrida;
    }

    private int obtenerIndiceParada(Ubicacion ubicacion){
        int i = 0;
        while(i < this.paradas.size()) {
            if(this.paradas.get(i).getParadaActual().getUbicacion() == ubicacion) return i;
            i++;
        }
        return -1;
    }

    public void agregarParadas(ParadasTransporte...paradas){
        Collections.addAll(this.paradas,paradas);
    }
    public void quitarParada(ParadasTransporte parada){
        this.paradas.remove(parada);
    }
}
