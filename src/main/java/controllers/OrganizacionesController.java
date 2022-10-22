package controllers;

import models.dominio.Usuario;
import models.dominio.organizacion.Clasificacion;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.TipoOrganizacion;
import models.dominio.transporte.Ubicacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Locale;

public class OrganizacionesController {

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_organizacion.html");
    }

    public Response guardar(Request request,Response response) {


        Organizacion org = new Organizacion();
        Clasificacion clasif = new Clasificacion();
        Usuario usuario = new Usuario();
        Ubicacion ubicacion = new Ubicacion();

        usuario.setNickname(request.queryParams("usuario"));
        //usuario.setContrasenia(request.queryParams("contrasenia"));
        org.setCuit(request.queryParams("cuit"));
        org.setRazonSocial(request.queryParams("razon_social"));
        TipoOrganizacion tipo = TipoOrganizacion.valueOf(request.queryParams("tipo_organizacion").toUpperCase(Locale.ROOT));
        ubicacion.setLocalidad(Integer.valueOf(request.queryParams("codigo_postal")));
        ubicacion.setCalle(request.queryParams("calle"));
        ubicacion.setAltura(Integer.valueOf(request.queryParams("altura")));
        clasif.setClasificacion(request.queryParams("clasificacion"));

        System.out.println(usuario.getNickname());
        //System.out.println(usuario.getContrasenia());
        System.out.println(org.getCuit());
        System.out.println(org.getRazonSocial());
        System.out.println(tipo);
        System.out.println(ubicacion.getLocalidad());
        System.out.println(ubicacion.getCalle());
        System.out.println(ubicacion.getAltura());
        System.out.println(clasif.getClasificacion());

        response.redirect("/alta_organizacion");
        return response;
    }
}
