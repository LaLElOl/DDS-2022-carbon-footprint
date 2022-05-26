package dominio.persona;

import dominio.Ubicacion;
import dominio.organizacion.Sector;

import java.util.ArrayList;
import java.util.List;

public class Miembro {
    private String apellido;
    private String contrasenia;
    private Ubicacion domicilio;
    private String nombre;
    private Integer numDoc;
    private final List<Sector> sectores;
    private TipoDoc tipoDoc;
    private List<Trayecto> trayectos;
    private String usuario;

    public Miembro(){
        this.sectores = new ArrayList<Sector>();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Ubicacion getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Ubicacion domicilio) {
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(Integer numDoc) {
        this.numDoc = numDoc;
    }

    public List<Sector> getSectores() {
        return sectores;
    }

    public void agregarSector(Sector sector){this.sectores.add(sector);}

    public TipoDoc getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(TipoDoc tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public List<Trayecto> getTrayectos() {
        return trayectos;
    }

    public void setTrayectos(List<Trayecto> trayectos) {
        this.trayectos = trayectos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
