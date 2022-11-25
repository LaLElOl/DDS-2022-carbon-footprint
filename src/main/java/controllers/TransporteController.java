package controllers;

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

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<Vehiculo> todosLosVehiculosParticulares = this.repositorioDeTransportes.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("transporte", todosLosVehiculosParticulares);
        }}, "transportes.hbs");
    }


    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_vehiculo_particular.hbs");
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

}
