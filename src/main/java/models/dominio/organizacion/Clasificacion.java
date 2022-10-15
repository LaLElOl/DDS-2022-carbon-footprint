package models.dominio.organizacion;

import models.dominio.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "clasificacion")

public class Clasificacion extends EntidadPersistente {

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_organizacion")
    private TipoOrganizacion tipoOrganizacion;

    @Column(name = "clasificacion")
    private String clasificacion;
}
