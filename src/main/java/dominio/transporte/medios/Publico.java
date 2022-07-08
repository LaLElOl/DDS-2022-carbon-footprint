package dominio.transporte.medios;

import dominio.transporte.Ubicacion;
import dominio.persona.Miembro;
import dominio.persona.Tramo;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
public abstract class Publico implements Transporte {
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
///Alternativa
    public Integer calcularDistancia2(Tramo tramo, Miembro miembro){

        int distanciaRecorrida = 0;
        ParadasTransporte paradaPosicion = obtenerParada(tramo.getInicioTramo());
        ParadasTransporte paradaFinal = obtenerParada(tramo.getFinTramo());

        while(paradaPosicion != paradaFinal){
            distanciaRecorrida += paradaPosicion.getDistanciaAlaSiguiente();
            paradaPosicion = paradaPosicion.getParadaTransporteSiguiente();
        }
        return distanciaRecorrida;
    }

    private ParadasTransporte obtenerParada(Ubicacion ubicacion){

        Iterator<ParadasTransporte> iterador = this.paradas.iterator();

        while(iterador.hasNext()) {
            if(iterador.next().getParadaActual().getUbicacion() == ubicacion) return iterador.next();
        }
        return null;
    }
/////
    public void agregarParadas(ParadasTransporte...paradas){
        Collections.addAll(this.paradas,paradas);
    }
    public void quitarParada(ParadasTransporte parada){
        this.paradas.remove(parada);
    }
}
