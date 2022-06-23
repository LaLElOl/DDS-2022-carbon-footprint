package dominio.transporte;

import dominio.Ubicacion;

import java.util.List;

public class Publico implements Transporte {
    private String nombre;
    private List<ParadasTransporte> paradas;

    public Integer calcularConsumo() {
        return 0;
    }

    public Integer calcularDistancia(Ubicacion ubicacionInicial, Ubicacion ubicacionFinal){

        int distanciaRecorrida = 0;
        int indiceParadaInicial = obtenerIndiceParada(ubicacionInicial);
        int indiceParadaFinal = obtenerIndiceParada(ubicacionInicial);
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
