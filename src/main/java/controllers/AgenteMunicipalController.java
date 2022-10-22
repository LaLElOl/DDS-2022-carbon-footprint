package controllers;

import models.dominio.Usuario;
import models.dominio.organizacion.AgenteMunicipal;
import models.dominio.organizacion.Clasificacion;
import models.dominio.organizacion.Organizacion;
import models.dominio.organizacion.TipoOrganizacion;
import models.dominio.transporte.Ubicacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Locale;

public class AgenteMunicipalController {

    public ModelAndView crear(Request request, Response response) {
        return new ModelAndView(null, "form_agente_municipal.html");
    }

    public Response guardar(Request request,Response response) {

        Usuario usuario = new Usuario();
        AgenteMunicipal agenteMunicipal = new AgenteMunicipal();

        usuario.setNickname(request.queryParams("usuario"));
        usuario.setContrasenia(request.queryParams("contrasenia"));
        agenteMunicipal.setNombre(request.queryParams("nombre"));
        agenteMunicipal.setMunicipio(request.queryParams("municipio"));

        System.out.println(usuario.getNickname());
        System.out.println(usuario.getContrasenia());
        System.out.println(agenteMunicipal.getNombre());
        System.out.println(agenteMunicipal.getMunicipio());
        //No printea nada :c

        response.redirect("/alta_agente_municipal");
        return response;
    }
}
