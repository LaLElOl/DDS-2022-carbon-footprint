package models.dominio.transporte;

import models.dominio.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter

@Entity
@Table(name = "ubicacion")

public class Ubicacion extends EntidadPersistente {

    @Column(name = "altura")
    private Integer altura;

    @Column(name = "calle")
    private String calle;

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "localidad")
    private Integer localidad;

    @Column(name = "longitud")
    private Double longitud;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "municipio")
    private String municipio;
}
