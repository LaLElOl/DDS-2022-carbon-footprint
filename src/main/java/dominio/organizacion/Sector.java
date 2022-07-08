package dominio.organizacion;

import dominio.persona.Miembro;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Sector {
    private List<Solicitud> solicitudes;
    private final HashSet<Miembro> miembros;
    private String nombre;
    private Organizacion organizacion;

    public Sector(){
        this.miembros = new HashSet<Miembro>();
        this.solicitudes = new ArrayList<Solicitud>() {
        };
    }

    public void darDeAltaAMiembro(Solicitud solicitud) {
        if(this.solicitudes.remove(solicitud)){
            this.miembros.add(solicitud.getSolicitante());
        }
    }

    public HashSet<Miembro> getMiembros() {
        return miembros;
    }

    public void agregarMiembroSolicitante(Solicitud solicitud){this.solicitudes.add(solicitud);}

    public List<Solicitud> getMiembrosSolicitantes() {
        return solicitudes;
    }
}
