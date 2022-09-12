package dominio.organizacion.datos;

import dominio.EntidadPersistente;
import dominio.organizacion.Organizacion;
import dominio.transporte.combustibles.Combustible;
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
}
