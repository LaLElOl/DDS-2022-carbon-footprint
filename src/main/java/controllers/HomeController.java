package controllers;

import models.dominio.Usuario;
import models.repositorios.RepositorioDeUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

    RepositorioDeUsuarios repositorioDeUsuarios =  new RepositorioDeUsuarios();

    public ModelAndView mostrarInicio(Request request, Response response) {

        //System.out.print((char[]) request.session().attribute("id"));
        Integer id = Integer.parseInt(request.session().attribute("id"));

        Usuario usuario = this.repositorioDeUsuarios.buscar(id);
        String archivo = "login.hbs";

        switch (usuario.getTipoUsuario()){
            case MIEMBRO:
                archivo = "home_miembro.hbs";
                break;
            case ORGANIZACION:
                archivo = "home_organizacion.hbs";
                break;
            case AGENTE_MUNICIPAL:
                archivo = "home_agente.hbs";
                break;
            case AGENTE_PROVINCIAL:
                archivo = "home_agente.hbs";
                break;
            default:
                //archivo = "home_miembro.hbs";
                break;
        }


        return new ModelAndView(null, archivo);
    }
}
