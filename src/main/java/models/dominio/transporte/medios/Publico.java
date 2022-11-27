package models.dominio.transporte.medios;

import models.dominio.transporte.Ubicacion;
import models.dominio.persona.Miembro;
import models.dominio.persona.Tramo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter

@Entity
@DiscriminatorValue("publico")
public class Publico extends Transporte {
    
    @Enumerated(EnumType.STRING)
    @Column(name = "medio_transporte")
    protected TipoTransporte tipoTransporte;

    @OneToMany(mappedBy = "transportePublico")
    private List<ParadasTransporte> paradas;

    @Column(name = "factor_emision")
    protected Double factorEmision;

    public Publico(){
        this.paradas = new ArrayList<>();
    }

    public Double calcularConsumo() {
        return this.factorEmision;
    }

    public Double calcularDistancia(Tramo tramo, Miembro miembro){

        Double distanciaRecorrida = 0.0;
        int indiceParadaInicial = obtenerIndiceParada(tramo.getInicioTramo());
        int indiceParadaFinal = obtenerIndiceParada(tramo.getFinTramo());
        int aux = indiceParadaInicial;
        while(aux < indiceParadaFinal && indiceParadaInicial != -1){
            distanciaRecorrida += this.paradas.get(aux).getDistanciaAlaSiguiente();
            aux++;
        }
        return distanciaRecorrida;
    }

    private int obtenerIndiceParada(Ubicacion ubicacion){
        int i = 0;
        while(i < this.paradas.size()) {
            if(Objects.equals(this.paradas.get(i).getParadaActual().getUbicacion().getId(), ubicacion.getId())) return i;
            i++;
        }
        return -1;
    }

///Alternativa
//    public Integer calcularDistancia2(Tramo tramo, Miembro miembro){
//
//        int distanciaRecorrida = 0;
//        ParadasTransporte paradaPosicion = obtenerParada(tramo.getInicioTramo());
//        ParadasTransporte paradaFinal = obtenerParada(tramo.getFinTramo());
//
//        while(paradaPosicion != paradaFinal){
//            distanciaRecorrida += paradaPosicion.getDistanciaAlaSiguiente();
//            paradaPosicion = paradaPosicion.getParadaSiguiente();
//        }
//        return distanciaRecorrida;
//    }

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
