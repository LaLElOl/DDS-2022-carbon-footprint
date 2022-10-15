package models.dominio.transporte.combustibles;

import models.dominio.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_combustible")
@Table(name = "combustible")
public abstract class Combustible extends EntidadPersistente {

    @Column(name = "factor_emision")
    private Double factorEmision;

    abstract public Double obtenerGramos(Double kilos);
}
