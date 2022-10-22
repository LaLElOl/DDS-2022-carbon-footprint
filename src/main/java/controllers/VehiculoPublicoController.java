package controllers;

import models.dominio.transporte.medios.Publico;
import models.dominio.transporte.medios.TipoTransporte;
import models.dominio.transporte.vehiculos.TipoVehiculo;
import models.dominio.transporte.vehiculos.Vehiculo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Locale;

public class VehiculoPublicoController {

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_vehiculo_publico.html");
    }

    public Response guardar(Request request,Response response) {

        Publico publico = new Publico();

        publico.setNombre(request.queryParams("nombre"));
        TipoTransporte tipoTransporte = TipoTransporte.valueOf(request.queryParams("tipoTransporte").toUpperCase(Locale.ROOT));
        //TODO Setear Factor Emision con combustible

        System.out.println(publico.getNombre());
        System.out.println(tipoTransporte);

        response.redirect("/alta_vehiculo_publico");
        return response;
    }
}
