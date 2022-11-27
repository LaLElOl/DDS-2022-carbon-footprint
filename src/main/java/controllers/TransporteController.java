package controllers;

import models.dominio.transporte.medios.ServicioContratado;
import models.dominio.transporte.medios.Transporte;
import models.dominio.transporte.vehiculos.TipoVehiculo;
import models.dominio.transporte.vehiculos.Vehiculo;
import models.repositorios.RepositorioDeTransportes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class TransporteController {

    private RepositorioDeTransportes repositorioDeTransportes;

    public TransporteController(){repositorioDeTransportes = new RepositorioDeTransportes();}

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_vehiculo_particular.hbs");
    }


    public ModelAndView crearServicioContratado(Request request, Response response) {
        return new ModelAndView(null, "form_servicio_contratado.hbs");
    }

    public ModelAndView mostrarServiciosContratados(Request request, Response response) {
        List<ServicioContratado> serviciosContratados = this.repositorioDeTransportes.buscarServiciosContratados();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("contratado", serviciosContratados);
        }}, "servicios_contratados.hbs");
    }



    public ModelAndView mostrarTodos(Request request, Response response) {
        List<Vehiculo> todosLosVehiculosParticulares = this.repositorioDeTransportes.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("transporte", todosLosVehiculosParticulares);
        }}, "transportes.hbs");
    }



    public Response guardar(Request request,Response response) {

        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setFactorVehiculo(Double.valueOf(request.queryParams("factorVehiculo")));
        TipoVehiculo tipoVehiculo = TipoVehiculo.valueOf(request.queryParams("tipoVehiculo").toUpperCase(Locale.ROOT));
        //TODO Setear Factor Emision con combustible
        vehiculo.setTipoVehiculo(tipoVehiculo);


        response.redirect("/alta_vehiculo_particular");
        return response;
    }


    public Response guardarServicioContratado(Request request, Response response) {
        ServicioContratado servicioContratado = new ServicioContratado();

        servicioContratado.setFactorEmision(Double.valueOf(request.queryParams("factor_emision")));
        servicioContratado.setNombre(request.queryParams("nombre"));

        this.repositorioDeTransportes.guardar(servicioContratado);

        response.redirect("/home");
        return response;
    }
}
