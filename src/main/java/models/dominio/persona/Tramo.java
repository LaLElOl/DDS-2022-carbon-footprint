package models.dominio.persona;

import models.dominio.EntidadPersistente;
import models.dominio.transporte.Ubicacion;
import models.dominio.transporte.medios.Transporte;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;

@Getter
@Setter

@Entity
@Table(name = "tramo")
public class Tramo extends EntidadPersistente {

    @ManyToOne
    @JoinColumn(name = "duenio_tramo_id", referencedColumnName = "id")
    private Miembro duenioTramo;

    @OneToOne
    @JoinColumn(name = "ubicacion_fin_id", referencedColumnName = "id")
    private Ubicacion finTramo;

    @OneToOne
    @JoinColumn(name = "ubicacion_inicio_id", referencedColumnName = "id")
    private Ubicacion inicioTramo;

    //TODO: controlar manytoone
    @ManyToOne
    @JoinColumn(name = "transporte_id", referencedColumnName = "id")
    private Transporte transporte;

    @Column(name = "es_compartido")
    private Boolean compartido;

    //TODO: revisar si se pone el OneToOne en el caso de que no se reutilizen los transportes
    @ManyToOne
    @JoinColumn(name = "trayecto_id", referencedColumnName = "id")
    private Trayecto trayecto;

    public Tramo(Miembro m){
        this.duenioTramo = m;
    }

    public Double calcularConsumo() {
        return this.transporte.calcularConsumo();
    }

    public Double calcularDistancia(Miembro miembro) throws IOException {
        return this.transporte.calcularDistancia(this, miembro);
    }

    public Double calcularHuella(Miembro miembro){
        double huella = 0.0;
        try{
            huella = this.calcularConsumo() * this.calcularDistancia(miembro);
        }catch(IOException e){
            e.printStackTrace();
        }
        return huella;
    }
}
