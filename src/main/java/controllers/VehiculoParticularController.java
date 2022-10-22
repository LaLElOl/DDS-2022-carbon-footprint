package controllers;

import models.dominio.organizacion.Sector;
import models.dominio.transporte.vehiculos.TipoVehiculo;
import models.dominio.transporte.vehiculos.Vehiculo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Locale;

public class VehiculoParticularController {

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_vehiculo_particular.html");
    }

    public Response guardar(Request request,Response response) {

        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setFactorVehiculo(Double.valueOf(request.queryParams("factorVehiculo")));
        TipoVehiculo tipoVehiculo = TipoVehiculo.valueOf(request.queryParams("tipoVehiculo").toUpperCase(Locale.ROOT));
        //TODO Setear Factor Emision con combustible

        System.out.println(vehiculo.getFactorVehiculo());
        System.out.println(tipoVehiculo);

        response.redirect("/alta_vehiculo_particular");
        return response;
    }
}
