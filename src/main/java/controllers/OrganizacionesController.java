package controllers;

import models.dominio.organizacion.Clasificacion;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.TipoOrganizacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Locale;

public class OrganizacionesController {

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_organizacion.html");
    }

    public Response guardar(Request request,Response response) {

        /*
        Organizacion org = new Organizacion();
        Clasificacion clasif = new Clasificacion();

        org.setCuit(request.queryParams("cuit"));
        org.setRazonSocial(request.queryParams("razon_social"));

        clasif.setTipoOrganizacion(request.queryParams("clasificacion"));
        TipoOrganizacion tipo = TipoOrganizacion.valueOf(request.queryParams("tipo_organizacion").toUpperCase(Locale.ROOT));

        System.out.println(tipo);
         */

        response.redirect("/alta_organizacion");
        return response;
    }
}
