package dominio.organizacion;

import java.util.List;

public class AgenteMunicipal {
    private String municipio;
    private String nombre;
    private List<Organizacion> organizaciones;


    public Integer calcularHCMunicipal(){
        return this.organizaciones.stream().mapToInt(org->org.calcularHC()).sum();
    }
}
