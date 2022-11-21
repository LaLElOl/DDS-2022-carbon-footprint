package controllers;

import models.dominio.persona.Trayecto;
import models.dominio.transporte.Ubicacion;
import models.repositorios.RepositorioDeTrayectos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class TrayectoController {

    private RepositorioDeTrayectos repositorioDeTrayectos;

    public TrayectoController(){repositorioDeTrayectos = new RepositorioDeTrayectos();}

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_trayecto.hbs");
    }

    public Response guardar(Request request,Response response) {

        Trayecto trayecto = new Trayecto();
        Ubicacion ubicacionInicio = new Ubicacion();
        Ubicacion ubicacionFin = new Ubicacion();

        ubicacionInicio.setCalle(request.queryParams("calle_inicio"));
        ubicacionInicio.setAltura(Integer.valueOf(request.queryParams("altura_inicio")));
        ubicacionFin.setCalle(request.queryParams("calle_fin"));
        ubicacionFin.setAltura(Integer.valueOf(request.queryParams("altura_fin")));
        trayecto.setInicio(ubicacionInicio);
        trayecto.setFin(ubicacionFin);

        this.repositorioDeTrayectos.guardar(trayecto);

        response.redirect("/trayectos");
        return response;
    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<Trayecto> todosLosTrayectos = this.repositorioDeTrayectos.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("trayecto", todosLosTrayectos);
        }}, "trayectos.hbs");
    }


}
