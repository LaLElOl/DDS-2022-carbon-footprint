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

    @Column(name = "localidad_id")
    private Integer localidadId;
    @Column(name = "localidad")
    private String localidad;

    @Column(name = "longitud")
    private Double longitud;

    @Column(name = "provincia_id")
    private String provinciaId;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "municipio_id")
    private String municipioId;

    @Column(name = "municipio")
    private String municipio;

    public Ubicacion(){
        altura = 0;
        calle = "";
        latitud = 0.0;
        longitud = 0.0;
        municipio = "";
        municipioId = "";
        localidad = "";
        localidadId = 0;
        provincia = "";
        provinciaId = "";
    }
    public Boolean algunNull(){
        if(this.altura.equals(null) ||
        this.calle.equals(null) ||
        this.latitud == null ||
        this.localidadId == null ||
        this.localidad.equals(null) ||
        this.provinciaId.equals(null) ||
        this.provincia.equals(null) ||
        this.municipioId.equals(null) ||
        this.municipio.equals(null)){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
