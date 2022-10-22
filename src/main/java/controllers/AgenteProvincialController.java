package controllers;

import models.dominio.Usuario;
import models.dominio.organizacion.AgenteMunicipal;
import models.dominio.organizacion.AgenteProvincial;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AgenteProvincialController {
    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_agente_provincial.html");
    }

    public Response guardar(Request request,Response response) {

        Usuario usuario = new Usuario();
        AgenteProvincial agenteProvincial = new AgenteProvincial();

        usuario.setNickname(request.queryParams("usuario"));
        usuario.setContrasenia(request.queryParams("contrasenia"));
        agenteProvincial.setNombre(request.queryParams("nombre"));
        agenteProvincial.setProvincia(request.queryParams("provincia"));

        System.out.println(usuario.getNickname());
        System.out.println(usuario.getContrasenia());
        System.out.println(agenteProvincial.getNombre());
        System.out.println(agenteProvincial.getProvincia());

        response.redirect("/alta_agente_Provincial");
        return response;
    }
}
