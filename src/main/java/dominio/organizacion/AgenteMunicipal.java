package dominio.organizacion;

import lombok.Getter;
import lombok.Setter;
import services.lectorExcel.Periodicidad;

import java.util.ArrayList;
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

    public Double calcularHCMunicipal(){
        return this.organizaciones.stream().mapToDouble(Organizacion::calcularHC).sum();
    }
}
