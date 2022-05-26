package dominio.persona;

import dominio.Ubicacion;
import dominio.organizacion.Sector;

import java.util.List;

public class Miembro {
    private String apellido;
    private String contrasenia;
    private Ubicacion domicilio;
    private String nombre;
    private Integer numDoc;
    private List<Sector> sectores;
    private TipoDoc tipoDoc;
    private List<Trayecto> trayectos;
    private String usuario;
}
