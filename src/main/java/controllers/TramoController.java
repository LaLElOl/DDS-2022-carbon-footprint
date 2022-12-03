package controllers;

import helpers.EntityManagerHelper;
import models.dominio.organizacion.Sector;
import models.dominio.persona.Miembro;
import models.dominio.persona.Tramo;
import models.dominio.persona.Trayecto;
import models.dominio.transporte.Ubicacion;
import models.dominio.transporte.medios.*;
import models.dominio.transporte.vehiculos.Vehiculo;
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
    private RepositorioDeVehiculos repositorioDeVehiculos;
    private RepositorioDeParadas repositorioDeParadas;

    public TramoController(){
        repositorioDeTramos = new RepositorioDeTramos();
        repositorioDeMiembros = new RepositorioDeMiembros();
        repositorioDeTrayectos = new RepositorioDeTrayectos();
        repositorioDeTransportes = new RepositorioDeTransportes();
        repositorioDeVehiculos = new RepositorioDeVehiculos();
        repositorioDeParadas = new RepositorioDeParadas();
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
        ubicacionInicio.setLocalidad(Integer.valueOf(request.queryParams("localidad_inicio")));
        ubicacionFin.setCalle(request.queryParams("calle_fin"));
        ubicacionFin.setAltura(Integer.valueOf(request.queryParams("altura_fin")));
        ubicacionFin.setLocalidad(Integer.valueOf(request.queryParams("localidad_fin")));

    }

    public ModelAndView mostrarTodos(Request request, Response response) {
        Integer id_trayecto = new Integer(request.params("id_trayecto"));
        List<Tramo> todosLosTramos = this.repositorioDeTramos.buscarTodos(id_trayecto);
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

        response.redirect("/miembro/"+ miembro.getId()+"/trayecto/"+id_trayecto+"/tramos");

        return response;
    }

    public ModelAndView tramoContratado(Request request, Response response) {
        List<ServicioContratado> serviciosContratados = this.repositorioDeTransportes.buscarServiciosContratados();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("servicio_contratado", serviciosContratados);
        }}, "tramo_contratado.hbs");
    }

    public Response guardarTramoContratado(Request request, Response response) {
        Integer servicio_contratado_id = new Integer(request.queryParams("servicio_contratado_id"));
        ServicioContratado servicioContratado = this.repositorioDeTransportes.buscarServicioContratado(servicio_contratado_id);
        Integer id = new Integer(request.params("id"));
        Miembro miembro = this.repositorioDeMiembros.buscar(id);
        Integer id_trayecto = new Integer(request.params("id_trayecto"));
        Trayecto trayecto = this.repositorioDeTrayectos.buscar(id_trayecto);
        Tramo tramo = new Tramo(miembro);
        Ubicacion ubicacionInicio = new Ubicacion();
        Ubicacion ubicacionFin = new Ubicacion();

        guardarUbicaciones(request,response,ubicacionInicio,ubicacionFin);
        tramo.setInicioTramo(ubicacionInicio);
        tramo.setFinTramo(ubicacionFin);
        tramo.setTrayecto(trayecto);
        tramo.setTransporte(servicioContratado);

        this.repositorioDeTramos.guardar(tramo);

        response.redirect("/miembro/"+ miembro.getId()+"/trayecto/"+id_trayecto+"/tramos");

        return response;
    }

    public ModelAndView tramosParticular(Request request, Response response) {
        String miembro_id = request.params("id");
        List<Vehiculo> vehiculos = this.repositorioDeVehiculos.buscarTodosSegunMiembro(miembro_id);

        return new ModelAndView(new HashMap<String, Object>(){{
            put("vehiculo", vehiculos);
        }}, "tramo_particular.hbs");
    }


    public Response guardarTramoParticular(Request request, Response response) {
        Integer vehiculo_id = new Integer(request.queryParams("vehiculo_id"));
        Vehiculo vehiculo = this.repositorioDeVehiculos.buscar(vehiculo_id);
        Particular particular = new Particular();
        particular.setVehiculo(vehiculo);
        Integer id = new Integer(request.params("id"));
        Miembro miembro = this.repositorioDeMiembros.buscar(id);
        Integer id_trayecto = new Integer(request.params("id_trayecto"));
        Trayecto trayecto = this.repositorioDeTrayectos.buscar(id_trayecto);
        Tramo tramo = new Tramo(miembro);
        Ubicacion ubicacionInicio = new Ubicacion();
        Ubicacion ubicacionFin = new Ubicacion();

        tramo.setTransporte(particular);
        tramo.setTrayecto(trayecto);
        guardarUbicaciones(request,response,ubicacionInicio,ubicacionFin);
        tramo.setInicioTramo(ubicacionInicio);
        tramo.setFinTramo(ubicacionFin);

        this.repositorioDeTransportes.guardar(particular);
        this.repositorioDeTramos.guardar(tramo);

        response.redirect("/miembro/"+ miembro.getId()+"/trayecto/"+id_trayecto+"/tramos");

        return response;
    }

    public ModelAndView tramoPublico(Request request, Response response) {
        List<Publico> Transportes_Publicos = this.repositorioDeTransportes.buscarPublicos();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("publico", Transportes_Publicos);
        }}, "tramo_publico.hbs");
    }

    public Response recibirTransportePublico(Request request, Response response) {
        String publico_id = request.queryParams("publico_id");
        String trayecto_id = request.params("id_trayecto");

        response.redirect("/miembro/trayecto/" + trayecto_id + "/tramo_publico/" + publico_id);
        return response;
    }

    public ModelAndView mostrarParadasPublico(Request request, Response response) {
        List<Parada> paradas = this.repositorioDeParadas.buscarParadasTransporte(request.params("id_publico"));

        return new ModelAndView(new HashMap<String,Object>(){{
            put("parada",paradas);
        }}, "paradas_publico.hbs");

    }

    public Response guardarTramoPublico(Request request, Response response) {
        Integer id = new Integer(request.session().attribute("id"));
        Miembro miembro = this.repositorioDeMiembros.buscarPorUsuario(id);
        Tramo tramo = new Tramo(miembro);
        Integer id_trayecto = new Integer(request.params("id_trayecto"));
        Trayecto trayecto = this.repositorioDeTrayectos.buscar(id_trayecto);
        Integer id_publico = new Integer(request.params("id_publico"));
        Publico publico = (Publico) this.repositorioDeTransportes.buscar(id_publico);
        Integer paradaT_inicio_id = new Integer(request.queryParams("parada_inicio_id"));
        Integer paradaT_destino_id = new Integer(request.queryParams("parada_destino_id"));
        ParadasTransporte parada_inicio = this.repositorioDeParadas.buscarParadaTransporte(paradaT_inicio_id);
        ParadasTransporte parada_destino = this.repositorioDeParadas.buscarParadaTransporte(paradaT_destino_id);

        Ubicacion ubicacion_inicio = parada_inicio.getParadaActual().getUbicacion();
        Ubicacion ubicacion_destino = parada_destino.getParadaActual().getUbicacion();

        tramo.setInicioTramo(ubicacion_inicio);
        tramo.setFinTramo(ubicacion_destino);
        tramo.setTrayecto(trayecto);
        tramo.setTransporte(publico);

        this.repositorioDeTramos.guardar(tramo);

        response.redirect("/miembro/"+ miembro.getId()+"/trayecto/"+id_trayecto+"/tramos");

        return response;
    }
}
