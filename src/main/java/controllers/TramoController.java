package controllers;

import helpers.EntityManagerHelper;
import models.dominio.persona.Miembro;
import models.dominio.persona.Tramo;
import models.dominio.persona.Trayecto;
import models.dominio.transporte.Ubicacion;
import models.dominio.transporte.medios.Ecologico;
import models.repositorios.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class TramoController {

    private RepositorioDeTramos repositorioDeTramos;
    private RepositorioDeMiembros repositorioDeMiembros;
    private RepositorioDeTrayectos repositorioDeTrayectos;
    private RepositorioDeTransportes repositorioDeTransportes;

    public TramoController(){
        repositorioDeTramos = new RepositorioDeTramos();
        repositorioDeMiembros = new RepositorioDeMiembros();
        repositorioDeTrayectos = new RepositorioDeTrayectos();
        repositorioDeTransportes = new RepositorioDeTransportes();
    }

    public ModelAndView crear(Request request, Response response) {

        Integer id = new Integer(request.session().attribute("id"));
        Miembro miembro = this.repositorioDeMiembros.buscarPorUsuario(id);
        List<Trayecto> trayectos = this.repositorioDeTrayectos.buscarTrayectosDeMiembro(miembro.getId());
        return new ModelAndView(new HashMap<String, Object>(){{
            put("trayecto", trayectos);
        }}, "form_tramo.hbs");
    }

    public Response guardar(Request request,Response response) {

        Integer id = new Integer(request.session().attribute("id"));
        Miembro miembro = this.repositorioDeMiembros.buscarPorUsuario(id);
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

    public ModelAndView tramoEcologico(Request request, Response response) {
        return new ModelAndView(null, "tramo_ecologico.hbs");
    }


    public Response guardarTramoEcologico(Request request, Response response) {
        Ecologico ecologico = new Ecologico();
        Integer id = new Integer(request.session().attribute("id"));
        Miembro miembro = this.repositorioDeMiembros.buscarPorUsuario(id);
        Integer id_trayecto = new Integer(request.params("id_trayecto"));
        Trayecto trayecto = this.repositorioDeTrayectos.buscar(id_trayecto);
        Tramo tramo = new Tramo(miembro);
        Ubicacion ubicacionInicio = new Ubicacion();
        Ubicacion ubicacionFin = new Ubicacion();

        tramo.setCompartido(Boolean.FALSE);
        guardarUbicaciones(request,response,ubicacionInicio,ubicacionFin);
        tramo.setInicioTramo(ubicacionInicio);
        tramo.setFinTramo(ubicacionFin);
        tramo.setTrayecto(trayecto);
        tramo.setTransporte(ecologico);

        this.repositorioDeTransportes.guardar(ecologico);
        this.repositorioDeTramos.guardar(tramo);

        response.redirect("/miembro/trayectos"); //TODO: Luego cambiar a vista de tramos.

        return response;
    }
}
