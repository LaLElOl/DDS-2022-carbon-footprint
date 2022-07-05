package dominio.persona;

import dominio.transporte.Ubicacion;
import dominio.organizacion.Sector;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Miembro {
    private String apellido;
    private String contrasenia;
    private Ubicacion domicilio;
    private String nombre;
    private Integer numDoc;
    private final List<Sector> sectores;
    private TipoDoc tipoDoc;
    private final List<Trayecto> trayectos;
    private String usuario;
    private Contacto contacto;

    public Miembro(){
        this.sectores = new ArrayList<>();
        this.trayectos = new ArrayList<>();
    }

    public void agregarSectores(Sector...sectores){
        Collections.addAll(this.sectores, sectores);
    }

    public void quitarSector(Sector sector){
        this.sectores.remove(sector);
    }

    public void agregarTrayectos(Trayecto...trayectos){
        Collections.addAll(this.trayectos, trayectos);
    }

    public void quitarTrayecto(Trayecto trayecto){
        this.trayectos.remove(trayecto);
    }
}
