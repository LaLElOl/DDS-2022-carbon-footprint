package controllers;

import models.dominio.Usuario;
import models.repositorios.RepositorioDeUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

    RepositorioDeUsuarios repositorioDeUsuarios =  new RepositorioDeUsuarios();

    public ModelAndView mostrarInicio(Request request, Response response) {

        Integer id = new Integer(request.session().attribute("id"));
        //TODO si id = null, mandarlo al login
        Usuario usuario = this.repositorioDeUsuarios.buscar(id);
        String archivo = "login.html";

        switch (usuario.getTipoUsuario()){
            case MIEMBRO:
                archivo = "home_miembro.html";
                break;
            case ORGANIZACION:
                archivo = "home_organizacion.html";
                break;
            case AGENTE_MUNICIPAL:
                archivo = "home_agente.html";
                break;
            case AGENTE_PROVINCIAL:
                archivo = "home_agente.html";
                break;
            default:
                //archivo = "home_miembro.html";
                break;
        }


        return new ModelAndView(null, archivo);
    }
}