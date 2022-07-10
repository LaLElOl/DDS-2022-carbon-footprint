package dominio.organizacion;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AgenteProvincial {
    private String provincia;
    private String nombre;
    private List<AgenteMunicipal> agentesMunicipales;

    public AgenteProvincial() {
        this.agentesMunicipales = new ArrayList<>();
    }


    public Double calcularHCMunicipal(){
        return this.agentesMunicipales.stream().mapToDouble(AgenteMunicipal::calcularHCMunicipal).sum();
    }
}
