package controllers;

import models.dominio.persona.Miembro;
import models.dominio.persona.Trayecto;
import models.dominio.transporte.Ubicacion;
import models.repositorios.RepositorioDeDatosConsumo;
import models.repositorios.RepositorioDeMiembros;
import models.repositorios.RepositorioDeTrayectos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class TrayectoController {

    private RepositorioDeTrayectos repositorioDeTrayectos;
    private RepositorioDeMiembros repositorioDeMiembros;

    public TrayectoController(){
        repositorioDeTrayectos = new RepositorioDeTrayectos();
        repositorioDeMiembros = new RepositorioDeMiembros();
    }

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_trayecto.hbs");
    }

    public Response guardar(Request request,Response response) {

        Integer id = new Integer(request.session().attribute("id"));
        Miembro miembro = this.repositorioDeMiembros.buscarPorUsuario(id);

        Trayecto trayecto = new Trayecto();
        Ubicacion ubicacionInicio = new Ubicacion();
        Ubicacion ubicacionFin = new Ubicacion();

        ubicacionInicio.setCalle(request.queryParams("calle_inicio"));
        ubicacionInicio.setAltura(Integer.valueOf(request.queryParams("altura_inicio")));
        ubicacionInicio.setMunicipio(request.queryParams("localidad_inicio"));
        ubicacionInicio.setProvincia(request.queryParams("provincia_inicio"));
        ubicacionFin.setCalle(request.queryParams("calle_fin"));
        ubicacionFin.setAltura(Integer.valueOf(request.queryParams("altura_fin")));
        ubicacionFin.setMunicipio(request.queryParams("localidad_fin"));
        ubicacionFin.setProvincia(request.queryParams("provincia_fin"));
        trayecto.setInicio(ubicacionInicio);
        trayecto.setFin(ubicacionFin);
        trayecto.setMiembro(miembro);
        trayecto.setNombre(request.queryParams("nombre"));

        this.repositorioDeTrayectos.guardar(trayecto);

        response.redirect("/miembro/trayectos");
        return response;
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Integer id = new Integer(request.session().attribute("id"));
        Miembro miembro = this.repositorioDeMiembros.buscarPorUsuario(id);

        List<Trayecto> todosLosTrayectos = this.repositorioDeTrayectos.buscarTrayectosDeMiembro(miembro.getId());
        return new ModelAndView(new HashMap<String, Object>(){{
            put("trayecto", todosLosTrayectos);
            put("miembro_id",miembro.getId());
        }}, "trayectos.hbs");
    }


}
