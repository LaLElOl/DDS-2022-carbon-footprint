package dominio.organizacion;

import dominio.persona.Miembro;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Sector {
    private final HashSet<Miembro> miembros;
    private final HashSet<Miembro> miembrosSolicitantes;
    private String nombre;
    private Organizacion organizacion;

    public Sector(){
        this.miembros = new HashSet<Miembro>();
        this.miembrosSolicitantes = new HashSet<Miembro>();
    }

    public void darDeAltaAMiembro(Miembro miembro) {
        if(this.miembrosSolicitantes.remove(miembro)){
            this.miembros.add(miembro);
        }
    }

    public HashSet<Miembro> getMiembros() {
        return miembros;
    }

    public void agregarMiembroSolicitante(Miembro miembro){this.miembrosSolicitantes.add(miembro);}

    public HashSet<Miembro> getMiembrosSolicitantes() {
        return miembrosSolicitantes;
    }
}
