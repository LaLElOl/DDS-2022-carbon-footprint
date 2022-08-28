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


    public Double calcularHuella(int mes, int anio){
        return this.agentesMunicipales.stream().mapToDouble(a -> a.calcularHuella(mes,anio)).sum();
    }
}
