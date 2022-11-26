package controllers;

import models.dominio.TipoUsuario;
import models.dominio.Usuario;
import models.dominio.organizacion.*;
import models.dominio.persona.Contacto;
import models.dominio.persona.Miembro;
import models.dominio.persona.TipoDoc;
import models.dominio.transporte.Ubicacion;
import models.dominio.transporte.combustibles.Combustible;
import models.dominio.transporte.vehiculos.TipoVehiculo;
import models.dominio.transporte.vehiculos.Vehiculo;
import models.repositorios.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MiembroController {

    private RepositorioDeMiembros repositorioDeMiembros;
    private RepositorioDeOrganizaciones repositorioDeOrganizaciones;
    private RepositorioDeSectores repositorioDeSectores;
    private RepositorioDeSolicitudes repositorioDeSolicitudes;


    public MiembroController(){
        repositorioDeMiembros = new RepositorioDeMiembros();
        repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
        repositorioDeSectores = new RepositorioDeSectores();
        repositorioDeSolicitudes = new RepositorioDeSolicitudes();
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<Miembro> todosLosMiembros = this.repositorioDeMiembros.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("miembros", todosLosMiembros);
        }}, "miembros.hbs");
    }

    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");
        Miembro miembroBuscado = this.repositorioDeMiembros.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("miembro", miembroBuscado);
            //put("cant_tareas", servicioBuscado.cantTareas());//TRAMOS,TRAYECTOS?
        }}, "miembro.hbs"); // TODO REVISAR
    }

    public ModelAndView crear(Request request, Response response)
    {
        return new ModelAndView(null, "form_miembro.hbs");
    }

    public Response guardar(Request request,Response response) {

        Usuario usuario = new Usuario();
        Miembro miembro = new Miembro();
        Ubicacion ubicacion = new Ubicacion();
        Contacto contacto = new Contacto();


        usuario.setNickname(request.queryParams("usuario"));
        usuario.setContrasenia(request.queryParams("contrasenia"));
        usuario.setTipoUsuario(TipoUsuario.MIEMBRO);
        miembro.setNombre(request.queryParams("nombre"));
        miembro.setApellido(request.queryParams("apellido"));
        ubicacion.setCalle(request.queryParams("calle"));
        ubicacion.setAltura(Integer.valueOf(request.queryParams("altura")));
        TipoDoc tipoDoc = TipoDoc.valueOf(request.queryParams("tipo_doc").toUpperCase(Locale.ROOT));
        miembro.setNumDoc(Integer.valueOf(request.queryParams("nro_doc")));
        contacto.setNumTelefono(request.queryParams("telefono"));
        contacto.setEmail(request.queryParams("email"));
        usuario.setEmail(request.queryParams("email"));
        miembro.setUsuario(usuario);
        miembro.setDomicilio(ubicacion);
        miembro.setContacto(contacto);
        miembro.setTipoDoc(tipoDoc);

        this.repositorioDeMiembros.guardar(miembro);

        response.redirect("/home");
        return response;
    }

    public ModelAndView editar(Request request, Response response) {
        String idBuscado = request.params("id");
        Miembro miembroBuscado = this.repositorioDeMiembros.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("miembro", miembroBuscado);

        }}, "form_miembro.hbs");//TODO REVISAR

    }


    public Response modificar(Request request, Response response) {
        String idBuscado = request.params("id");
        Miembro miembroBuscado = this.repositorioDeMiembros.buscar(new Integer(idBuscado));
        Ubicacion ubicacion = new Ubicacion();
        Contacto contacto = new Contacto();

        miembroBuscado.setNombre(request.queryParams("nombre"));
        miembroBuscado.setApellido(request.queryParams("apellido"));
        ubicacion.setCalle(request.queryParams("calle"));
        ubicacion.setAltura(Integer.valueOf(request.queryParams("altura")));
        TipoDoc tipoDoc = TipoDoc.valueOf(request.queryParams("tipo_doc").toUpperCase(Locale.ROOT));
        miembroBuscado.setNumDoc(Integer.valueOf(request.queryParams("nro_doc")));
        contacto.setNumTelefono(request.queryParams("numero_telefono"));
        contacto.setEmail(request.queryParams("email"));
        miembroBuscado.setDomicilio(ubicacion);
        miembroBuscado.setContacto(contacto);
        miembroBuscado.setTipoDoc(tipoDoc);

        this.repositorioDeMiembros.guardar(miembroBuscado);

        response.redirect("/index");
        return response;
    }

    public ModelAndView mostrarSectores(Request request, Response response) {
        String id_miembro = request.params("id");
        //List<Sector> sectores = this.repositorioDeSectores TODO Mostrar las organizaciones y el sector
        return new ModelAndView(null, "sectores.hbs");
    }




    public ModelAndView unirseAOrg(Request request, Response response) {
        List<Organizacion> organizaciones = this.repositorioDeOrganizaciones.buscarTodos();
        return new ModelAndView(new HashMap<String,Object>(){{
            put("organizaciones",organizaciones);
        }}, "form_solicitud_org.hbs");
    }


    public Response generarSolicitud(Request request, Response response){
        Sector sector =this.repositorioDeSectores.buscar(new Integer(request.queryParams("sector_id")));
        Integer id = new Integer(request.session().attribute("id"));
        Miembro miembro = this.repositorioDeMiembros.buscarPorUsuario(id);
        Solicitud solicitud = new Solicitud();

        solicitud.setSolicitante(miembro);
        solicitud.setSector(sector);
        sector.agregarMiembroSolicitante(solicitud);
        solicitud.setFecha(LocalDate.now());

        this.repositorioDeSolicitudes.guardar(solicitud);
        this.repositorioDeSectores.guardar(sector);
        response.redirect("/home");

        return response;
    }

    public Response recibirOrganizacion(Request request, Response response) {
        String organizacion_id = request.queryParams("organizacion_id");

        response.redirect("/miembro/unirse_sector/" + organizacion_id);
        return response;
    }

    public ModelAndView mostrarSectoresOrganizacion(Request request, Response response) {
        List<Sector> sectores = this.repositorioDeSectores.buscarTodos(request.params("id"));

        return new ModelAndView(new HashMap<String,Object>(){{
            put("sectores",sectores);
        }}, "form_solicitud_sector.hbs");

    }

    public ModelAndView tipoTransporte(Request request, Response response) {
        Integer id_miembro = new Integer(request.params("id"));
        Integer id_trayecto = new Integer(request.params("id_trayecto"));
        return new ModelAndView(new HashMap<String,Object>(){{
            put("id_miembro",id_miembro);
            put("id_trayecto",id_trayecto);
        }}, "tipo_transporte.hbs");
    }






}
