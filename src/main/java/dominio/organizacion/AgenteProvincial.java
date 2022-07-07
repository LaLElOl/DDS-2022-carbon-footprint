package dominio.organizacion;

import java.util.List;

public class AgenteProvincial {
    private String provincia;
    private String nombre;
    private List<AgenteMunicipal> agentesMunicipales;


    public Integer calcularHCMunicipal(){
        return this.agentesMunicipales.stream().mapToInt(ag->ag.calcularHCMunicipal()).sum();
    }
}
