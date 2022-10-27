package controllers;

import models.dominio.Usuario;
import models.dominio.organizacion.Clasificacion;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.TipoOrganizacion;
import models.dominio.transporte.Ubicacion;
import models.repositorios.RepositorioDeOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class OrganizacionesController {

    private RepositorioDeOrganizaciones repositorioDeOrganizaciones;

    public OrganizacionesController(){repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();}

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<Organizacion> todosLasOrganizaciones = this.repositorioDeOrganizaciones.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizaciones", todosLasOrganizaciones);
        }}, "organizaciones.hbs");
    }

    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");
        Organizacion organizacionBuscada = this.repositorioDeOrganizaciones.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizacion", organizacionBuscada);
            //put("cant_tareas", servicioBuscado.cantTareas());
        }}, "organizacion.hbs");
    }



    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_organizacion.html");
    }

    public Response guardar(Request request,Response response) {


        Organizacion org = new Organizacion();
        Clasificacion clasif = new Clasificacion();
        Usuario usuario = new Usuario();
        Ubicacion ubicacion = new Ubicacion();

        usuario.setNickname(request.queryParams("usuario"));
        usuario.setContrasenia(request.queryParams("contrasenia"));
        org.setCuit(request.queryParams("cuit"));
        org.setRazonSocial(request.queryParams("razon_social"));
        TipoOrganizacion tipo = TipoOrganizacion.valueOf(request.queryParams("tipo_organizacion").toUpperCase(Locale.ROOT));
        ubicacion.setLocalidad(Integer.valueOf(request.queryParams("codigo_postal")));
        ubicacion.setCalle(request.queryParams("calle"));
        ubicacion.setAltura(Integer.valueOf(request.queryParams("altura")));
        clasif.setClasificacion(request.queryParams("clasificacion"));
        org.setUsuario(usuario);
        org.setClasificacion(clasif);
        org.setUbicacion(ubicacion);

        this.repositorioDeOrganizaciones.guardar(org);

        response.redirect("/alta_organizacion");
        return response;
    }


    public ModelAndView editar(Request request, Response response) {
        String idBuscado = request.params("id");
        Organizacion organizacionBuscada = this.repositorioDeOrganizaciones.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizacion", organizacionBuscada);
            //put("cant_tareas", servicioBuscado.cantTareas());
        }}, "organizacion.hbs");
    }

    public Response modificar(Request request, Response response) {
        String idBuscado = request.params("id");
        Organizacion organizacionBuscada = this.repositorioDeOrganizaciones.buscar(new Integer(idBuscado));
        Clasificacion clasif = new Clasificacion();
        Ubicacion ubicacion = new Ubicacion();

        organizacionBuscada.setCuit(request.queryParams("cuit"));
        organizacionBuscada.setRazonSocial(request.queryParams("razon_social"));
        TipoOrganizacion tipo = TipoOrganizacion.valueOf(request.queryParams("tipo_organizacion").toUpperCase(Locale.ROOT));
        ubicacion.setLocalidad(Integer.valueOf(request.queryParams("codigo_postal")));
        ubicacion.setCalle(request.queryParams("calle"));
        ubicacion.setAltura(Integer.valueOf(request.queryParams("altura")));
        clasif.setClasificacion(request.queryParams("clasificacion"));
        organizacionBuscada.setClasificacion(clasif);
        organizacionBuscada.setUbicacion(ubicacion);

        this.repositorioDeOrganizaciones.guardar(organizacionBuscada);

        response.redirect("/organizaciones");
        return response;
    }

}
