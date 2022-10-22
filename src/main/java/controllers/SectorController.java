package controllers;

import models.dominio.Usuario;
import models.dominio.organizacion.Sector;
import models.dominio.persona.Contacto;
import models.dominio.persona.Miembro;
import models.dominio.persona.TipoDoc;
import models.dominio.transporte.Ubicacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Locale;

public class SectorController {

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_sector.html");
    }

    public Response guardar(Request request,Response response) {

        Sector sector = new Sector();

        sector.setNombre(request.queryParams("nombre"));
        //sector.setOrganizacion();


        System.out.println(sector.getNombre());


        response.redirect("/alta_sector");
        return response;
    }
}
