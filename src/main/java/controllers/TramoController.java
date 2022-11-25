package controllers;

import models.dominio.persona.Miembro;
import models.dominio.persona.Tramo;
import models.dominio.transporte.Ubicacion;
import models.repositorios.RepositorioDeTramos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class TramoController {

    private RepositorioDeTramos repositorioDeTramos;

    public TramoController(){repositorioDeTramos = new RepositorioDeTramos();}

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_tramo.hbs");
    }

    public Response guardar(Request request,Response response) {

        Miembro miembro = new Miembro();// TODO: Setear verdadero duenio
        Tramo tramo = new Tramo(miembro);
        Ubicacion ubicacionInicio = new Ubicacion();
        Ubicacion ubicacionFin = new Ubicacion();

        tramo.setCompartido(Boolean.FALSE);
        guardarUbicaciones(request,response,ubicacionInicio,ubicacionFin);
        tramo.setInicioTramo(ubicacionInicio);
        tramo.setFinTramo(ubicacionFin);

        this.repositorioDeTramos.guardar(tramo);

        response.redirect("/miembro/tramos");
        return response;
    }

    public void guardarUbicaciones(Request request,Response response,Ubicacion ubicacionInicio,Ubicacion ubicacionFin){

        ubicacionInicio.setCalle(request.queryParams("calle_inicio"));
        ubicacionInicio.setAltura(Integer.valueOf(request.queryParams("altura_inicio")));
        ubicacionFin.setCalle(request.queryParams("calle_fin"));
        ubicacionFin.setAltura(Integer.valueOf(request.queryParams("altura_fin")));

    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<Tramo> todosLosTramos = this.repositorioDeTramos.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("tramo", todosLosTramos);
        }}, "tramos.hbs");
    }


}
