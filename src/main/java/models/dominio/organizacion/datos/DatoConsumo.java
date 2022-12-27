package models.dominio.organizacion.datos;

import models.dominio.EntidadPersistente;
import models.dominio.organizacion.Organizacion;
import models.dominio.transporte.combustibles.Combustible;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter

@Entity
@Table(name = "dato_consumo")

public class DatoConsumo extends EntidadPersistente {

    @Column(name = "actividad")
    private String actividad;

    @ManyToOne
    @JoinColumn(name = "tipo_consumo_id", referencedColumnName = "id")
    private Combustible tipoConsumo;

    @Column(name = "valor")
    private Double valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "periodicidad")
    private EPeriodicidad ePeriodicidad;

    @Column(name = "periodo", columnDefinition = "DATE")
    private LocalDate periodo;

    @ManyToOne
    @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
    private Organizacion organizacion;

    public double calcularHuella(){
        return this.valor * this.tipoConsumo.getFactorEmision();
    }

    public boolean esActividad( String actividad){
        if(this.actividad.equals(actividad)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public String anio(){
        return String.valueOf(this.periodo.getYear());
    }
}
