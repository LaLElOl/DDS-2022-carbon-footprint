package dominio.organizacion;

import dominio.organizacion.datos.Periodicidad;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class AgenteMunicipal {
    private String municipio;
    private String nombre;
    private List<Organizacion> organizaciones;

    public AgenteMunicipal(){
        this.organizaciones = new ArrayList<>();
    }

    public void agregarOrganizaciones(Organizacion...organizaciones){
        Collections.addAll(this.organizaciones,organizaciones);
    }

    public Double calcularHuella(int mes, int anio) {
        return this.organizaciones.stream().mapToDouble(o -> o.calcularHuella(mes, anio)).sum();
    }
}
