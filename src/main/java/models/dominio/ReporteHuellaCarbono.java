package models.dominio;

import models.dominio.organizacion.Organizacion;
import lombok.Getter;
import lombok.Setter;
import models.dominio.organizacion.datos.EPeriodicidad;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter

@Entity
@Table(name = "reporteHuellaCarbono")

//Aca se guardan los valores anuales y mensuales de la huella carbono (1ro de cada mes/año)
public class ReporteHuellaCarbono extends EntidadPersistente{

    @ManyToOne
    @JoinColumn(name = "organizacion_id",referencedColumnName = "id")
    private Organizacion organizacion;

    @Column(name = "fechaPeriodo", columnDefinition = "DATE")
    private LocalDate fechaPeriodo;

    @Enumerated(EnumType.STRING)
    @Column(name = "periodicidad")
    private EPeriodicidad ePeriodicidad;

    @Column(name = "valorHuella")
    private Double valorHuella;
}
