package controllers;

import models.dominio.Usuario;
import models.dominio.organizacion.AgenteProvincial;
import models.repositorios.RepositorioDeAgentesProvinciales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class AgenteProvincialController {

    private RepositorioDeAgentesProvinciales repositorioAgenteProvincial;

    public AgenteProvincialController(){repositorioAgenteProvincial = new RepositorioDeAgentesProvinciales();}

    public ModelAndView mostrarTodos(Request request, Response response) {
        List<AgenteProvincial> todosLosAgentesProvinciales = this.repositorioAgenteProvincial.buscarTodos();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_provincial", todosLosAgentesProvinciales);
        }}, "agentes_provincials.hbs");
    }

    public ModelAndView mostrar(Request request, Response response) {
        String idBuscado = request.params("id");
        AgenteProvincial agenteProvincialBuscado = this.repositorioAgenteProvincial.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_provincial", agenteProvincialBuscado);
        }}, "agente_provincial.hbs");//TODO
    }

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

        this.repositorioAgenteProvincial.guardar(agenteProvincial);

        response.redirect("/alta_agente_Provincial");
        return response;
    }

    public ModelAndView editar(Request request, Response response) {
        String idBuscado = request.params("id");
        AgenteProvincial agenteProvincialBuscado = this.repositorioAgenteProvincial.buscar(new Integer(idBuscado));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("agente_provincial", agenteProvincialBuscado);
        }}, "agente_provincial.hbs");//TODO
    }


    public Response modificar(Request request, Response response) {
        String idBuscado = request.params("id");
        AgenteProvincial agenteProvincialBuscado = this.repositorioAgenteProvincial.buscar(new Integer(idBuscado));

        agenteProvincialBuscado.setNombre(request.queryParams("nombre"));
        agenteProvincialBuscado.setProvincia(request.queryParams("provincia"));

        this.repositorioAgenteProvincial.guardar(agenteProvincialBuscado);

        response.redirect("/agentes_provinciales");
        return response;
    }
}
